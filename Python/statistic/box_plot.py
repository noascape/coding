import matplotlib.pyplot as plt

data = [2, 3, 5, 5, 5, 7, 7, 7, 7, 7, 8, 8, 9, 9, 10, 12, 12, 12, 12, 13, 14, 14]

plt.figure(figsize=(8, 5))
plt.boxplot(data, vert=False, patch_artist=True)
plt.title("Box-Plot der gegebenen Zahlen")
plt.xlabel("Werte")
plt.grid(axis='x', linestyle='--', alpha=0.7)
plt.show()