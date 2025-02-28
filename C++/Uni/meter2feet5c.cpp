#include <iostream>
#include <iomanip>

using namespace std;

int main()
{
    // Ueberschrift erzeugen
    cout << setw( 6 )  << "Meter"
         << setw( 10 ) << "Feet" << endl;

    // Umrechnungszeilen erzeugen
    for( int meter : { 1, 5, 10, 50, 100, 500, 1000, 5000 } )
    {
        cout << setw( 6 ) << meter
             << fixed << setprecision( 2 )
             << setw( 10 ) <<  meter * 3.2808399 << endl;
    }

    return 0;
}

