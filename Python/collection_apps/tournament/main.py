from PySide6.QtWidgets import (
    QApplication, QWidget, QVBoxLayout, QLabel, QPushButton, QComboBox,
    QMessageBox, QTableWidget, QTableWidgetItem
)
from datetime import datetime
from tournament import (
    load_teams, build_initial_tournament, process_today,
    record_result, get_group_table
)
from persistence import load_tournament, save_tournament


class TournamentApp(QWidget):
    def __init__(self):
        super().__init__()
        self.setWindowTitle("Turniermanager")
        self.resize(500, 500)
        self.layout = QVBoxLayout()
        self.setLayout(self.layout)

        self.tournament = load_tournament()
        if not self.tournament:
            teams = load_teams()
            self.tournament = build_initial_tournament(teams)
            save_tournament(self.tournament)

        self.today_match = process_today(self.tournament)
        self.group_id = (
            self.today_match["group"] if self.today_match else None
        )

        self.init_ui()

    def init_ui(self):
        self.layout.addWidget(QLabel(f"Heute: {datetime.today().strftime('%d.%m.%Y')}"))

        if not self.today_match:
            self.layout.addWidget(QLabel("Kein Spiel deines Teams heute oder bereits abgeschlossen."))
            return

        a, b = self.today_match["team1"], self.today_match["team2"]
        label = QLabel(f"{a} vs {b}")
        self.layout.addWidget(label)

        self.winner_combo = QComboBox()
        self.winner_combo.addItems([a, b])
        self.layout.addWidget(self.winner_combo)

        self.button = QPushButton("Ergebnis eintragen & simulieren")
        self.button.clicked.connect(self.process_day)
        self.layout.addWidget(self.button)

        self.table = QTableWidget()
        self.layout.addWidget(QLabel("Aktuelle Tabelle"))
        self.layout.addWidget(self.table)
        self.update_table()

    def process_day(self):
        winner = self.winner_combo.currentText()
        a = self.today_match["team1"]
        b = self.today_match["team2"]
        record_result(self.tournament, a, b, winner)

        # simuliere andere Spiele des heutigen Tages
        process_today(self.tournament)

        save_tournament(self.tournament)
        QMessageBox.information(self, "Fertig", "Ergebnisse gespeichert & Tag abgeschlossen.")
        self.close()

    def update_table(self):
        if not self.group_id:
            return

        data = get_group_table(self.tournament, self.group_id)
        self.table.setRowCount(len(data))
        self.table.setColumnCount(4)
        self.table.setHorizontalHeaderLabels(["Team", "Spiele", "Siege", "Punkte"])

        for i, (team, stats) in enumerate(data):
            self.table.setItem(i, 0, QTableWidgetItem(team))
            self.table.setItem(i, 1, QTableWidgetItem(str(stats["played"])))
            self.table.setItem(i, 2, QTableWidgetItem(str(stats["won"])))
            self.table.setItem(i, 3, QTableWidgetItem(str(stats["points"])))


if __name__ == "__main__":
    import sys
    app = QApplication(sys.argv)
    window = TournamentApp()
    window.show()
    sys.exit(app.exec())