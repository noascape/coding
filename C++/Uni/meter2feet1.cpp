// Rechnet Meter in Feet um

#include <iostream>

using namespace std;

int main()
{
    // Laenge in Meter einlesen
    double meter = 0.0;
    cout << "Meter: ";
    cin >> meter;

    // In Feet umrechnen und ausgeben
    cout << "Feet:  " << meter * 3.2808399 << endl;

    return 0;
}
