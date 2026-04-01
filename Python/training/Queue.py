from concurrent.futures import ThreadPoolExecutor
import time
import random

queue_data = [i for i in range(10)]

def consumer(name):
    for _ in range(6):
        if queue_data:  # nicht atomar
            time.sleep(random.random() * 0.01)
            try:
                value = queue_data.pop(0)
                print(f"{name} verbraucht {value}")
            except IndexError:
                print(f"{name} ERROR: Queue leer!")

with ThreadPoolExecutor(max_workers=3) as executor:
    for i in range(3):
        executor.submit(consumer, f"C{i}")