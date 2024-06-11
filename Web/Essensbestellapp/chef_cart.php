<?php
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: index.html");
    exit();
}

$ordersFile = 'orders.json';
$carts = file_exists($ordersFile) ? json_decode(file_get_contents($ordersFile), true) : [];
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Bestellungen</title>
    <link rel="icon" href="favicon.png">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"> 
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
        <h2>Hier sind alle Bestellungen:</h2>
        <?php if (empty($carts)): ?>
            <p>Der Warenkorb ist leer.</p>
        <?php else: ?>
            <?php foreach ($carts as $username => $cart): ?>
                <h3><?php echo htmlspecialchars($username); ?>'s Warenkorb:</h3>
                <ul>
                    <?php foreach ($cart as $index => $item): ?>
                        <li>
                            <span>
                                <strong><?php echo htmlspecialchars($item['tag'] . ' ' . $item['datum']); ?></strong><br>
                                <?php echo htmlspecialchars($item['art']); ?>: <?php echo htmlspecialchars($item['beschreibung']); ?><br>
                                Preis: <?php echo htmlspecialchars($item['preis']); ?>
                            </span>
                        </li>
                    <?php endforeach; ?>
                </ul>
            <?php endforeach; ?>
        <?php endif; ?>
    </main>
</body>
</html>
