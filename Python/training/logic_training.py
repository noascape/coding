#Imports
from math import *                                                              #for further math functions
import useful_tools                                                             #import another python file as a module to get access of its functionality (has to be in the same directory as the file itself); This can save much time: https://docs.python.org/3/py-modindex.html

print(useful_tools.roll_dice(10))
#Variables
name = "John"                                                                   #string
age = 50.123                                                                    #number
is_male = True                                                                  #boolean value  (True and False)

#Print and String
print("Neue\nZeile")
print("Anführungszeichen\"")
print(name.lower())                                                             #convert string to lower case / upper() case
print(name.isupper())                                                           #function if the string is upper case, you can combine 
print(name.islower())                                                           #function if the string is lower case 
print(len(name))                                                                #length of a string
print(name[0])                                                                  #to get the value of the first digit
print(name.index("J"))                                                          #to get the index of J, can also be a whole word (would give the index of where this word starts)
print(name.replace("John", "Ludwig"))                                           #replace the first with the second

#Print and Numbers
print(50)                                                                       #You can just print numbers
print(3 + (4.5 * 2))                                                            #you can even let python calculate
print(-2.34)                                                                    #you can print negative numers and decimal numbers
print(10 % 3)                                                                   #to get the remainder: 10:3 = 3 R 1 -> 10%3 = 1
print(str(age), name)                                                           #convert variable number to a string, because you cannot print out a number next to a sting
print(abs(age))                                                                 #to get the absolute numbers (would affect negative numbers)
print(pow(2,3))                                                                 #to get the power number: 2^3 
print(max(4,6))                                                                 #returns the higher number
print(min(4,6))                                                                 #returns the minimum number
print(round(age))                                                               #to round a decimal number

#Further accesed math functions:
print(floor(3.7))                                                               #round the number down
print(ceil(3.7))                                                                #round the number up
print(sqrt(36))                                                                 #square number (of 36: 6*6 -> 6)

#If statements
is_tall = False
is_not_male = True
if is_male or is_not_male and is_tall:                                          #the is_male boolean is already defined 
    print("You are a tall male")
elif is_male and not is_tall:
    print("You are a short male")
elif not is_male and is_tall:
    print("You are a tall female")
else: 
    print("You are neither a male nor tall")

def max_num(num1, num2, num3):                                                  #you can also use different comparisons
    if num1 >= num2 and num1 >= num3:                                           # != means isnot, and you can also use <, > alone
        return num1
    elif num2 >= num1 and num2 >= num3: 
        return num2
    else: 
        return num3
    
#While Loop
i = 1
while i <= 10:
    print(i)
    i += 1                                                                      #each loop i will get an increment of 1
print("Done with Loop")

#For Loop
family = ["Papa", "Mama", "Opa"]                                                #Array
for letter in "Giraffe Academy":                                                #loops through every single letter 
    print(letter)
for index in family:                                                            #loops through every single entry in the array
    print(index)
for num in range(3, 10):                                                        #1st one: start, optional 2nd one: length, optional 3rd one: steps
    print(num)
for blabla in range(len(family)):                                               #len(family) = 3 so its equal to in range(3) 
    if blabla == 0:
        print("First Iteration:", family[blabla])
    else:
        print(family[blabla])

#User Input
user_input = input("Enter a number: ")                                          #put the user input into a variable 
result = age + int(user_input)                                                  #convert the input string into an integer (number without decimals); float (for decimal numbers)
print("Hello", result)                                                          #use the information 

#Calculation
def raise_to_power(base_num, pow_num):
    output = 1
    for i in range(pow_num):                                                    #we loop through it as often as the pow numbers value (2^3)
        output = output * base_num
    return output 
                                                                            
print(raise_to_power(2, 3))

#Files (Reading)
employee_file = open("employees.txt", "r")                                      #Relative path / absolute path / name of the file if in the same directory      | r = read; w = write; a = append; r+ = reading and writing
print(employee_file.read())                                                     #.readable() to get a boolean if its really readable, .readline to only read a line which you can do multiple times, .readlines will put all the lines into an array
employee_file.close()                                                           #you should always close a file after opening it

#Files (Appending)
employee_file = open("employees.txt", "a")                                      #w would overwrite the existing file while a is just appending
employee_file.write("\nToby - Human Resources")                                 #you have to be careful to not execute this multiple times and mess up the file
employee_file.close()

#Files (Writing)
employee_file = open("index.html", "w")                                         #this will create another file called index.html with the following content
employee_file.write("<p> This is HTML </p>")
employee_file.close()

#Try & Except 
try:                                                                            #to protect the whole programm 
    number = int(input("Enter a number: "))
    print(number)
except ZeroDivisionError as err:                                                #further specification of the possible errors 
    print(err)                                                                  #we can store the error as a variable
except ValueError:
    print("Invalide Input")
except Exception as e:                                                          #general except
    print(f"An error accured: {e}")