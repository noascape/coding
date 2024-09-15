<!-- 
     Apache und SQL in XAMPP starten, Datei in htdocs
     index.html -> index.php
     unter XAMPP - SQL - Admin eine neue Datenbank anlegen "CREATE DATABASE userdb" darin ein Tabelle anlegen-> "CREATE TABLE users(id int AUTO_INCREMENT PRIMARY KEY, username varchar(255), email varchar(255), password varchar(255))"
-->
<?php
    require("connection.php");              #connection.php importieren
                                            #  Funktion isset() überprüft, ob Variable existiert und ob sie nicht null ist. Wenn beides zutrifft wird true zurückgegeben
    if(isset($_POST["submit"])) {           #  $_POST ist ein globales Array, das alle Daten erhält, die mit der Methode Post an diese Datei gesandt wurden. Hier wird überprüft, ob das Formular mit dem name-Tag "submit" abgeschickt wurde
        var_dump($_POST);

        $username = $_POST["username"];     #Variablen username, email und password bekommen ihre Werte unten bei der Benutzereingabe (jeweils gekennzeichnet durch name="")
        $email = $_POST["email"];
        $password = PASSWORD_HASH($_POST["password"], PASSWORD_DEFAULT);       #verschlüsselt das pw in der db

        $stmt = $con->prepare("SELECT * FROM users WHERE username=:username OR email=:email");           #SQL-Befehl mit prepare vorbereiten, um ihn später mit execute auszuführen       ":username" ist ein Platzhalter, welcher mit bindParam ersetzt werden kann
        $stmt->bindParam(":username", $username);                  #users muss wie in der db geschrieben werden
        $stmt->bindParam(":email", $email);
        $stmt->execute();

        $userAlreadyExists = $stmt->fetchColumn();               #um die Anzahl der gleichen Benutzer zu zählen, bei 0 = false  

        if(!$userAlreadyExists) {
            //Registrieren
            registerUser($username, $email, $password);
        }
        else{
            //User existiert bereits
        }
    }

    function registerUser($username, $email, $password){
        global $con;
        $stmt = $con->prepare("INSERT INTO users(username, email, password) VALUES(:username, :email, :password)");
        $stmt->bindParam(":username", $username);                  #users muss wie in der db geschrieben werden
        $stmt->bindParam(":email", $email);
        $stmt->bindParam(":password", $password);                  
        $stmt->execute();
        header("Location: homepage.php");                         #damit user, die sich registrieren direkt auf die Homepage weitergeleitet werden  
    }
?>

<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Registrierung</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <form action="index.php" method="POST">              <!--  alle Eingabefelder(<input/select/textarea/usw.>) mit einem name-Tag in form werden mit der Methode POST an die Datei index.php gesendet (wenn {zb. Button mit dem} type="submit" gedrückt wurde ) -->
        <h1>Account Erstellen</h1>
        <div class="inputs_container">    
            <input type="text" placeholder="Benutzername" name="username" autocomplete="off">
            <input type="text" placeholder="Email" name="email" autocomplete="off">
            <input type="password" placeholder="Passwort" name="password" autocomplete="off">
        </div>
        <button name="submit">Erstellen</button>
    </form>  
</body>
</html>