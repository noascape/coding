import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

//StatelessWidget wenn sich nichts am Inhalt verändern soll, sonst StatefulWidget
class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        scaffoldBackgroundColor: const Color(0xFF292929)
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});
  final String title;
  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
//  int _counter = 0;

//  void _incrementCounter() {
//    setState(() {
//      _counter++;
//    });
//  }

  @override
  Widget build(BuildContext context) {
    return const Scaffold(

      body: Padding(
        padding: EdgeInsetsDirectional.fromSTEB(0.0, 80, 0.0, 32.0),
        child: Column(
          children: <Widget>[
            Padding(
              padding: EdgeInsetsDirectional.fromSTEB(32.0, 0, 32.0, 24.0),
              child: Row(children: <Widget>[
                Icon(Icons.directions_run, color: Colors.white70, size: 50),
                Text(
                    '2300/5000m:',
                    style: TextStyle(color: Colors.white70, fontSize: 35)
              ),
            ],),
            ),
            LinearProgressIndicator(
              value: 0.3,
              color: Color(0xFF8BEF11),
              backgroundColor: Color(0x40FFFFFF),
              minHeight: 10.0,
            )
          ],
        ),
      ),
    );
  }
}