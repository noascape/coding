#include <iostream>
#include <iomanip>

using namespace std;

int main()
{
    double wert = 12.3456789;

    // Standardgenauigkeit
    cout << wert << endl;                         // Ausgabe: 12.3457

    // Angepasste Genauigkeit
    cout << setprecision( 4 ) << wert << endl;    // Ausgabe: 12.35

    return 0;
}

