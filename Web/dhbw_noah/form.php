<!DOCTYPE html>
<html lang="de">
<head>
    <meta charset="UTF-8">
    <title>Form2</title>
</head>
<body>

    Hallo <strong>
    <?php
    $minuend = $_POST["minuend"] ?? 0;
    $subtrahend = $_POST["subtrahend"] ?? 0;

    echo "<p>";
    echo $minuend;
    echo "minus";
    echo $subtrahend;
    echo "ist";
    echo $minuend - $subtrahend;
    echo "!";
    echo "</p>";

    ?>
    
    
   
</body>
</html>