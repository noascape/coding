<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="refresh" content="3">
    <title>Refresh</title>
</head>
<body>
    <?php
    $datum = date("d.m.Y",$timestamp);
    $uhrzeit = date("H:i",$timestamp);
    echo $datum," - ",$uhrzeit," Uhr";
    ?>
</body>
</html>