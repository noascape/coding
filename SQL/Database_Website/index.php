<!-- 
     Apache und SQL in XAMPP starten, Datei in htdocs
     index.html -> index.php
     unter XAMPP - SQL - Admin eine neue Datenbank anlegen "CREATE TABLE userdb(id int AUTO_INCREMENT PRIMARY KEY, username varchar(255), email varchar(255), password varchar(255))"
-->
<?php
    require("connection.php");              #connection.php importieren

    if(isset($_POST["submit"])) {           #abfragen ob es submitted wurde
        var_dump($_POST);

        $username = $_POST["username"];          #Variable username,..
        $email = $_POST["email"];
        $password = PASSWORD_HASH($_POST["password"], PASSWORD_DEFAULT);       #verschlüsselt das pw in der db

        $stmt = $con->prepare("SELECT * FROM users WHERE username=:username OR email=:email");           #SQL-Befehl mit prepare vorbereiten, um ihn später mit execute auszuführen
        $stmt->bindParam(":username", $username);                  #users muss wie in der db geschrieben werden
        $stmt->bindParam(":email", $email);
        $stmt->execute();

        $userAlreadyExists = $stmt->fetchColumn();

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
    <link rel="stylesheet" href="style.css"> </link>
</head>
<body>
    <form action="index.php" method="POST">
        <h1>Account Erstellen</h1>
        <div class="inputs_container">    
            <input type="text" placeholder="Benutzername" name="username" autocomplete="off">
            <input type="text" placeholder="Email" name="email" autocomplete="off">
            <input type="text" placeholder="Passwort" name="password" autocomplete="off">
        </div>
        <button name="submit">Erstellen</button>
    </form>  
</body>
</html>