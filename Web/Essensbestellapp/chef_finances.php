<?php
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: index.html");
    exit();
}

$umsatz_heute = 150;
$umsatz_woche = 980;
$umsatz_monat = 4200;

$bestellungen = [
    ['id' => 1, 'datum' => '2024-06-10', 'artikel' => 'Lachs-Burger', 'preis' => 8.50],
    ['id' => 2, 'datum' => '2024-06-09', 'artikel' => 'Tomatensuppe', 'preis' => 4.20],
    ['id' => 3, 'datum' => '2024-06-08', 'artikel' => 'Schnitzel mit Pommes', 'preis' => 9.80],

];

?>
<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Finanzen</title>
    <link rel="icon" href="favicon.png">
    <link rel="stylesheet" href="styles.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
        <h2>Hey, <?php echo $_SESSION['username']; ?>!</h2>
        <h3>So sehen unsere Finanzen momentan aus:</h3>

        <section>
            <h4>Umsatzübersicht</h4>
            <ul>
                <li>Heute: <?php echo $umsatz_heute; ?>€</li>
                <li>Diese Woche: <?php echo $umsatz_woche; ?>€</li>
                <li>Dieser Monat: <?php echo $umsatz_monat; ?>€</li>
            </ul>
        </section>

        <section>
            <h4>Bestellhistorie</h4>
            <table>
                <tr>
                    <th>Bestellnummer</th>
                    <th>Datum</th>
                    <th>Artikel</th>
                    <th>Preis</th>
                </tr>
                <?php foreach ($bestellungen as $bestellung): ?>
                    <tr>
                        <td><?php echo $bestellung['id']; ?></td>
                        <td><?php echo $bestellung['datum']; ?></td>
                        <td><?php echo $bestellung['artikel']; ?></td>
                        <td><?php echo $bestellung['preis']; ?>€</td>
                    </tr>
                <?php endforeach; ?>
            </table>
        </section>
        
        <section>
            <h4>Umsatzdiagramm (letzte 7 Tage)</h4>
            <canvas id="umsatzDiagramm" width="400" height="200"></canvas>
            <script>
                var ctx = document.getElementById('umsatzDiagramm').getContext('2d');
                var myChart = new Chart(ctx, {
                    type: 'line',
                    data: {
                        labels: ['Tag 1', 'Tag 2', 'Tag 3', 'Tag 4', 'Tag 5', 'Tag 6', 'Tag 7'],
                        datasets: [{
                            label: 'Umsatz in €',
                            data: [50, 70, 90, 120, 80, 110, 150],
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                ticks: {
                                    beginAtZero: true
                                }
                            }]
                        }
                    }
                });
            </script>
        </section>
    </main>
</body>
</html>
