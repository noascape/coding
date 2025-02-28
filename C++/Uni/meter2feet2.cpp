// Rechnet Meter in Feet um

#include <iostream>

using namespace std;

int main()
{
    double meter = 0.0;
    cout << "Meter (>0): ";
    cin >> meter;

    // Bei ungueltigem Wert Programm beenden
    if( meter <= 0 )
    {
        cout << "Ungültiger Wert" << endl;
        return 0;
    }

    cout << "Feet:  " << meter * 3.2808399 << endl;

    return 0;
}

