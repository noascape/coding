<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h1>Willkommen</h1>
    <?php
    $mail = $_POST["mail"];
    $pw = $_POST["pw"] ?? 0;

    echo "Mail:";
    echo $mail;
    ?> <br>
    <?php
    echo "PW:";
    echo $pw;
    ?>
</body>
</html>