#Imports
from math import *                                                              #for further math functions



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

#List []
friends = ["Kevin", "Karen", "Jim"]                                             #friend array
lucky_numbers = [4, 8, 15, 17, 33, 43]                                          #number array
friends2 = friends.copy()                                                       #copy a list into another list
friends[0] = "Mike"                                                             #modify the list
friends.extend(lucky_numbers)                                                   #extend one list with another one
friends.append("Luke")                                                          #append a value to the end of the list
lucky_numbers.insert(1, "Kelly")                                                #insert a value to a position and the other values will be pushed to the right
lucky_numbers.pop()                                                             #to remove the last value, you can also do .clear or .remove and some more
friends.count("Jim")                                                            #count the amount of Jim`s in the list                                                          
lucky_numbers.reverse()                                                         #reverse the order of the list
print(friends[1])                                                               #list output of the element with the array 1 (=Karen), you could also do it from the right side of the list [-1] = Jim

#Tuple ()
coordinates = (4, 5)                                                            #a Tuple ist like a list, but () cannot be modified

#Functions
def say_hi(name, age):                                                          #Function definition and naming and the parameters name, age that have to be given when calling this function
    print("Hello", name, "you are", str(age))
say_hi("Mike", 15)                                                              #Execute the function say_hi with the parameters "Mike" and 15

#Return keyword
def cube(num):
    return num*num*num                                                          #will give back the value to whatever called the function, which is the print statement below, return breaks the function (so you cannot put code below it)
print(cube(3))

#If statements
is_tall = False
is_not_male = True
if is_male or is_not_male and is_tall:                                          #the is_male boolean is already defined 
    print("You are a tall male")
elif is_male and not(is_tall):
    print("You are a short male")
elif not(is_male) and is_tall:
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
#User Input
#user_input = input("Enter a number: ")                                          #put the user input into a variable 
#result = age + int(user_input)                                                  #convert the input string into an integer (number without decimals); float (for decimal numbers)
#print("Hello", result)                                                          #use the information 

#Mad Lips                                                                               
#color = input("Enter a color:")
#plural_noun = input("Enter a Plural Noun:")
#celebrity = input("Enter a celebrity:")
#print("Roses are", color)                                                       #now the output of this Mad Lips
#print(plural_noun, "are blue")
#print("I love", celebrity)

