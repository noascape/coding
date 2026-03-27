from __future__ import annotations

import math
import random
import time
from typing import TypedDict

from blackbox_client import BlackBoxClient


API_URL = "http://10.50.15.53:8001"
SESSION_CODE = "FB4D6A"
BOT_NAME = "Noahaha [Bot3]"


class HistoryItem(TypedDict):
    x: float
    y: float
    z: float
    step: int


def clamp(value: float, low: float = -5.0, high: float = 5.0) -> float:
    return max(low, min(high, value))


def point_already_tested(
    x: float,
    y: float,
    history: list[HistoryItem],
    tolerance: float = 1e-5,
) -> bool:
    for p in history:
        if abs(p["x"] - x) < tolerance and abs(p["y"] - y) < tolerance:
            return True
    return False


def random_point(history: list[HistoryItem]) -> tuple[float, float]:
    for _ in range(300):
        x = random.uniform(-5, 5)
        y = random.uniform(-5, 5)
        if not point_already_tested(x, y, history):
            return x, y
    return random.uniform(-5, 5), random.uniform(-5, 5)


def get_best_points(history: list[HistoryItem], k: int = 8) -> list[HistoryItem]:
    return sorted(history, key=lambda p: p["z"])[:k]


def get_best_point(history: list[HistoryItem]) -> HistoryItem | None:
    if not history:
        return None
    return min(history, key=lambda p: p["z"])


def get_last_improvement_distance(history: list[HistoryItem]) -> int:
    best_so_far = float("inf")
    last_improvement_index = -1

    for i, p in enumerate(history):
        if p["z"] < best_so_far:
            best_so_far = p["z"]
            last_improvement_index = i

    if last_improvement_index == -1:
        return len(history)

    return len(history) - 1 - last_improvement_index


def get_elite_center(points: list[HistoryItem]) -> tuple[float, float]:
    """
    Gewichteter Mittelpunkt der besten Punkte.
    Bessere Punkte (kleineres z) bekommen mehr Gewicht.
    """
    eps = 1e-9
    total_w = 0.0
    cx = 0.0
    cy = 0.0

    for p in points:
        w = 1.0 / (p["z"] + eps)
        cx += w * p["x"]
        cy += w * p["y"]
        total_w += w

    if total_w == 0:
        return 0.0, 0.0

    return clamp(cx / total_w), clamp(cy / total_w)


def exploration_point(history: list[HistoryItem]) -> tuple[float, float]:
    """
    Frühe grobe Exploration.
    """
    preset = [
        (0.0, 0.0),
        (-5.0, -5.0),
        (-5.0, 5.0),
        (5.0, -5.0),
        (5.0, 5.0),
        (-5.0, 0.0),
        (5.0, 0.0),
        (0.0, -5.0),
        (0.0, 5.0),
        (-2.5, -2.5),
        (-2.5, 2.5),
        (2.5, -2.5),
        (2.5, 2.5),
    ]

    for x, y in preset:
        if not point_already_tested(x, y, history):
            return x, y

    return random_point(history)


def ring_candidates(cx: float, cy: float, radius: float) -> list[tuple[float, float]]:
    directions = [
        (1, 0),
        (-1, 0),
        (0, 1),
        (0, -1),
        (1, 1),
        (1, -1),
        (-1, 1),
        (-1, -1),
    ]

    out = []
    for dx, dy in directions:
        length = math.sqrt(dx * dx + dy * dy)
        x = clamp(cx + radius * dx / length)
        y = clamp(cy + radius * dy / length)
        out.append((x, y))
    return out


def midpoint(a: tuple[float, float], b: tuple[float, float]) -> tuple[float, float]:
    return clamp((a[0] + b[0]) / 2.0), clamp((a[1] + b[1]) / 2.0)


def propose_point(
    step: int,
    history: list[HistoryItem],
    best_z: float | None,
) -> tuple[float, float]:

    # Strategische Punkte zuerst
    preset = [
        (1.0, 1.0),   # Rosenbrock-Optimum
        (0.0, 0.0),
        (1.0, 0.0),
        (0.0, 1.0),
        (-1.0, 1.0),
        (0.5, 0.25),  # liegt auf y = x^2
        (1.2, 1.44),  # auch auf y = x^2
        (0.8, 0.64),
    ]

    for x, y in preset:
        if not point_already_tested(x, y, history):
            return x, y

    # Danach um besten Punkt herum fein suchen
    best = min(history, key=lambda p: p["z"])
    bx, by = best["x"], best["y"]

    # Schrittweiten fein verkleinern
    if step < 10:
        radii = [0.5, 0.25, 0.1]
    elif step < 20:
        radii = [0.25, 0.1, 0.05]
    else:
        radii = [0.1, 0.05, 0.01, 0.005]

    candidates = []

    for r in radii:
        candidates.extend([
            (clamp(bx + r), by),
            (clamp(bx - r), by),
            (bx, clamp(by + r)),
            (bx, clamp(by - r)),
            (clamp(bx + r), clamp(by + r)),
            (clamp(bx + r), clamp(by - r)),
            (clamp(bx - r), clamp(by + r)),
            (clamp(bx - r), clamp(by - r)),
        ])

    # Zusätzlich Punkte entlang des Rosenbrock-Tals y = x^2 testen
    for dx in [0.5, 0.25, 0.1, 0.05, 0.01]:
        for sign in [-1, 1]:
            x = clamp(bx + sign * dx)
            y = clamp(x * x)
            candidates.append((x, y))

    for x, y in candidates:
        if not point_already_tested(x, y, history):
            return x, y

    return random_point(history)


def main():
    client = BlackBoxClient(API_URL, SESSION_CODE)

    info = client.get_public_info()
    print("Public session info:", info)

    if info.status != "running":
        print(f"Session ist nicht aktiv (status={info.status}). Bot wird nicht gestartet.")
        return

    participant_id = client.join(BOT_NAME, is_bot=True)
    print("Joined as:", participant_id)

    history: list[HistoryItem] = []
    best_z: float | None = None

    for step in range(info.max_steps):
        x, y = propose_point(step, history, best_z)

        try:
            result = client.evaluate(x, y)
        except Exception as e:
            print(f"Bot gestoppt in Schritt {step + 1}: {e}")
            break

        z = float(result["z"])
        history.append(
            {
                "x": float(x),
                "y": float(y),
                "z": z,
                "step": int(result["step"]),
            }
        )

        if best_z is None or z < best_z:
            best_z = z

        print(
            f"step={result['step']:02d} "
            f"x={x:.6f} y={y:.6f} z={z:.8f} best_z={best_z:.8f}"
        )

        time.sleep(0.2)


if __name__ == "__main__":
    main()