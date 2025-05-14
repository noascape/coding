#Array/List [] (list)
from typing import Tuple

friends = ["Kevin", "Karen", "Jim"]                                             #friend array
friends_extention = ["Rob", "Will"]
lucky_numbers = [4, 8, 15, 17, 33, 43]                                          #number array

friends2 = friends.copy()                                                       #copy a list into another list
friends[0] = "Mike"                                                             #modify the list
friends.extend(friends_extention)                                               #extend one list with another one
friends.append("Luke")                                                          #append a value to the end of the list
lucky_numbers.insert(1, 8)                                       #insert a value to a position and the other values will be pushed to the right
lucky_numbers.pop()                                                             #to remove the last value, you can also do .clear or .remove and some more
friends.count("Jim")                                                            #count the amount of Jim`s in the list
lucky_numbers.reverse()                                                         #reverse the order of the list
print(friends[1])                                                               #list output of the element with the array 1 (=Karen), you could also do it from the right side of the list [-1] = Jim
friends.remove("Mike")
friends.index("Karen")
lucky_numbers.sort()
friends2.clear()
sorted(friends)
letzes_element = friends[-1]
umgekehrt = friends[::-1]                                                       #gibt eine umgekehrte Kopie der Liste
len(umgekehrt)
#in                                                                             #prüft, ob ein Element enthalten ist: if "Jim" in friends:



#Tuple ()
coordinates = (4, "Anna", 5)                                                #a Tuple ist like a list(Array), but (), cannot be modified, can have elements of different types  --> return num1, name, num2

def tuple_function() -> Tuple[int, str, int]:
    return coordinates                                                      #the position will always remain the same -> coordinates[1] is always the name in this case


#Dictionary {} (dict) (= Hash-Map)
monthConversions = {
    "Jan": "January",                                                          #on the left a unique key in the dicitionary, on the ride the value
    "Feb": "February",
    "Mar": "March",
    "Apr": "April",
    "May": "May",
    "Jun": "June",
    "Jul": "July",
    "Aug": "August",
    "Sep": "September",
    "Oct": "October",
    "Nov": "November",
    "Dec": "December",
}
print(monthConversions["Nov"])                                                  #will give the associated value of the key
print(monthConversions.get("Luc", "Not a valid Key"))                           #you can specify a default value in case the key is not faund in the dictinoary
monthConversions.keys()
monthConversions.values()
monthConversions.items()
monthConversions.update({"Dec": "Dezember"})
monthConversions.update(Dec="December")
del monthConversions["Dec"]


# Beispiele: JSON-Datein:
jsonresponse_list = [
    {
        "id": 1,
        "name": "Anna"
    },
    {
        "id": 2,
        "name": "Beate"
    },
    {
        "id": 3,
        "name": "Horst"
    }
]
namen = [eintrag.get("name") for eintrag in jsonresponse_list ]
print(f"Namen [List]: {namen}")

jsonresponse_dict = {
    "hausDaten": {
        "Hausnummer": 7,
        "Straße": "Waldwiese",
        "Farbe": "Schwarz"
    },
    "inhaberDaten": {
        "Name": "Müller",
        "Anzahl": 5,
        "Tiere": True
    }
}
infos = {
    "Name": jsonresponse_dict.get("inhaberDaten", {}).get("Name"),
    "Anzahl": jsonresponse_dict.get("inhaberDaten", {}).get("Anzahl")
}
print(f"Infos [Dict]: {infos}")

# Vereinheitlichung beide in Liste von Dictionaries:
vereinheitlicht_list = []
for eintrag in jsonresponse_list:  # Informatinoen aus der Liste
    vereinheitlicht_list.append({"name": eintrag.get("name")})

vereinheitlicht_list.append({
    "name": infos.get("Name"),
    "anzahl": infos.get("Anzahl")
})
print(f"Vereinheitlicht [List]: {vereinheitlicht_list}")

vereinheitlicht_dict = {eintrag["name"]: eintrag for eintrag in vereinheitlicht_list}
print(f"Vereinheitlicht [Dict]: {vereinheitlicht_dict}")


# SET                                                                          #Verwendbar für Mengenoperationen (union, intersection, difference), Duplikat-Erkennung oder -Entfernung, enthält keine Duplikate
set1 = set()
my_set = {1, 2, 3, 2}                                                           #the position of the elements can vary
print(my_set)
my_set.add(5)
my_set.remove(5)
my_set.discard(4)                                                               #Entfernt ein Element (Kein Fehler bei Nichtvorhandensein)
my_set.pop()                                                                    #Entfernt ein beliebiges Element
my_set.clear()                                                                  #Löscht alle Elemente


#2D List
number_grid = [
    [1, 2, 3],                                                                  #4 Elements in a List which are all elements themselves
    [4, 5, 6],
    [7, 8, 9],
    [0]
]
print(number_grid[0][0])                                                        #How to access the Elements in this kind of list
for line in number_grid:
    for col in line:
        print(col)

