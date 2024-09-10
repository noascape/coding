<?php
 class UserModel {
  private $users = [
    ['id' => 1, 'name' => 'John Doe', 'email' => 'john@example.com'],
    ['id' => 2, 'name' => 'Jane Smith', 'email' => 'jane@example.com'],
    ['id' => 3, 'name' => 'Mike Johnson', 'email' => 'mike@example.com']
  ];
  public function getAllUsers() {
    return $this->users;
  }
  public function getUserById($id) {
    foreach ($this->users as $user) {
      if ($user['id'] === $id) {
        return $user;
      }
    }
    return null;
  }
 }
