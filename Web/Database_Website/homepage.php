<?php
    require("connection.php");
    session_start();

    function showUsers() {
        global $con;
        $stmt = $con->prepare("SELECT username AS Benutzername , email AS Email FROM users"); 
        $stmt->execute();   
        return $stmt->fetchAll(PDO::FETCH_ASSOC);          // Alle Ergebnisse als assoziatives Array zurückgeben
    }


    if(isset($_POST["logout"])) {  
        session_destroy();                                  //beendet die Session
        header("Location: index.php");
        exit();
    }
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
</head>
<body>
    <h1>Willkommen  
        <?php 
            echo $_SESSION["username"];      #mit der Session kann nun der global gespeicherte username in $_SESSION mit echo ausgegeben werden
        ?> 
        auf der Homepage</h1>
        <h2>Benutzerliste:</h2>
    <table>
        <tr>
            <th>Benutzername</th>
            <th>E-Mail</th>
        </tr>
        <?php
            $users = showUsers();                              // Datenbankdaten abrufen
            foreach ($users as $user) {                        // iteriert durch die Einträge durch und speichert sie jeweils in $user ab
                echo "<tr><td>" . htmlspecialchars($user['Benutzername']) . "</td><td>" . htmlspecialchars($user['Email']) . "</td></tr>";
                // für jede Zeile wird eine Html-Zeile erstellt  
            }
        ?>
    </table>
        <a href="cart.php">Einkaufen</a>

    <form action="homepage.php" method="POST">
        <button name="logout">Abmelden</button>
    </form>
</body>
</html>