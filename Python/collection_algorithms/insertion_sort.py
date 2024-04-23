import random, time

def insertion_sort(A):
    for j in range(1, len(A)):
        key = A[j]
        i = j - 1
        while i >= 0 and A[i] > key:
            A[i + 1] = A[i]
            i = i - 1
        A[i + 1] = key

# Arrays
#C = [8,9,3,5,6,2,4,1,7]   
array_length_10 = [random.randint(1,100) for _ in range(10)]
array_length_20 = [random.randint(1,100) for _ in range(20)]
array_length_50 = [random.randint(1,100) for _ in range(50)]


#insertion_sort(C)
start_1 = time.perf_counter_ns()
insertion_sort(array_length_10)
end_1 = time.perf_counter_ns()
insertion_sort(array_length_20)
end_2 = time.perf_counter_ns()
insertion_sort(array_length_50)
end_3 = time.perf_counter_ns()

print(array_length_10)

#print("Sortierte Liste ist:", C)
print("Die Liste der Länge 10 ist:", array_length_10)
print("Algorithmus 1 hat:", end_1 - start_1, "Nanosekunden gebraucht")
print("Die Liste der Länge 20 ist:", array_length_20)
print("Algorithmus 2 hat:", end_2 - end_1, "Nanosekunden gebraucht")
print("Die Liste der Länge 50 ist:", array_length_50)
print("Algorithmus 3 hat:", end_3 - end_2, "Nanosekunden gebraucht")
