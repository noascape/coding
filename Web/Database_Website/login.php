<?php 
    require("connection.php");
                                                    #  Funktion isset() überprüft, ob Variable existiert und ob sie nicht null ist. Wenn beides zutrifft wird true zurückgegeben
    if(isset($_POST["submit"])) {                   #  $_POST ist ein globales Array, das alle Daten erhält, die mit der Methode Post an diese Datei gesandt wurden. Hier wird überprüft, ob das Formular mit dem name-Tag "submit" abgeschickt wurde

        $username = $_POST["username"];                                 #hier ist alles was eingegeben wurde
        $password = $_POST["password"];

        $stmt = $con->prepare("SELECT * FROM users WHERE username=:username");         #alle Zeilen eines gleichen username´s werden aus der DB geholt
        $stmt->bindParam(":username", $username);
        $stmt->execute();
        $userExists = $stmt->fetchAll();
        var_dump($userExists);

        $passwordHashed = $userExists[0]["password"];                  #unkenntlich gemachtes Passwort wird wieder entschlüsselt und in passwordHahsed gespeichert
        $checkPassword = password_verify($password, $passwordHashed);  #Abgleich der Passwörter: Je nachdem wird True oder False zurückgegeben

        if($checkPassword === false) {
            echo "Login fehlgeschlagen, Passwort stimmt nicht überein";
        }
        if($checkPassword === true) {
            session_start();
            $_SESSION["username"] = $userExists[0]["username"];       #username ist in der SESSION-Variable global gespeichert

            header("Location: homepage.php");
        }
    }
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <form action="login.php" method="POST">             <!-- wichtig darauf achten, dass hier die richtige Empfangs-Datei ausgewählt wurde -->
        <h1>Login</h1>
        <div class="inputs_container">    
            <input type="text" placeholder="Benutzername" name="username" autocomplete="off">
            <input type="password" placeholder="Passwort" name="password" autocomplete="off">
        </div>
        <button name="submit">Login</button>
    </form>  
</body>
</html>