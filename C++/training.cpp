//imports
#include <iostream>                 //input-output stream braucht man in jedem Program (sonst werder Ein- noch Ausgabe)
#include <string>                  //dann kann man "string name" schreiben anstatt "char[] name"
using namespace std;              //dann muss man nicht mehr std::count, etc. schreiben (standard name space)

const int Max = 100;

int main() {   
    string name = "Noah";                               //main wird immer ausgeführt
    int birth_date = 2004;
    int age = 2025 - 2004;
    cout << "Hello " + name + " how are you doing?\n";          //Zuweisung mit << an character out (zur Ausgabe)
    cout << "Ich bin " to_string(age) + " Jahre alt\n";         //to_string, um einen Integer in einer Zeichenketten-Ausgabe anzuzeigen
   
    cout << "Schreibe etweder 1 oder 2:";
    string number;
    cin >> number;                                //um die nächste Eingabe in der Kommandozeile mit c_in zu bekommen
    
    if(number == "1") {       //Vergleichsoperationen: ==,!=,<,>,<=,>=          
                              //Logische Operatoren: &&,||,!
                              //Bitweise Operatoren: &(AND),|(OR),^(XOR),~(NOT),<<(Bit-Shift links),>>(Bit-Shift rechts) 
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

    } else {cout << "Option 2"; }    //Kontrollstrukturen: if,else,switch 

    for (int i = 0; i < 10; i++) {cout << i;}         //Schleifen: for,while,do-while

    double i = sum(10.0,32.0);
    Person john("John");
    john.greet();
    return 0;                              //jede Funktion braucht einen Rückgabewert
}




double sum(double a, double b) {                               //Funktionen ganz normal
    return a + b;       //arithmetische Operationen: +,-,*,/,%
}

class Person {
    private: string name; 
    //Konstruktor:
    public: Person(const string& n) : name(n) {}
    //Methode
    void greet() const {cout << "Hello, my name is: " + name;}
}

//Zeiger
int a = 10;
int* ptr = &a;           // Zeiger (*) speichert die Adresse einer Variablen (&)
*ptr = 15;         // ändert den Wert von a auf 15
cout << "Wert von a: " << *ptr << endl;

int* ip = new int;        //Speicher reservieren
if(!ip) { return -1 }   //Prüfen ob erfolgreich
*ip = 5;                //Speicher verwenden
delete ip;           //Speicher freigeben

//Referenzen
int b = 20;
int& ref = b;      //Referenzen (&) sind Aliasnamen für Variablen und müssen beim Deklarieren initialisiert werden
ref = 30;  // b ist jetzt 30

//Dynamische Speicherverwaltung
int* array = new int[5];
// ...
delete[] array;         //man muss den Speicher explizit verwalten



//Exception Handling
try {
    throw std::runtime_error("Ein Fehler ist aufgetreten!");
} catch (const std::exception& e) {
    std::cout << "Fehler: " << e.what() << std::endl;
}


//Lambda-Ausdrücke
auto add = [](int a, int b) -> int {
    return a + b;
};

cout << "Summe: " << add(5, 7) << endl;



