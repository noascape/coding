from concurrent.futures import ThreadPoolExecutor
import time
import random

list_data = [number for number in range(10)]


def consumer(name):
    while True:
        try:
            value = list_data.pop(0)
            time.sleep(random.random() * 0.5)
            print(f"{name} verbraucht {value}")
        except IndexError:
            break


with ThreadPoolExecutor(max_workers=3) as executor:
    for thread_number in range(3):
        executor.submit(consumer, f"Thread-{thread_number}")