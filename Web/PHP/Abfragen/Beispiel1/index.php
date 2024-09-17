<?php
 $request = $_SERVER['REQUEST_URI‘];
 $parts = explode('/', $request);
 $resource = $parts[1]; 
if ($resource === 'users‘) {
 include 'controllers/UsersController.php‘;
 } else {
 header('HTTP/1.1 404 Not Found‘);
 echo json_encode(['message' => 'Resource not found.‘]);
 }