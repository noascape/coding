import os
import tempfile
from pathlib import Path


'''
---------- Relative Pfade -----------
'''
print(f"Aktuelles Arbeitsverzeichnis: {os.getcwd()}")               #liefert das Verzeichnis, von dem aus das Skript aufgerufen wurde
base_dir = os.path.dirname(os.path.abspath(__file__))               #Ordner, unabängig vom Arbeitsverzeichnis

relative_path0 = os.path.join(base_dir, "data", "input.txt")
relative_path1 = base_dir / "data" / "input.txt"
print(relative_path0, relative_path1)



'''
---------- Absolute Pfade -----------
'''
absolute_path_lin = "/home/benutzer/projekt/datei.txt"              #Linux/MacOs
absolute_path_win = "C:\\Users\\Benutzer\\projekte\\datei.txt"      #Windows

# Aus einem relativen Pfad einen absoluten Pfad erstellen
absolute_path0 = os.path.abspath("unterordner/datei.txt")           #Absolute Pfade uterscheiden sich je nach Betriebssystem. Am besten mit os.path.join / pathlib arbeiten --> Trennzeichen-Portabilität sicherstellen




'''
---------- Wichtige Funktionen -----------
'''
other_dir = "C:\\Home"
os.chdir(other_dir)                         #Arbeitsverzeichnis ändern
home_dir0 = os.path.expanduser("~")         #Home-Verzeichnis des Users
home_dir1 = Path.home()                     #Home-Verzeichnis des Users
temp_dir = tempfile.gettempdir()            #Temporäre Verzeichnisse


#path.is_file()                             #nochmal prüfen
#path.exists()
