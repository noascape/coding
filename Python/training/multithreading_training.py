from concurrent.futures import ThreadPoolExecutor, as_completed     # es gibt auch den ProcessPoolExecutor (Prozesse statt Threads), dieser nutzt wirklich mehrere Kerne simultan für Berechnungen
import threading
import requests
import asyncio      # Voll asynchron, ohne Thread-Overhead
import aiohttp
import time
import os


def arbeite(name):
    #print(f"{name} startet")
    #time.sleep(2)
    #print(f"{name} beendet")
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

def worker_function(number): 
    print(f"Calculating the result for number {number}")
    time.sleep(2)
    return number ** 2


pool = ThreadPoolExecutor(max_workers=3)

work1 = pool.submit(worker_function, 1)                 #ist jetzt ein asynchrones Objekt (man kann immer mehr Obejekte zum Pool hinzufügen)
work2 = pool.submit(worker_function, 2)
work3 = pool.submit(worker_function, 3)

# nach diesen sumit-Zeilen wird der weitere Code direkt weiter ausgeführt
print("Es geht direkt weiter")
#print(work3.result())            # wartet darauf, dass die Aufgabe abgeschlossen ist und macht dann weiter (um dieses Warten zu umgehen, kann )
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

async def fetch_url(url):
    async with session.get(url, timeout=5) as resp:
        resp.raise_for_status()
        return await resp.json()


async def main(): 
    async with aiohttp.ClientSession() as session: 
        tasks = [ fetch_url(session, url) for url in URLS ]
        # startet alle gleichzeitig und wartet, bis alle fertig sind
        results = await asyncio.gather(*tasks, return_exceptions=True)
        for url, result in zip(URLS, results): 
            if isinstance(result, Exception):
                print(f"Fehler bei {url}: {result}")
            else: 
                print(f"{url} -> {result}")


if __name__ == "__main__":
    asyncio.run(main())