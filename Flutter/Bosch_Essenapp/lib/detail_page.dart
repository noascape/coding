import 'package:flutter/material.dart';

class DetailPage extends StatelessWidget {
  const DetailPage({Key? key, required this.headline}) : super(key: key);

  final String headline;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(headline),
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            Align(
              alignment: Alignment.topRight,
              child: Image.asset(
                'lib/assets/images/Logo.png',
                width: 100.0,
                height: 100.0,
                fit: BoxFit.contain,
              ),
            ),
            Center(
              child: Text(
                headline,
                style: const TextStyle(fontSize: 48.0, color: Colors.white),
              ),
            ),
            SizedBox(height: 20.0),
            _buildMenuSection('Frühstück'),
            _buildMenuSection('Mittagessen'),
            _buildMenuSection('Nachtisch'),
            _buildMenuSection('Snacks'),
          ],
        ),
      ),
    );
  }

  Widget _buildMenuSection(String title) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          title,
          style: const TextStyle(fontSize: 24.0, color: Colors.white),
        ),
        // Hier könnten weitere Widgets für die Gerichte eingefügt werden
        ListView.builder(
          shrinkWrap: true,
          physics: NeverScrollableScrollPhysics(),
          itemCount: 3, // Beispielanzahl, durch echte Daten ersetzen
          itemBuilder: (context, index) {
            return ListTile(
              title: Text('Gericht ${index + 1}', style: TextStyle(color: Colors.white)),
            );
          },
        ),
        SizedBox(height: 16.0),
      ],
    );
  }
}
