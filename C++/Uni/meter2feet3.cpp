// Rechnet Meter in Feet um

#include <iostream>

using namespace std;

int main()
{
    double meter = 0.0;

    // Ungueltige Eingaben wiederholen
    do
    {
        cout << "Meter (>0): ";
        cin >> meter;
    }
    while( meter <= 0 );

    cout << "Feet:  " << meter * 3.2808399 << endl;

    return 0;
}

