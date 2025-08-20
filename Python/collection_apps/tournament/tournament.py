import os
import random
from itertools import combinations
from datetime import datetime, timedelta

def load_teams():
    dir_path = os.path.dirname(os.path.realpath(__file__))
    path = os.path.join(dir_path, "teams.txt")
    if not os.path.exists(path):
        raise FileNotFoundError(f"Datei {path} nicht gefunden. Stelle sicher, dass 'teams.txt' im selben Ordner wie tournament.py liegt.")
    with open(path, "r", encoding="utf-8") as f:
        teams = [line.strip() for line in f.readlines() if line.strip()]
    assert len(teams) == 16, "Es müssen genau 16 Teams in teams.txt stehen"
    return teams

def create_groups(teams):
    return {
        "A": teams[0:4],
        "B": teams[4:8],
        "C": teams[8:12],
        "D": teams[12:16]
    }

def generate_schedule(groups, start_date="2025-06-22"):
    schedule = []
    date = datetime.strptime(start_date, "%Y-%m-%d")
    for group_id, teams in groups.items():
        for team1, team2 in combinations(teams, 2):
            schedule.append({
                "date": date.strftime("%Y-%m-%d"),
                "team1": team1,
                "team2": team2,
                "group": group_id,
                "result": None
            })
            date += timedelta(days=1)
    return schedule

def build_initial_tournament(teams):
    groups = create_groups(teams)
    schedule = generate_schedule(groups)
    my_team = teams[0]
    return {
        "teams": teams,
        "my_team": my_team,
        "groups": groups,
        "schedule": schedule,
        "results": []
    }

def simulate_match(match):
    return random.choice([match["team1"], match["team2"]])

def process_today(tournament):
    today = datetime.today().strftime("%Y-%m-%d")
    for match in tournament["schedule"]:
        if match["date"] == today and match["result"] is None:
            if tournament["my_team"] in (match["team1"], match["team2"]):
                return match  # warte auf manuelles Ergebnis
            else:
                match["result"] = simulate_match(match)
    return None

def record_result(tournament, team1, team2, winner):
    today = datetime.today().strftime("%Y-%m-%d")
    for match in tournament["schedule"]:
        if match["team1"] == team1 and match["team2"] == team2 and match["date"] == today:
            match["result"] = winner
            return

def get_group_table(tournament, group_id):
    group_teams = tournament["groups"][group_id]
    table = {team: {"played": 0, "won": 0, "points": 0} for team in group_teams}
    for match in tournament["schedule"]:
        if match["group"] != group_id or match["result"] is None:
            continue
        team1, team2, winner = match["team1"], match["team2"], match["result"]
        table[team1]["played"] += 1
        table[team2]["played"] += 1
        table[winner]["won"] += 1
        table[winner]["points"] += 3
    sorted_table = sorted(table.items(), key=lambda x: (-x[1]["points"], -x[1]["won"]))
    return sorted_table
