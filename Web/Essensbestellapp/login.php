<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = $_POST['username'];
    $password = $_POST['password'];

    $users = [
        'Julian' => '1234',
        'Maria'  => '1234', 
        'chef' => 'chef', 
    ];

    if (isset($users[$username]) && $users[$username] == $password) {
        $_SESSION['username'] = $username;
        if ($username == 'chef') {
            header("Location: chef_dashboard.php");
        } else {
            header("Location: home.php");
        }
        exit();
    } else {
        $error_message = "Passwort oder Benutzername ist falsch.";
        header("Location: index.html?error=1&message=$error_message");
        exit();
    }
}
?>

