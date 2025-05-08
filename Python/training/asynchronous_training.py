import asyncio
from concurrent.futures import ThreadPoolExecutor
import requests
import time

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
async def main():
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
asyncio.run(main())
