<?php
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: index.html");
    exit();
}

function load_hours() {
    $json = file_get_contents('hours.json');
    return json_decode($json, true);
}

$hours = load_hours();
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="icon" href="favicon.png">
    <link rel="stylesheet" href="styles.css">
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
        <h3>Öffnungszeiten für diese Woche:</h3>
        <ul>
            <li>Montag: <?php echo $hours['Montag']; ?></li>
            <li>Dienstag: <?php echo $hours['Dienstag']; ?></li>
            <li>Mittwoch: <?php echo $hours['Mittwoch']; ?></li>
            <li>Donnerstag: <?php echo $hours['Donnerstag']; ?></li>
            <li>Freitag: <?php echo $hours['Freitag']; ?></li>
        </ul>
        <p id="closure-notice">Kein Ausfall gemeldet.</p>
    </main>
</body>
</html>