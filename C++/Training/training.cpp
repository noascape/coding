//imports
#include <iostream>     // input-output stream
#include <string>       // für std::string und to_string
#include <vector>       // für std::vector (Vokabeln)
#include <stdexcept>    // für std::runtime_error
using namespace std;

double sum(double a, double b);  // Vorwärtsdeklaration

class Person {                   // Klassendefinition nach C++-Standard
    private: 
        string name; 
    public: 
        Person(const string& n) : name(n) {}
        void greet() const { cout << "Hello, my name is: " << name; }
};

int main() {   
    string name = "Noah";                    // main wird immer ausgeführt
    int birth_date = 2004;
    int age = 2025 - birth_date;
    cout << "Hello " << name << " how are you doing?\n";
    cout << "Ich bin " << to_string(age) << " Jahre alt\n";
   
    cout << "Schreibe etweder 1 oder 2:";
    string number;
    cin >> number;
    
    // Vektoren für die Vokabeln anlegen
    vector<string> voc_german;
    vector<string> voc_spanish;
    
    if(number == "1") {  // Vergleichsoperationen
        cout << "Option 1 (Vokabelabfrage)\n";
        string german_word;
        string spanish_word;
        cout << "Gib ein deutsches Wort ein: ";
        cin >> german_word;
        cout << "Wie lautet die Übersetzung? ";
        cin >> spanish_word;
        voc_german.push_back(german_word);
        voc_spanish.push_back(spanish_word);
        cout << "Vokabel wurde hinzugefügt: " << german_word 
             << " lautet auf spanisch " << spanish_word << "\n";
        cout << "Hinzugefügt Anzahl an Vokabeln: " << to_string(voc_german.size()) << "\n";
    } else {
        cout << "Option 2";
    }
    
    for (int i = 0; i < 10; i++) {
        cout << i;
    }
    
    double result = sum(10.0, 32.0);
    Person john("John");
    john.greet();
    
    // Zeiger-Beispiele
    int a = 10;
    int* ptr = &a;           // ptr speichert die Adresse von a
    *ptr = 15;               // ändert den Wert von a auf 15
    cout << "\nWert von a: " << *ptr << endl;
    
    int* ip = new int;       // Speicher reservieren
    if (!ip) { return -1; }   // Prüfen, ob erfolgreich
    *ip = 5;                 // Speicher verwenden
    delete ip;               // Speicher freigeben
    
    // Referenzen
    int b = 20;
    int& ref = b;            // ref ist ein Alias für b und muss initialisiert werden
    ref = 30;                // b ist jetzt 30
    
    // Dynamische Speicherverwaltung (Array)
    int* array = new int[5];
    // ...
    delete[] array;          // Speicher freigeben
    
    // Exception Handling
    try {
        throw runtime_error("Ein Fehler ist aufgetreten!");
    } catch (const exception& e) {
        cout << "Fehler: " << e.what() << endl;
    }
    
    // Lambda-Ausdrücke
    auto add = [](int a, int b) -> int {
        return a + b;
    };
    
    cout << "Summe: " << add(5, 7) << endl;
    
    return 0;
}

double sum(double a, double b) {
    return a + b;
}
