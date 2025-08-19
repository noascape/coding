import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:io';



void main() {

    for (var i = 0; i < 2; i++){
      print("Hello $i ");
    }

    something();
    api();
    pictureapi();
}



void something(){

  print("Funktioniert");

}




void api() async{
  final url = Uri.parse("https://anapioficeandfire.com/api/characters/583");  //or books/x or houses/x
  final response = await http.get(url);

  if (response.statusCode == 200) {
    final data = jsonDecode(response.body);
    print("Name: ${data['name']}");
    print("Gender: ${data['gender']}");
    print("Titles: ${data['titles'][0]}");   //if I want to extract the first entry from the list
    print("Played by: ${data['playedBy']}");
  } else {
    print("Error: ${response.statusCode}");
  }
}




void pictureapi() async{
  final url = Uri.parse("https://cdn2.thecatapi.com/images/ebv.jpg");
  final response = await http.get(url);

  if (response.statusCode == 200) {
    //In Flutter: Image.memory(response.bodyBytes);
    //In Dart:
    final file = File("cat.jpg");
    await file.writeAsBytes(response.bodyBytes);
    print("Bild gespeichert als cat.jpg");
  } else {
    print("Error: ${response.statusCode}");
  }
}