//imports
#include <iostream>                                                                                 //input-output stream braucht man in jedem Program (sonst werder Ein- noch Ausgabe)
#include <string>                                                                                   //dann kann man "string name" schreiben anstatt "char[] name"
using namespace std;                                                                                //dann muss man nicht mehr std::count, etc. schreiben (standard name space)

int main() {   
    string name = "Noah";                                                                            //main wird immer ausgeführt
    int birth_date = 2004;
    int age = 2025 - 2004;
    cout << "Hello " + name + " how are you doing?";                                                 //Zuweisung mit << an character out (zur Ausgabe)
    cout << "Ich bin " to_string(age) + " Jahre alt";                                                //to_string, um einen Integer in einer Zeichenketten-Ausgabe anzuzeigen
    return 0;                                                                                        //jede Funktion braucht einen Rückgabewert
}