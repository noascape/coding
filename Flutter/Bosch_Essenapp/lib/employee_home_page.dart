import 'package:flutter/material.dart';

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
