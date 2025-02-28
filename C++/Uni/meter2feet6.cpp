// Rechnet Meter in Feet um

#include <iostream>

using namespace std;

double meter2feet( double m )
// Rechnet die Laenge m von Metern in Feet um
{
    return m * 3.2808399;
}

int main()
{
    // Laenge in Meter einlesen
    double meter = 0.0;
    cout << "Meter: ";
    cin >> meter;

    // In Feet umrechnen und ausgeben
    cout << "Feet:  " << meter2feet( meter ) << endl;

    return 0;
}
