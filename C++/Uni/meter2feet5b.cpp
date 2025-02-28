// Erzeugt eine Umrechnungstabelle Meter/Feet

#include <iostream>

using namespace std;

int main()
{
    // Ueberschrift erzeugen
    cout << "Meter\tFeet" << endl;

    // Umrechnungszeilen erzeugen
    for( int meter : { 1, 2, 3, 5, 10, 50, 100 } )
    {
        cout << meter << '\t' << meter * 3.2808399 << endl;
    }

    return 0;
}
