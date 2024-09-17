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

function save_hours($hours) {
    $json = json_encode($hours, JSON_PRETTY_PRINT);
    file_put_contents('hours.json', $json);
}

$hours = load_hours();

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $hours['Montag'] = $_POST['Montag'];
    $hours['Dienstag'] = $_POST['Dienstag'];
    $hours['Mittwoch'] = $_POST['Mittwoch'];
    $hours['Donnerstag'] = $_POST['Donnerstag'];
    $hours['Freitag'] = $_POST['Freitag'];

    save_hours($hours);

    header("Location: chef_dashboard.php");
    exit();
}
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
        <h3>Öffnungszeiten für diese Woche:</h3>
        <form method="post">
            <label for="Montag">Montag:</label>
            <input type="text" id="Montag" name="Montag" value="<?php echo $hours['Montag']; ?>"><br>
            <label for="Dienstag">Dienstag:</label>
            <input type="text" id="Dienstag" name="Dienstag" value="<?php echo $hours['Dienstag']; ?>"><br>
            <label for="Mittwoch">Mittwoch:</label>
            <input type="text" id="Mittwoch" name="Mittwoch" value="<?php echo $hours['Mittwoch']; ?>"><br>
            <label for="Donnerstag">Donnerstag:</label>
            <input type="text" id="Donnerstag" name="Donnerstag" value="<?php echo $hours['Donnerstag']; ?>"><br>
            <label for="Freitag">Freitag:</label>
            <input type="text" id="Freitag" name="Freitag" value="<?php echo $hours['Freitag']; ?>"><br>
            <input type="submit" value="Speichern">
        </form>
    </main>
</body>
</html>