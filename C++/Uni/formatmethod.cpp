#include <iostream>

using namespace std;

int main()
{
    double wert = 12.3456789;

    // Standardgenauigkeit
    cout << "Genauigkeit: " << cout.precision() << endl; // Ausgabe: 6
    cout << wert << endl;                                // Ausgabe: 12.3457

    // Angepasste Genauigkeit
    cout.precision( 4 );

    cout << "Genauigkeit: " << cout.precision() << endl; // Ausgabe: 4
    cout << wert << endl;                                // Ausgabe: 12.35

    return 0;
}

