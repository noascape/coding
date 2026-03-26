import numpy as np
import matplotlib.pyplot as plt

def f(x):
    return x**3 - 3*x   


# Hill Climbing (2D)
def hill_climbing(start, step=0.2, max_iter=50):
    x = start
    path = [x]

    for _ in range(max_iter):
        left = x - step
        right = x + step

        if f(left) > f(x):
            x = left
        elif f(right) > f(x):
            x = right
        else:
            break

        path.append(x)

    return path


# PLOT 
def plot_1d(path):
    x_vals = np.linspace(-5, 5, 400)
    y_vals = f(x_vals)

    plt.figure(figsize=(7,6))

    # Graph
    plt.plot(x_vals, y_vals, 'k', linewidth=2)

    # Hill Climbing Steps (red)
    px = path
    py = [f(x) for x in px]
    plt.plot(px, py, 'ro-', label="Hill Climbing")

    # Found Maximum (blue)
    plt.scatter(px[-1], py[-1], s=100, label="Gefundenes Maximum")

    # Start
    plt.scatter(px[0], py[0], s=100, label="Start")

    plt.title("Hill Climbing (2D)")
    plt.xlabel("x")
    plt.ylabel("f(x)")
    plt.grid()
    plt.legend()

    plt.show()


# MAIN
start = -4
path = hill_climbing(start)

plot_1d(path)