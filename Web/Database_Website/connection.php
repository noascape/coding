<?php

    $dsn = 'mysql:dbname=userdb;host=localhost';  //später IP-Adresse des richtigen Hosts      (für PostgreSQL: 'pgsql:host=localhost;port=5432;dbname=testdb')  |  (für SQLite: 'sqlite:/path/to/database.db')
    $username = 'root';                           //nur für Entwicklung-Zwecke, später braucht man einen richtigen Account
    $password = '';                               //mit richtigem Passwort

    $con = new PDO($dsn, $username, $password);   //neue PHP Data Objects(PDO)-Klasse := Diese Schnittstelle benötigt zum Zugriff auf SQL-Datenbanken dns(datasourcename), username und password

?>