from itertools import product

weights = [2, 4, 3, 5]
values = [3, 5, 4, 6]
W = 8

best_value = -1
best_solutions = []

print("Alle Belegungen:")
for x in product([0, 1], repeat=4):
    total_weight = sum(w * xi for w, xi in zip(weights, x))
    total_value = sum(v * xi for v, xi in zip(values, x))
    feasible = total_weight <= W

    print(f"x={x}, Gewicht={total_weight}, Wert={total_value}, zulässig={feasible}")

    if feasible:
        if total_value > best_value:
            best_value = total_value
            best_solutions = [x]
        elif total_value == best_value:
            best_solutions.append(x)

print("\nOptimale Lösung(en):")
for sol in best_solutions:
    print(sol)
print("Bester Wert:", best_value)