<?php
    require("connection.php");
    session_start();
    echo $_SESSION["username"];

    if(isset($_GET["submit"])) {
        global $con;

        $product = $_GET["product"];
        $productnumber = $_GET["productnumber"];
        $amount = $_GET["amount"];
        $color = $_GET["color"];

        $username = $_SESSION["username"]; // Benutzername aus der Session

        echo "Erfolgreich hinzugefügt <br>";

        // Überprüfen, ob die Tabelle existiert
        $check = $con->prepare("SHOW TABLES LIKE ?");
        $check->execute([$username]);
        $tableAlreadyExists = $check->fetchColumn();

        // Tabelle erstellen, falls sie nicht existiert
        if (!$tableAlreadyExists) {
            $stmt = $con->prepare("CREATE TABLE `$username` (
                id INT AUTO_INCREMENT PRIMARY KEY,
                product VARCHAR(255),
                productnumber VARCHAR(255),
                amount INT,
                color VARCHAR(255)
            )");
            $stmt->execute();
        }

        // Produkt in die Tabelle einfügen
        $insert = $con->prepare("INSERT INTO `$username` (product, productnumber, amount, color) VALUES (:product, :productnumber, :amount, :color)");
        $insert->bindParam(":product", $product);
        $insert->bindParam(":productnumber", $productnumber);
        $insert->bindParam(":amount", $amount);
        $insert->bindParam(":color", $color);
        $insert->execute();
    }

    // Einkaufswagen anzeigen
    function showCart() {
            $username = $_SESSION["username"];
            global $con;

            $stmt = $con->prepare("SELECT product AS Produkt, productnumber AS Produktnummer, amount AS Anzahl, color AS Farbe FROM `$username`"); 
            $stmt->execute();   
            return $stmt->fetchAll(PDO::FETCH_ASSOC);          // Alle Ergebnisse als assoziatives Array zurückgeben
        }
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Einkaufswagen</title>
</head>
<body>
    <h1>Das ist der Einkaufswagen</h1>
    
    <form action="cart.php" method="GET">
        <input type="text" name="product" placeholder="Produkt">
        <input type="text" name="productnumber" placeholder="Produktnummer">
        <input type="number" name="amount" placeholder="Anzahl">
        <input type="text" name="color" placeholder="Farbe">
        <input type="submit" name="submit">
    </form>

    <table>
        <tr><th>Produkt</th><th>Produktnummer</th><th>Anzahl</th><th>Farbe</th></tr>
        
        <?php
            global $con;
            $username = $_SESSION["username"];
    
            $check = $con->prepare("SHOW TABLES LIKE ?");
            $check->execute([$username]);
            $tableAlreadyExists = $check->fetchColumn();

            if($tableAlreadyExists) {
                $table = showCart();
                foreach($table as $row) {
                    echo "<tr><td>" . htmlspecialchars($row["Produkt"]) . "</td><td>" . htmlspecialchars($row["Produktnummer"]) . "</td><td>" . htmlspecialchars($row["Anzahl"]). "</td><td>" . htmlspecialchars($row["Farbe"]). "</table>";
                }
            } else {
                echo "</table><br>Der Warenkorb ist aktuell leer";
            }
        ?>
    <form action="homepage.php" method="POST">
        <button name="logout">Abmelden</button>
    </form>
</body>
</html>