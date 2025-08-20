import json
from pathlib import Path

SAVE_PATH = Path("tournament.json")

def save_tournament(data):
    with open(SAVE_PATH, "w") as f:
        json.dump(data, f, indent=4)

def load_tournament():
    if SAVE_PATH.exists():
        with open(SAVE_PATH, "r") as f:
            return json.load(f)
    return None
