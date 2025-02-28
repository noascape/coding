// Erzeugt eine Umrechnungstabelle Meter/Feet

#include <iostream>

using namespace std;

// Anzahl der Tabellenzeilen
const int AnzZeilen = 5;

int main()
{
    // Ueberschrift erzeugen
    cout << "Meter\tFeet" << endl;

    // Umrechnungszeilen erzeugen
    for( int meter = 1; meter <= AnzZeilen; meter++ )
    {
        cout << meter << '\t' << meter * 3.2808399 << endl;
    }

    return 0;
}
