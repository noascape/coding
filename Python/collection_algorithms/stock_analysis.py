array = [1, 4, 7, 3, 2, 9]

#not a fast algorithm as it needs n*n! time
def Max(a):
    n = len(a)
    gap = 0
    for i in range(n):
        for j in range(i+1, n):
            if (a[j] - a[i]) > gap:
                gap = a[j] - a[i]
    return gap

solution = Max(array)
print("Der größte Unterschied in dem Array beträgt:", solution)



