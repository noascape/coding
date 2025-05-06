#Funktionen:
# Wiederverwendbarkeit, Strukturierung & Lesbarkeit, Vermeidung von Fehlern, Testbarkeit (lassen sich einzeln testen), Abstraktion (Können verwendet werden, ohne Komplexen Inhalt zu verstehen),
# Parameterisierung (durch Parameter flexibel und generisch einsetzbar), Vermeidung von globalem Code, Kürzerer Hauptcode (weil Details in Funktionen ausgelagert sind, die dann einfach aufgerufen werden)

from typing import Iterator, Optional, Any, NoReturn, NamedTuple, TypedDict
from dataclasses import dataclass


#Rückgabefunktionen
#Mehrere Rückgabewerte (Tupel)
def get_name_and_age(name: str, age: int) -> tuple[str, int]:                                      #tuple / Tuple[...] (/ NamedTuple / dataclass für mehr Lesbarkeit) für mehrere Rückgabewerte
    return name, age

returned_name, returned_age = get_name_and_age("Mike", 12)


#Listen, Dictionaries, Sets, etc.
def get_scores() -> list[int]:
    return [98, 12, 20, 1]

def get_names() -> dict[str, str]:
    return {"mode": "dark", "lang": "de", "code": "12fe238aC"}

def get_tags() -> set[str]:
    return {"python", "java", "c++"}


#Iteratoren, Generatoren
def count_up_to(max_number: int) -> Iterator[int]:                                     #Iterator muss von typing importiert werden; Bei Generator: (YieldType, SendType, ReturnType)
    count = 1
    while count < max_number:
        yield count                                                                    #ist die Alternative zu return (gibt ebenfalls Wert zurück, merkt sich aber vorherigen Zustand. Sinnvoll bei großen Datenmengen, Iterator-Funktionen, streams, pipielines oder endlose Schleifen
        count += 1

for number in count_up_to(3):
    print(number)


#Optional (kann None sein)
def find_user(id_number: int) -> Optional[set[str]]:                                 #Optional muss von typing importiert werden
    user_dictionary = {1: "John", 2: "Malvin", 3: "Theo"}
    user_name = user_dictionary[id_number]
    return {user_name}

print(find_user(1))


#Union; | (entweder/oder-Typen)
def parse_input(user_input: str) -> int | str:                                      # = Union[int, str]: Union muss von typing importiert werden
    try:
        return int(user_input)
    except ValueError:
        return user_input


#Any (beliebiger Typ)
def get_value() -> Any:                                                         #Any muss von typing importiert werden; verwenden wenn man den genauen Typ nicht kennt
    return 1


#NoReturn (endet mit Fehler oder Schleife)
def crash() -> NoReturn:                                                       #NoReturn muss von typing importiert werden
    raise RuntimeError("Fatal error")


#Selbst definierte Klassen oder Datentypen                                     #man kapselt Verhalten und Daten (OOP), man kann Methoden, Validierungen, usw. einbauen, guter Stil für komplexere Anwendungen
class User:
    def __init__(self, username: str, age: int):
        self.username = username
        self.age = age
    def __str__(self):
        return f"{self.username} ({self.age}"

def create_user() -> User:
    return User("Theo", 25)


#Rückgabe strukturierter Objekte
# a) NamedTuple                                                                     #Vorteile: immutable, schnell, typsicher, Tuple-Zugriff + Attributnamen
class UserData(NamedTuple):                                                         #NamedTuple muss von typing importiert werden
    username: str
    age: int

def fetch_user_data() -> UserData:
    return UserData("Alice", 32)

user = fetch_user_data()
print(user.username)
print(user.age)


# b) dataclass                                                                      #Vorteile: Mutable (änderbar), lesbar und kompakt, Automatische __init__, __repr__, __eq__, etc.
@dataclass                                                                          #muss von dataclasses importiert werden
class UserProfile:
    username: str
    age: int
    email: str

def load_profile() -> UserProfile:
    return UserProfile("Bob", 28, "bob@example.com")

profile = load_profile()
print (profile.email)


# c) TypedDict  (--> pydantic würde alles prüfen)                                   #Vorteil: Typisierte Dictionaries (ideal für JSON/API-Daten), Optional: Felder können auch optional gemacht werden, perfekt für strukturierte, aber flexible Daten
class UserDict(TypedDict):                                                          #muss von typing importiert werden
    username: str
    age: int
    email: str

def get_user_dict() -> UserDict[str, str]:                                          #Die Rückgabe dieser Funktion erfüllt die Struktur von UserDict! (Keine Überprüfugn: keine Auswirkung auf Performance und keine Fehler)
    return {                                                                        #besser zu dokumentieren, IDE-Autovervollständigung kann genutzt werden, Typfehler frühzeitig mit weiteren Tools finden, Code robuster und wartbarer machen
        "username": "Eve",
        "age": 40,
        "email": "eve@example.com"
    }

user_info = get_user_dict()
print(user_info["username"])



#Functions
def say_hi(name, age):                                                                      #Grundaufbau, Parameter müssen nicht typisiert werden, Nachteile: Keine automatisierte Typprüfung, Weniger verständlich, Kein Support für statische Tools
    print("Hello", name, "you are", str(age))

say_hi("Mike", 15)                                                              #Execute the function say_hi with the parameters "Mike" and 15



