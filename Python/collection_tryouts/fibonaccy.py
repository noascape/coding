def fib(number: int, status: int):
    value = number + status
    print(value)
    fib(value, number)


fib(1, 0)