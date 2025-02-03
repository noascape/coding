//imports
#include <iostream>                                                                                 //input-output stream braucht man in jedem Program (sonst werder Ein- noch Ausgabe)
#include <string>                                                                                   //dann kann man "string name" schreiben anstatt "char[] name"
using namespace std;                                                                                //dann muss man nicht mehr std::count, etc. schreiben (standard name space)

int main() {   
    string name = "Noah";                                                                            //main wird immer ausgeführt
    int birth_date = 2004;
    int age = 2025 - 2004;
    cout << "Hello " + name + " how are you doing?\n";                                                 //Zuweisung mit << an character out (zur Ausgabe)
    cout << "Ich bin " to_string(age) + " Jahre alt\n";                                                //to_string, um einen Integer in einer Zeichenketten-Ausgabe anzuzeigen
   
    cout << "Schreibe etweder 1 oder 2:";
    string number;
    cin >> number;                                                                                     //um die nächste Eingabe in der Kommandozeile mit c_in zu bekommen
    
    if(number == "1") {
        cout << "Option 1 (Vokabelabfrage)";
        string german_word;
        string spanish_word;
        cin >> german_word;
        cout << "Wie lautet die Übersetzung?";
        cin >> spanish_word;
        voc_german.push_back(german_word);
        voc_spanish.push_back(spanish_word);
        cout << "Vokabel wurde hinzugefügt: " + german_word + " lautet auf spanisch " + spanish_word;
        cout << "Hinzugefügt Anzahl an Vokabeln: " + to_string(voc.german.size());

    } else {
        cout << "Option 2";
    }

    return 0;                                                                                        //jede Funktion braucht einen Rückgabewert
}