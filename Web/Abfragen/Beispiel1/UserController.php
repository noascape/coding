<?php
 include $_SERVER['DOCUMENT_ROOT'].'/models/UserModel.php‘;
 $method = $_SERVER['REQUEST_METHOD'];
 $id = isset($parts[3]) ? intval($parts[3]) : 0;
 $usersModel = new UserModel(); 
switch ($method) {
  case 'GET':
    if ($id === 0) {
      echo json_encode($usersModel->getAllUsers());
    } else {
      echo json_encode($usersModel->getUserById($id));
    }
    break;
  default:
    header('HTTP/1.1 405 Method Not Allowed');
    break;
 }
