from typing import Tuple


"""
 ----- Array/List [] (list) -----
"""
friends = ["Kevin", "Karen", "Jim"]
friends_ext = ["Rob", "Will"]

# Kopieren
friends2 = friends.copy()

# Hinzufügen
friends.append("Luke")          # Einzelwert
friends.extend(friends_ext)     # Iterable („aufklappen“)
friends.insert(1, "Olaf")       # an Index 1

# Suchen & Zählen
idx = friends.index("Karen")    # Position oder ValueError
cnt = friends.count("Jim")      # Anzahl Vorkommen

# Entfernen
friends.pop()                   # letztes Element
friends.pop(1)                  # Element an Index 1
friends.remove("Jim")           # erstes Vorkommen eines Werts
friends.clear()                 # alle Elemente löschen

# Sortieren & Umkehren
friends.sort()                  # in-place sortieren
friends.reverse()               # Reihenfolge umdrehen

# Slicing kopiert Teilabschnitte
teil = friends[1:4]             # Elemente Index 1 bis 3
schritte = friends[::2]         # jedes zweite Element

# List Comprehension (Filter + Transform)
nums = [1, 2, 3, 4, 5]
squares = [x*x for x in nums if x % 2 == 1]  # [1,9,25]




"""
 ----- Tuple () -----
"""
coordinates = (4, "Anna", 5)

# Zugriff
x, name, y = coordinates    # Unpacking
first = coordinates[0]

# Methoden (nur zwei, da unveränderlich)
idx1 = coordinates.index("Anna")
cnt1 = coordinates.count(5)  # 5 kommt "1"mal vor

def tuple_function() -> Tuple[int, str, int]:
    return coordinates                 #immer gleiche Position -> coordinates[1] ist immer der name in diesem Fall

# Nach Typ filtern
if isinstance(coordinates[0], int):
    print("Die erste Stelle des Tuple`s ist ein Integer")
print(type(x))



"""
 ----- Dictionary {} (dict) (= Hash-Map) -----
"""
monthConversions = {
    "Jan": "January",
    "Feb": "February",
    "Mar": "March",
}

# Zugriff
val = monthConversions["Jan"]            # KeyError, wenn nicht da
val1 = monthConversions.get("Luc", "–")   # Default

# Hinzufügen / Überschreiben
monthConversions["Apr"] = "April"
monthConversions.update({"May": "May", "Jun": "June"})
monthConversions.update(Jul="July")      # Kurzform

# Entfernen
monthConversions.pop("Mar")              # gibt Wert zurück oder Fehler
monthConversions.pop("Foo", None)        # sicherer Default
monthConversions.popitem()               # entfernt ein beliebiges Paar
del monthConversions["Feb"]
monthConversions.clear()

# Iterieren
for k in monthConversions:               # über Keys
    print(k, monthConversions[k])
for k, v in monthConversions.items():    # Key/Value-Paare
    print(k, v)

# Nützliche Klassenmethoden
new_dict = dict.fromkeys(["a","b","c"], 0)  # {'a':0,'b':0,'c':0}

# setdefault: liefert vorhandenen Wert oder setzt Default
x = monthConversions.setdefault("Aug", "August")

# Dict-Comprehension
full_months = ["January","February","March"]
abbreviation = { m[:3]: m for m in full_months }

# Slicing-Syntax:  seq[start:stop:step]  start = Index, wo Slice beginnt | stop = Index, wo Slice endet | step = Schrittweise, überspringt jeweils step-1 Elemente | - = von hinten
value = "HalloWelt"
print(value[:5]) # "Hallo"
value1 = "0000ff453"
print(value1[4:]) # "ff453"
value2 = "abcdefg"
print(value2[0:7:2]) # "aceg"
value3 = "145 mm"
print(value3[:-3]) # "145"


"""
 ----- Mengen / set() -----
"""
s = {1, 2, 3, 2}    # Duplikate werden automatisch entfernt

# Hinzufügen / Entfernen
s.add(4)
s.update([4,5,6])   # wie extend für Listen
s.discard(10)       # kein Fehler, wenn nicht da
s.remove(3)         # KeyError, wenn nicht da

# Mengenoperationen
a = {1,2,3}
b = {3,4,5}
print(a | b)        # Vereinigung
print(a & b)        # Schnittmenge
print(a - b)        # Differenz
print(a ^ b)        # symmetrische Differenz

# Abfragen
exists = 2 in s
s.clear()




"""
 ----- Zweidimensionale Listen & Matrix-Zugriff -----
"""
grid = [
    [1,2,3],
    [4,5,6],
    [7,8,9],
]

# Zugriff
print(grid[1][2])   # Zeile 1, Spalte 2 → 6

# Flaches Kopieren einer 2D-Liste
flat = [val for row in grid for val in row]

# Matrix-Operationen (z.B. Transponieren)
transpose = list(zip(*grid))  # [(1,4,7),(2,5,8),(3,6,9)]




"""
 ----- Beispiele: JSON-Dateien -----
"""
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

vereinheitlicht_list.append({"name": infos.get("Name")})
print(f"Vereinheitlicht [List]: {vereinheitlicht_list}")

vereinheitlicht_dict = {eintrag["name"]: eintrag for eintrag in vereinheitlicht_list}    #eintrag["name"] = Key (z.B.: Anna)  & eintrag selbst ist das Value (z.B.: {'name': 'Anna'})
print(f"Vereinheitlicht [Dict]: {vereinheitlicht_dict}")