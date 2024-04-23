document.addEventListener("DOMContentLoaded", function () {
    const board = document.getElementById("game-board");
    const cells = [];

    // Erstelle das Spielfeld
    for (let i = 0; i < 9; i++) {
        const cell = document.createElement("div");
        cell.className = "cell";
        cell.dataset.index = i;
        cell.addEventListener("click", handleCellClick);
        cells.push(cell);
        board.appendChild(cell);
    }

    // Aktueller Spieler
    let currentPlayer = "X";

    function handleCellClick(event) {
        const cell = event.target;
        const index = cell.dataset.index;

        // Überprüfe, ob das Feld bereits belegt ist
        if (!cell.textContent) {
            cell.textContent = currentPlayer;

            // Überprüfe auf Gewinner
            if (checkWinner()) {
                alert(`Spieler ${currentPlayer} gewinnt!`);
                resetGame();
            } else if ([...cells].every(cell => cell.textContent)) {
                alert("Unentschieden!");
                resetGame();
            } else {
                // Wechsle den Spieler
                currentPlayer = currentPlayer === "X" ? "O" : "X";
            }
        }
    }

    function checkWinner() {
        // Überprüfung von Zeilen, Spalten und Diagonalen
        const winningCombinations = [
            [0, 1, 2], [3, 4, 5], [6, 7, 8], // Zeilen
            [0, 3, 6], [1, 4, 7], [2, 5, 8], // Spalten
            [0, 4, 8], [2, 4, 6]             // Diagonalen
        ];

        for (const combination of winningCombinations) {
            const [a, b, c] = combination;
            if (cells[a].textContent && cells[a].textContent === cells[b].textContent && cells[a].textContent === cells[c].textContent) {
                return true;
            }
        }

        return false;
    }

    function resetGame() {
        // Setze alle Zellen zurück
        cells.forEach(cell => cell.textContent = "");
        currentPlayer = "X";
    }
});
