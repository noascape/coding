<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
</head>
<body>
    <h1>Willkommen  
        <?php 
            session_start();                    #mit der Session kann nun der global gespeicherte username in $_SESSION mit echo ausgegeben werden
            echo $_SESSION["username"]; 
        ?> 
        auf der Homepage</h1>
    <button id="btn">Get Back</button>
</body>
</html>