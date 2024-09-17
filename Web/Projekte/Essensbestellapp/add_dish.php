<?php
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    if (isset($_POST['tag']) && isset($_POST['datum']) && isset($_POST['art']) && isset($_POST['beschreibung']) && isset($_POST['preis'])) {
        $gerichte = json_decode(file_get_contents('dishes.json'), true);

        $neuesGericht = [
            'tag' => $_POST['tag'],
            'datum' => $_POST['datum'],
            'art' => $_POST['art'],
            'beschreibung' => $_POST['beschreibung'],
            'preis' => $_POST['preis']
        ];

        $gerichte[] = $neuesGericht;

        file_put_contents('dishes.json', json_encode($gerichte, JSON_PRETTY_PRINT));

        header("Location: chef_orders.php");
        exit();
    } else {
        echo "Nicht alle Felder wurden ausgefüllt.";
    }
} else {
    echo "Das Formular wurde nicht gesendet.";
}
?>
