from concurrent.futures import ThreadPoolExecutor
import queue
import time
import random

queue_data = queue.Queue()

for number in range(10):
    queue_data.put(number)


def consumer(name):
    while not queue_data.empty():
        value = queue_data.get()
        print(f"{name} verbraucht {value}")
        time.sleep(random.random() * 0.5)



with ThreadPoolExecutor(max_workers=3) as executor:
    for thread_number in range(3):
        executor.submit(consumer, f"C{thread_number}")