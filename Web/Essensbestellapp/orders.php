<?php
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: index.html");
    exit();
}

$username = $_SESSION['username'];
$ordersFile = 'orders.json';
$orders = file_exists($ordersFile) ? json_decode(file_get_contents($ordersFile), true) : [];
$userOrders = isset($orders[$username]) ? $orders[$username] : [];
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
                    location.reload(); 
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

        document.addEventListener('DOMContentLoaded', () => {
            document.querySelectorAll('.gericht-block').forEach(block => {
                block.addEventListener('click', handleBlockClick);
                
                const gerichtsBeschreibung = block.querySelector('.gericht-beschreibung').innerText;
                if (gerichtsBeschreibung.includes('Wurde bereits bestellt')) {
                    block.style.backgroundColor = "#4CAF50";
                }
            });
        });
    </script>
</head>
<body>
    <header>
        <h1>Food Ordering Website</h1>
        <nav>
            <ul>
                <li><a href="home.php">Home</a></li>
                <li><a href="orders.php">Gerichte</a></li>
                <li><a href="cart.php">Warenkorb</a></li>
                <li><a href="index.html">Abmelden</a></li>
            </ul>
        </nav>
    </header>
    <main>
        <h2>Willkommen, <?php echo $_SESSION['username']; ?>!</h2>
        <h3>Hier können Sie das Menu der Woche sehen:</h3>
    </main>
</body>

<?php
foreach ($gerichte as $gericht) {
    $gerichtJson = json_encode($gericht);
    $isOrdered = in_array($gericht, $userOrders);
    echo '<div class="gericht-block" style="background-color:' . ($isOrdered ? '#4CAF50' : '#007BFF') . '">';
    echo '<div class="gericht-header">';
    echo '<span class="gericht-tag">' . htmlspecialchars($gericht['tag']) . '</span>';
    echo '<span class="gericht-datum">' . htmlspecialchars($gericht['datum']) . '</span>';
    echo '</div>';
    echo '<div class="gericht-art">' . htmlspecialchars($gericht['art']) . '</div>';
    echo '<div class="gericht-beschreibung">' . htmlspecialchars($gericht['beschreibung']) . '</div>';
    echo '<div class="gericht-preis">' . htmlspecialchars($gericht['preis']) . '</div>';
    echo '</div>';
}
?>
</html>