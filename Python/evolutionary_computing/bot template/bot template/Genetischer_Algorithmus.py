from __future__ import annotations

import random
import time
from typing import TypedDict

from blackbox_client import BlackBoxClient


API_URL = "http://10.50.15.53:8001"
SESSION_CODE = "9DAB43"
BOT_NAME = "Generic_GA [Bot4]"

LOW = -500.0
HIGH = 500.0

USE_LOCAL_TEST_FUNCTION = True


class Individual(TypedDict):
    x: float
    y: float
    z: float
    step: int


def clamp(value: float, low: float = LOW, high: float = HIGH) -> float:
    return max(low, min(high, value))


def random_point() -> tuple[float, float]:
    return random.uniform(LOW, HIGH), random.uniform(LOW, HIGH)


# Nur zu Testzwecken
def himmelblau(x: float, y: float) -> float:
    return (x * x + y - 11) ** 2 + (x + y * y - 7) ** 2


def evaluate_local(x: float, y: float, step: int) -> dict:
    return {
        "x": x,
        "y": y,
        "z": himmelblau(x, y),
        "step": step,
    }


def evaluate_point(client: BlackBoxClient | None, x: float, y: float, step: int) -> dict:
    if USE_LOCAL_TEST_FUNCTION:
        return evaluate_local(x, y, step)
    return client.evaluate(x, y)


def get_best(population: list[Individual]) -> Individual:
    return min(population, key=lambda ind: ind["z"])


def tournament_selection(population: list[Individual], tournament_size: int = 3) -> Individual:
    candidates = random.sample(population, k=min(tournament_size, len(population)))
    return min(candidates, key=lambda ind: ind["z"])


def arithmetic_crossover(parent1: Individual, parent2: Individual) -> tuple[float, float]:
    alpha = random.random()
    x = alpha * parent1["x"] + (1 - alpha) * parent2["x"]
    y = alpha * parent1["y"] + (1 - alpha) * parent2["y"]
    return clamp(x), clamp(y)


def mutate(x: float, y: float, sigma: float, mutation_probability: float) -> tuple[float, float]:
    if random.random() < mutation_probability:
        x += random.gauss(0, sigma)
    if random.random() < mutation_probability:
        y += random.gauss(0, sigma)
    return clamp(x), clamp(y)


def create_child(population: list[Individual], sigma: float, mutation_probability: float) -> tuple[float, float]:
    parent1 = tournament_selection(population)
    parent2 = tournament_selection(population)
    x, y = arithmetic_crossover(parent1, parent2)
    x, y = mutate(x, y, sigma, mutation_probability)
    return x, y


def main():
    client = None

    if not USE_LOCAL_TEST_FUNCTION:
        client = BlackBoxClient(API_URL, SESSION_CODE)

        info = client.get_public_info()
        print("Public session info:", info)

        if info.status != "running":
            print(f"Session ist nicht aktiv (status={info.status}).")
            return

        participant_id = client.join(BOT_NAME, is_bot=True)
        print("Joined as:", participant_id)

        max_steps = info.max_steps
    else:
        print("Lokaler Testmodus aktiv.")
        max_steps = 3000

    # Allgemeine GA-Parameter
    population_size = 12
    elite_size = 2
    mutation_probability = 0.25
    sigma = 30.0

    step_counter = 0
    history: list[Individual] = []

    # 1. Initialpopulation
    population: list[Individual] = []
    while len(population) < population_size and step_counter < max_steps:
        x, y = random_point()
        result = evaluate_point(client, x, y, step_counter + 1)

        individual: Individual = {
            "x": float(x),
            "y": float(y),
            "z": float(result["z"]),
            "step": int(result["step"]),
        }

        population.append(individual)
        history.append(individual)
        step_counter += 1

        best = get_best(population)
        print(
            f"step={individual['step']:03d} "
            f"x={individual['x']:.6f} y={individual['y']:.6f} "
            f"z={individual['z']:.8f} "
            f"best_z={best['z']:.8f}"
        )

    # 2. Evolution
    while step_counter < max_steps:
        population.sort(key=lambda ind: ind["z"])
        elites = population[:elite_size]

        new_population: list[Individual] = elites.copy()

        while len(new_population) < population_size and step_counter < max_steps:
            x, y = create_child(
                population=population,
                sigma=sigma,
                mutation_probability=mutation_probability,
            )

            result = evaluate_point(client, x, y, step_counter + 1)

            child: Individual = {
                "x": float(x),
                "y": float(y),
                "z": float(result["z"]),
                "step": int(result["step"]),
            }

            new_population.append(child)
            history.append(child)
            step_counter += 1

            best_overall = min(history, key=lambda ind: ind["z"])
            print(
                f"step={child['step']:03d} "
                f"x={child['x']:.6f} y={child['y']:.6f} "
                f"z={child['z']:.8f} "
                f"best=({best_overall['x']:.6f}, {best_overall['y']:.6f}) "
                f"best_z={best_overall['z']:.8f}"
            )

            time.sleep(0.05)

        population = new_population

    print("\nFertig.")
    best = min(history, key=lambda ind: ind["z"])
    print(
        f"Bestes Ergebnis: x={best['x']:.6f}, y={best['y']:.6f}, z={best['z']:.8f}"
    )


if __name__ == "__main__":
    main()