const http = require('http');
const express = require("express");
const sqlite3 = require("sqlite3").verbose();
const fs = require("fs");

// Konfigurationsdatei einlesen
const config = JSON.parse(fs.readFileSync("./config.json", "utf8"));
const dbPath = config.dbPath;

// Express-App erstellen
const app = express();
const PORT = 3000;

// Middleware & View Engine
app.set("view engine", "ejs"); // EJS als Template-Engine
app.use(express.static("public")); // Statische Dateien
app.use(bodyParser.urlencoded({ extended: true })); // Body-Parser für Formulardaten

// Verbindung zur SQLite-Datenbank
const db = new sqlite3.Database(dbPath, (err) => {
    if (err) {
        console.error("Datenbankfehler:", err.message);
    } else {
        console.log("Verbunden mit SQLite-Datenbank:", dbPath);
    }
});

// Warenkorb-Seite anzeigen
app.get("/cart", (req, res) => {
    db.all("SELECT * FROM Warenkorb", [], (err, rows) => {
        if (err) {
            console.error(err.message);
            res.status(500).send("Fehler beim Abrufen der Daten");
            return;
        }

        // Gesamtpreis berechnen
        const total = rows.reduce((sum, item) => sum + item.Price * item.Amount, 0);

        res.render("shoppingcart", { title: "Dein Warenkorb", items: rows, total: total });
    });
});

// Produkt zum Warenkorb hinzufügen
app.post("/cart/add", (req, res) => {
    const { product_name, amount, price } = req.body;
    db.run(
        "INSERT INTO Warenkorb (ProductName, Amount, Price) VALUES (?, ?, ?)",
        [product_name, amount, price],
        (err) => {
            if (err) {
                console.error(err.message);
            }
            res.redirect("/cart");
        }
    );
});

// Produkt aus Warenkorb löschen
app.post("/cart/delete/:id", (req, res) => {
    const id = req.params.id;
    db.run("DELETE FROM Warenkorb WHERE ID = ?", [id], (err) => {
        if (err) {
            console.error(err.message);
        }
        res.redirect("/cart");
    });
});


const server = http.createServer((req, res) => {
    res.writeHead(200, { 'Content-Type': 'text/plain' });
    res.end('Hello, Node.js!');
});
    
server.listen(3000, () => {
    console.log('Server läuft auf http://localhost:3000');
});
