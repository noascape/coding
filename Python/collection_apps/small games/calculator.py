#Calculator
number1 = float(input("Enter first number:"))                                   #converts the input from a standard string into a float
op = input("Enter operator:")
number2 = float(input("Enter second number:"))
if op == "+":
    print(number1 + number2)
elif op == "-":
    print(number1 - number2)
elif op == "/": 
    print(number1 / number2)
elif op == "*": 
    print(number1 * number2)
else:
    print("invalide operator")