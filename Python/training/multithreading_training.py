import threading
import time

def arbeite(name):
    print(f"{name} startet")
    time.sleep(2)
    print(f"{name} beendet")

# Threads starten
t1 = threading.Thread(target=arbeite, args=("Thread 1",))
t2 = threading.Thread(target=arbeite, args=("Thread 2",))

t1.start()
t2.start()

# Warten bis beide fertig sind
t1.join()
t2.join()

print("Alle Threads beendet.")
