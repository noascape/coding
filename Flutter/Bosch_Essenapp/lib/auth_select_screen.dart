import 'package:flutter/material.dart';
import 'package:firebase_core/firebase_core.dart';
import 'auth_select_screen.dart';  // Importiere den Bildschirm für die Authentifizierungsauswahl

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Bosch Essen-App',
      theme: ThemeData(scaffoldBackgroundColor: Color(0xFF361D11)),
      home: const AuthSelectScreen(),
    );
  }
}

