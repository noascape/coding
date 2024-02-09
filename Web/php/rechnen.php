<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
</head>

<body>
<form method="post">
<input required name="minuend" type="number" placeholder="Minuend">
<input required name="subtrahend" type="number" placeholder="Subtrahend">
<button>Berechne</button>
</form>

<?php
$minuend = $_POST["minuend"] ?? 0;
$subtrahend = $_POST["subtrahend"] ?? 0;

echo "<p>";
echo $minuend;
echo " minus ";
echo $subtrahend;
echo " ist ";
echo $minuend - $subtrahend;
echo "!";
echo "</p>"
?>
</body>

</html>