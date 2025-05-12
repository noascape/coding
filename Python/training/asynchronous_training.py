import asyncio
from concurrent.futures import ThreadPoolExecutor
import requests

##--------------------------------------------------------------------------------------------Inforamtionen:---------------------------------------------------------------------------------##
# Ein Thread mit Event Loop, Gleichzeitigkeit durch Task-Scheduling, nicht parallel, aber effizient durch nicht blockieren, geringer Ressourcenverbrauch (nur ein Thread, leichter Kontextwechsel)
# geeignet für viele kleine I/O-bound Tasks, Tausende Tasks durch Event Loop möglich (Skalierung), Code komplexer durch async/await aber skalierbarer, Fehleranfällig bei await aber deterministisch




##-----------------------------Mehrere Tasks gleichzeitig------------------------------------------##
async def task(name, seconds):
    print(f"{name} startet")
    await asyncio.sleep(seconds)
    print(f"{name} endet nach {seconds}s")


async def main():
    t1= asyncio.create_task(task("Task A", 2))         # beide Aufgaben laufen parallel
    t2 = asyncio.create_task(task("Task B", 3))        # startet Funktion: task(...) sofort im Hintergrund (nicht blockierend)
    await t1                    # der Code danach wird erst ausgeführt, wenn t1 abgeschlossen ist
    await t2
    await asyncio.gather(t1, t2) # wenn man direkt auf beide wartet und nicht einzeln unterscheidet

asyncio.run(main())


##-----------------------------------+ ThreadPoolExecutor----------------------------------------------##

# Normale (blockierende) API-Anfrage mit requests
def fetch_url(url):
    print(f"Starte Abruf von: {url}")
    response = requests.get(url)
    print(f"Beendet Abruf von: {url} mit Status {response.status_code}")
    return response.status_code

# Async-Wrapper um die synchrone Funktion
async def async_fetch(url, executor):
    loop = asyncio.get_running_loop()
    result = await loop.run_in_executor(executor, fetch_url, url)
    return result

# Main-Eventloop
async def main2():
    urls = [
        "https://httpbin.org/delay/2",
        "https://httpbin.org/delay/3",
        "https://httpbin.org/delay/1",
    ]
    with ThreadPoolExecutor(max_workers=3) as executor:
        tasks = [async_fetch(url, executor) for url in urls]
        results = await asyncio.gather(*tasks)
        print(f"Statuscodes: {results}")

# Start
#asyncio.run(main2())
