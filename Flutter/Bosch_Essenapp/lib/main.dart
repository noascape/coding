import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Bosch Essen-App',
      theme: ThemeData(scaffoldBackgroundColor: Color(0xFF361D11)),
      home: const AuthCheck(),
    );
  }
}

// Widget, um Benutzerrolle zu überprüfen und entsprechende Seite anzuzeigen
class AuthCheck extends StatelessWidget {
  const AuthCheck({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    // Hier wird die Benutzerrolle überprüft
    String userRole = getUserRole(); // Beispielhaft, die tatsächliche Implementierung hängt von der Authentifizierung ab

    if (userRole == "Mitarbeiter") {
      return const EmployeeHomePage();
    } else {
      return const CuisineHomePage();
    }
  }

  // Diese Methode sollte die Rolle des Benutzers zurückgeben
  String getUserRole() {
    // Hier könntest du den tatsächlichen Code einfügen, der die Benutzerrolle überprüft
    return "Küche"; // Dummy-Wert für Beispielzwecke
  }
}

// Home-Seite für Mitarbeiter
class EmployeeHomePage extends StatelessWidget {
  const EmployeeHomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Dashboard - Mitarbeiter"),
      ),
      body: const Center(
        child: Text("Willkommen, Mitarbeiter!", style: TextStyle(fontSize: 24.0, color: Colors.white)),
      ),
    );
  }
}

// Home-Seite für Konsumenten
class CuisineHomePage extends StatelessWidget {
  const CuisineHomePage({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Dashboard - Küche"),
      ),
      body: PageView(
        children: const [
          DetailPage(headline: 'Montag'),
          DetailPage(headline: 'Dienstag'),
          DetailPage(headline: 'Mittwoch'),
          DetailPage(headline: 'Donnerstag'),
          DetailPage(headline: 'Freitag'),
          DetailPage(headline: 'Samstag'),
        ],
      ),
    );
  }
}

// Detail-Seite für die Wochentage
class DetailPage extends StatelessWidget {
  const DetailPage({Key? key, required this.headline}) : super(key: key);

  final String headline;

  @override
  Widget build(BuildContext context) {
    return Column(
            children: [
              Align(
                alignment: Alignment.topRight, child: Image.asset('lib/assets/images/Logo.png', width: 100.0, height: 100.0, fit: BoxFit.contain)),
              //SizedBox(height: 20.0),
              Center(child: Text(headline, style: const TextStyle(fontSize: 48.0, color: Colors.white))),
              //Spacer(),

                      ],
                );
            }
        }
