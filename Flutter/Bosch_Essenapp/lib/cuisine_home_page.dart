import 'package:flutter/material.dart';
import 'detail_page.dart';  // Importiere die Detail-Seite

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
