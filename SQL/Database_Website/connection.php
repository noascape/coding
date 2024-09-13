<?php

    $dsn = 'mysql:dbname=userdb;host=localhost';  //später IP-Adresse des richtigen Hosts
    $username = 'root';                           //nur für Entwicklung-Zwecke, später braucht man einen richtigen Account
    $password = '';                               //mit richtigem Passwort

    $con = new PDO($dsn, $username, $password);   //Schnittstelle zum Zugriff auf SQL-Datenbanken benötigt dns(datasourcename), username und password



?>