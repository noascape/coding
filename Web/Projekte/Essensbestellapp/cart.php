<?php
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: index.html");
    exit();
}

$username = $_SESSION['username'];
$ordersFile = 'orders.json';
$orders = file_exists($ordersFile) ? json_decode(file_get_contents($ordersFile), true) : [];
$userCart = isset($orders[$username]) ? $orders[$username] : [];
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Warenkorb</title>
    <link rel="icon" href="favicon.png">
    <link rel="stylesheet" href="styles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"> 
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
        <h2>Hier ist Ihr Warenkorb:</h2>
        <?php if (empty($userCart)): ?>
            <p>Ihr Warenkorb ist leer.</p>
        <?php else: ?>
            <ul>
                <?php foreach ($userCart as $index => $item): ?>
                    <li>
                        <span>
                            <strong><?php echo htmlspecialchars($item['tag'] . ' ' . $item['datum']); ?></strong><br>
                            <?php echo htmlspecialchars($item['art']); ?>: <?php echo htmlspecialchars($item['beschreibung']); ?><br>
                            Preis: <?php echo htmlspecialchars($item['preis']); ?>
                            <button onclick="removeFromCart(<?php echo $index; ?>)" style="background: none; border: none; cursor: pointer; margin-left: 30px; text-align: left">
                                <i class="fas fa-trash-alt" style="color: #000000;"></i> 
                            </button>
                        </span>
                    </li>
                <?php endforeach; ?>
            </ul>
        <?php endif; ?>
    </main>

    <script>
        function removeFromCart(index) {
            fetch('remove_from_cart.php', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ index: index })
            })
            .then(response => response.json())
            .then(data => {
                if (data.success) {
                    location.reload(); 
                } else {
                    alert('Fehler beim Löschen des Gerichts aus dem Warenkorb.');
                }
            });
        }
    </script>
</body>
</html>



