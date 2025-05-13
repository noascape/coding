from concurrent.futures import ThreadPoolExecutor, as_completed     # es gibt auch den ProcessPoolExecutor (Prozesse statt Threads), dieser nutzt wirklich mehrere Kerne simultan für Berechnungen
import threading
from typing import Any, Tuple

import requests
#import asyncio      # Voll asynchron, ohne Thread-Overhead  (z.B.: aiohttp)
import time
import os

##--------------------------------------------------------------------------------------------Inforamtionen:---------------------------------------------------------------------------------##
# Mehrere Threads mit parallelem Scheduling, Gleichzeitigkeit durch echte Thread-Ausführung (limitiert durch GIL), Parallelität bei I/O bei CPU aber wegen GIl eingeschränkt, höherer Ressourcenbrauch (mehr Kontextwechsel, mehr RAM)
# geeignet für blockierende I/O- oder einfach gleichzeitige Tasks, ein paar Hundert Threads möglich (Skalierbarkeit), bei wenigen Tasks einfache Komplexität, Fehleranfällig bei Race Conditions und Deadlocks
# Bsp.: viele kurze API-Anfragen mit requests (synchron), wenn asynchrone Bibliothek wie aiohttp genutzt wird, Ziel: maximale Kompatibilität
# max_workers=?  CPU-lastig: os.cpu_count() (+1) [eher weniger]    - I/O-lastig: 10-100  --> allgemein aber Testen

def arbeite(name):
    print(f"{name} startet")
    time.sleep(2)
    print(f"{name} beendet")
    return 1

# Threads starten
t1 = threading.Thread(target=arbeite, args=("Thread 1",))
t2 = threading.Thread(target=arbeite, args=("Thread 2",))

t1.start()
t2.start()

# Warten bis beide fertig sind
t1.join()
t2.join()

print("Alle Threads beendet.")




##----------------------------ThreadPoolExecutor-----------------------------##

def worker_function(number: int) -> int:
    print(f"Calculating the result for number {number}")
    time.sleep(2)
    return number ** 2


pool = ThreadPoolExecutor(max_workers=3)

work1 = pool.submit(worker_function, 1)                 #ist jetzt ein asynchrones Objekt (man kann immer mehr Obejekte zum Pool hinzufügen)
work2 = pool.submit(worker_function, 2)
work3 = pool.submit(worker_function, 3)

# nach diesen sumit-Zeilen wird der weitere Code direkt weiter ausgeführt
print("Es geht direkt weiter")
print(work3.result())            # .result() wartet darauf, dass die Aufgabe abgeschlossen ist (blockiert solange) und macht dann weiter
print(work3.done())              # gibt mit einem Boolean aus, ob die Aufgabe bereits abgeschlossen ist, oder noch nicht



if work3.done():
    print(work3.result())
else: 
    print("No Results yet")

time.sleep(2)

if work3.done():
    print(work3.result())


pool.shutdown()           # alles was aktuell noch läuft wird noch fertiggestellt, aber es können keine neuen Aufgaben in den Pool aufgenommen werden

cores = os.cpu_count()
print(f"Maximal verfügbare Prozessor-Treads: {cores}")         # es können natürlich mehr Threads gestartet werden, aber es können nur maximal 32 simultan ausgeführt werden



##------------------API (requests + ThreadPoolExecutor + as_completed )----------------------##

URLS = [
    "https://api.example.com/endpoint1"
    "https://api.example.com/enpoint2"
]

def fetch_url(url: str) -> Tuple[str, Any]:
    try:
        response = requests.get(url, timeout=5)
        response.raise_for_status()
        return url, response.json()
    except Exception as e:
        return url, e



def main():
    with ThreadPoolExecutor() as executor:
        futures = [executor.submit(fetch_url, url) for url in URLS]
        for future in as_completed(futures):                          # as_completed(iterable of futures) gibt einen Iterator zurück, der jedes Future sofort liefert, sobald es fertig ist (Ergebnisreihenfolge != Eingabereihenfolge!)
            url, result = future.result()
            if isinstance(result, Exception):
                print(f"Fehler bei {url}: {result}")
            else:
                print(f"{url} -> {result}")


if __name__ == "__main__":
    main()