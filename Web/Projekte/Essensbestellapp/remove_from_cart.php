<?php
session_start();

header('Content-Type: application/json');

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $input = json_decode(file_get_contents('php://input'), true);

    if ($input && isset($_SESSION['username'], $input['index'])) {
        $username = $_SESSION['username'];
        $ordersFile = 'orders.json';

        if (file_exists($ordersFile)) {
            $orders = json_decode(file_get_contents($ordersFile), true);

            if (isset($orders[$username])) {
                array_splice($orders[$username], $input['index'], 1);

                file_put_contents($ordersFile, json_encode($orders));

                echo json_encode(['success' => true]);
                exit();
            }
        }
    }

    echo json_encode(['success' => false, 'message' => 'Invalid input or user not authenticated']);
    exit();
}

echo json_encode(['success' => false, 'message' => 'Invalid request method']);
?>

