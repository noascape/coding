#Klassen:
# Strukturierung und Kapselung von Logik, Wiederverwendbarkeit, Entkopplung von Daten und Verhalten, Testbarkeit, Skalierbarkeit (besonders bei APIs, Datenverarbeitung, Webentwicklung, usw.)
import json
import requests
#from dataclasses import dataclass
from unittest.mock import patch
import unittest
from fastapi import FastAPI
from pydantic.dataclasses import dataclass

class Student:
    def __init__(self, name, major, gpa, is_on_probation):                      #initialize function     | self is needed, so that Python knows which specific object within the class is being referred to (which would be student1 and student2 in this case)
        self.name = name                                                    #the student stores a name, major,... and now this information of all students will be stored alltogether
        self.major = major
        self.gpa = gpa
        self.is_on_probation = is_on_probation

    def on_honor_roll(self):                                                    #another function that checks if the students gpa is good enough to be on honor roll
        if self.gpa >= 3.5:
            return True
        else:
            return False

student1 = Student("Jim", "Business", 3.1, False)
student2 = Student("Anna", "Finance", 1.7, True )
print(student1.name, student1.gpa)
print(student1.on_honor_roll())

#Inheritance of classes
class Chef:
    def make_chicken(self):
        print("The chef makes a chicken")
    def make_salad(self):
        print("The chef makes a salad")
    def make_special_dish(self):
        print("The chef makes bbq ribs")

class ChineseChef(Chef):                                                        #can do everything that the normal Chef can do -> inheritance from Chef
    def make_fried_rice(self):
        print("The chef makes fried rice")
    def make_special_dish(self):                                                #you can overwrite an already existing definition (redefine it with the same name)
        print("The chef makes orange chicken")

yourChef = ChineseChef()
myChef = Chef()
yourChef.make_special_dish()
myChef.make_special_dish()
Chef().make_special_dish()


# 1) Kapselung von Daten + Verhalten
class Config:
    def __init__(self, env: str, debug: bool):
        self.env = env
        self.debug = debug

    # Anpassung der Darstellung des Objekts beim Ausgeben
    def __repr__(self):
        return f"<BRKPF(brNo={self.env}, brMotorO{self.debug!r})>"



# 2) Testbare Einheiten für Funktionen                                             #exterme API-Logik isolieren und testen --> unittest.mock
class WeatherService:
    def __init__(self, api_key: str):
        self.api_key = api_key

    def fetch_weather(self, city: str):
        url = f"https://api.weather.com/v1/{city}?ke={self.api_key}"
        response = requests.get(url)
        return response.json()
        #pass

class TestWeatherService(unittest.TestCase):
    @patch("requests.get")                                                  #ersetze während dieses Tests die Funktion requests.get durch ein Mock-Objekt
    def test_fetch_weather_success(self, mock_get):                         #wenn nicht wirklich ein API-Call gemacht werden soll, sondern nur kontrolliert werden soll, was requests.get() zurückgibt
        # Simuliere, dass .json() einen bestimmten Wert zurückgibt
        mock_get.return_value.json.return_value = {"temp": 22}
        # Test-Objekt erzeugen und Funktion testen
        service = WeatherService(api_key="dummy")
        result = service.fetch_weather("Berlin")
        # Erwartung prüfen und sicherstellen, dass requests.get genau einmal aufgerufen wurde
        self.assertEqual(result["temp"], 22)
        mock_get.assert_called_once()


# 3) Modelle für Datenstrukturen (wie API-Responses)
class User:
    def __init__(self, data: dict):
        self.id = data["id"]
        self.username = data["username"]
        self.email = data["email"]

    def is_active(self) -> bool:
        return "@" in self.email


# 4) Utility-Klassen (ohne Zustand)                                                             #Gruppiere verwandte Funktionen thematisch. Kein Zustand nötig
class StringUtils:
    @staticmethod
    def capitalize_each_word(s: str) -> str:
        return ' '.join(word.capitalize() for word in s.split())


# 5) Service-Klassen / API-Wrapper                                                              #Eine Klasse kapselt alle Methoden zum Zugriff auf einen Dienst
class GitHubClient:
    def __init__(self, token: str):
        self.token = token

    def get_repos(self, username: str):
        headers = {"Authorization": f"Bearer {self.token}"}
        response = requests.get(f"https://api.github.com/users/{username}/repos", headers=headers)
        return response.json()


# 6) Factory / Builder Pattern                                                                  #Erstelle gezielt vorkonfigurierte Objekte
class User2:
    def __init__(self, username: str, role: str):
        self.username = username
        self.role = role


class UserFactory:                                                                               #zeigt, dass hier die offizielle Art ist, User2-Objekte zu erzeugen, Erweiterbarkeit, Testbarkeit und Interface-Konzepte
    @staticmethod                                                                                #static, weil sie nicht auf self zugreifen
    def create_admin(self, username: str):
        return User2(username, role="admin")
    @staticmethod
    def create_guest(self):
        return User2("guest", role="readonly")


# 7) Vererbung für Spezialfälle (Inheritance)                                                     #Ideal für polymorphe Strukturen, z.B. bei Spielen, UI-Elementen, Event-Handling
class Animal:
    def speak(self): pass                                                                         #pass ist ein Platzhalter, der nichts tut, aber notwendig ist, wenn ein Block leer wäre (Funktion gitb dann None zurück)

class Dog(Animal):
    def speak(self):
        return "Woof"

class Cat(Animal):
    def speak(self):
        return "Meow"


# 8) State Machines / Steuerungen                                                                 #Klasse verwaltet internen Zustand und erlaubt kontrollierte Übergänge
class VendingMachine:
    def __init__(self):
        self.state = "IDLE"

    def insert_coin(self):
        if self.state == "IDLE":
            self.state = "READY"


# 9) Dataklassen                                                                                  #Schnell definierte Klassen für reine Datencontainer, z.B.: API-Modelle
@dataclass                                                                                        #Autovervollständigung in IDEs, Typsicherheit, Automatische __repr___, ..., Bessere Strukturierung bei vielen Modellen in z.B. models.py
class Invoice:
    id: int
    customer: str
    amount: float

json_data = '{"id": 101, "customer": "Jay", "amount": 499.99}'
invoice = Invoice(**json.loads(json_data))          #** = Keyword-Argument-Unpacker: Nimmt ein Dictionary und "entpackt" es zu benannten Parametern -> Invoice(id=101, customer='Jay', amount=499.99)
print(invoice)

#Verwendung mit FastAPI
app = FastAPI()         #Webserver-Objekt, mit dem man Endpunkte definiert

@dataclass              #beschreibt, welche Felder der Client beim POST-Aufruf senden muss
class InvoiceRequest:
    customer: str
    amount: float

@app.post("/invoice")       #Definition der POST-Anfrage auf /invoice; FastAPI übernimmt den JSON-Body, parsed ihn automatisch in ein Invoice Request-Objekt
def create_invoice(invoice_objekt: InvoiceRequest):
    return {"message": f"Invoice for {invoice_objekt.customer} accepted."}


# 10) Validierung und Regeln implementieren                                                        #Man kapselt Regeln für Gültigkeit direkt in der Klasse - keine losen Validierungsfunktionen nötig
class UserInput:
    def __init__(self,text: str):
        if not text.strip():
            raise ValueError("Input must not be empty")
        self.text = text