<?php
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: index.html");
    exit();
}
$gerichte = json_decode(file_get_contents('dishes.json'), true);
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Gerichte</title>
    <link rel="icon" href="favicon.png">
    <link rel="stylesheet" href="styles.css">
    <script>
        function addToCart(gericht) {
            fetch('add_to_cart.php', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(gericht)
            })
            .then(response => response.json())
            .then(data => {
                if(data.success) {
                    alert('Gericht wurde zum Warenkorb hinzugefügt.');
                    gericht.currentTarget.style.backgroundColor = "#4CAF50";
                } else {
                    alert('Fehler beim Hinzufügen des Gerichts zum Warenkorb.');
                }
            });
        }

        function handleBlockClick(event) {
            const gericht = {
                tag: event.currentTarget.querySelector('.gericht-tag').innerText,
                datum: event.currentTarget.querySelector('.gericht-datum').innerText,
                art: event.currentTarget.querySelector('.gericht-art').innerText,
                beschreibung: event.currentTarget.querySelector('.gericht-beschreibung').innerText,
                preis: event.currentTarget.querySelector('.gericht-preis').innerText
            };
            addToCart(gericht);
        }

        function showAddDishForm() {
            document.getElementById('addDishForm').style.display = 'block';
        }

        function closeAddDishForm() {
            document.getElementById('addDishForm').style.display = 'none';
        }

    </script>
</head>
<body>
    <header>
        <h1>Food Ordering Website</h1>
        <nav>
            <ul>
                <li><a href="chef_dashboard.php">Home</a></li>
                <li><a href="chef_orders.php">Gerichte</a></li>
                <li><a href="chef_cart.php">Bestellungen</a></li>
                <li><a href="chef_finances.php">Finanzen</a></li>
                <li><a href="index.html">Abmelden</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h2>Willkommen, <?php echo $_SESSION['username']; ?>!</h2>
        <h3>Hier können Sie das Menü der Woche sehen:</h3>
        <button onclick="showAddDishForm()" style="margin-bottom: 50px; color: #000000; background-color: #fff; border: 1px solid #808080">Neues Gericht hinzufügen</button>
    </main>

    <div id="addDishForm" style="display: none;">
        <form action="add_dish.php" method="post">
            <label for="tag">Tag:</label>
            <input type="text" id="tag" name="tag" required><br>
            <label for="datum">Datum:</label>
            <input type="text" id="datum" name="datum" required><br>
            <label for="art">Art:</label>
            <input type="text" id="art" name="art" required><br>
            <label for="beschreibung">Beschreibung:</label>
            <input type="text" id="beschreibung" name="beschreibung" required><br>
            <label for="preis">Preis:</label>
            <input type="text" id="preis" name="preis" required><br>
            <button type="submit" style="color: #000000; background-color: #fff; border: 1px solid #808080">Gericht hinzufügen</button>
            <button type="button" onclick="closeAddDishForm()" style="margin-bottom: 50px; color: #000000; background-color: #fff; border: 1px solid #808080">Abbrechen</button>
        </form>
    </div>
</body>

<?php
foreach ($gerichte as $gericht) {
    echo '<div class="gericht-block">';
    echo '<div class="gericht-header">';
    echo '<span class="gericht-tag">' . $gericht['tag'] . '</span>';
    echo '<span class="gericht-datum">' . $gericht['datum'] . '</span>';
    echo '</div>';
    echo '<div class="gericht-art">' . $gericht['art'] . '</div>';
    echo '<div class="gericht-beschreibung">' . $gericht['beschreibung'] . '</div>';
    echo '<div class="gericht-preis">' . $gericht['preis'] . '</div>';
    echo '</div>';
}
?>
</html>

