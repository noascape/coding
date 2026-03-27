from __future__ import annotations

import random
import time
from typing import TypedDict

from blackbox_client import BlackBoxClient


API_URL = "http://10.50.15.53:8001"
SESSION_CODE = "FB4D6A"
BOT_NAME = "First Tryout LocalSearch [Bot]"

LOW = -512.0
HIGH = 512.0


class HistoryItem(TypedDict):
    x: float
    y: float
    z: float
    step: int


def clamp(value: float, low: float = LOW, high: float = HIGH) -> float:
    return max(low, min(high, value))


def random_point() -> tuple[float, float]:
    return random.uniform(LOW, HIGH), random.uniform(LOW, HIGH)


def get_best_point(history: list[HistoryItem]) -> HistoryItem | None:
    if not history:
        return None
    return min(history, key=lambda p: p["z"])


def propose_point(step: int, history: list[HistoryItem], best_z: float | None) -> tuple[float, float]:
    if step == 0:
        return random_point()

    if step == 1:
        return random_point()

    prev = history[-2]
    last = history[-1]
    best = get_best_point(history)

    if best is None:
        return random_point()

    dx = last["x"] - prev["x"]
    dy = last["y"] - prev["y"]

    # Schrittweite abhängig von bisher bestem z
    if best["z"] < -300:
        factor = 0.15
        jitter = 15.0
    elif best["z"] < -100:
        factor = 0.25
        jitter = 25.0
    else:
        factor = 0.35
        jitter = 50.0

    # letzter Schritt war besser -> vorsichtig weiter
    if last["z"] < prev["z"]:
        x = last["x"] + dx * factor
        y = last["y"] + dy * factor
        return clamp(x), clamp(y)

    # letzter Schritt war schlechter -> zurück zum besten Punkt
    # und kleine lokale Suche um ihn herum
    x = best["x"] + random.uniform(-jitter, jitter)
    y = best["y"] + random.uniform(-jitter, jitter)
    return clamp(x), clamp(y)


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