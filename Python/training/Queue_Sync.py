from concurrent.futures import ThreadPoolExecutor
import queue
import time
import random

queue_data = queue.Queue()

for i in range(10):
    queue_data.put(i)



def consumer(name):
    for _ in range(6):
        try:
            value = queue_data.get(timeout=0.1)  
            print(f"{name} verbraucht {value}")
            time.sleep(random.random() * 0.01)
        except queue.Empty:
            print(f"{name}: nichts mehr zu holen")



with ThreadPoolExecutor(max_workers=3) as executor:
    for i in range(3):
        executor.submit(consumer, f"C{i}")