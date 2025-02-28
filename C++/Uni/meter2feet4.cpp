// Rechnet Meter in Feet um

#include <iostream>

using namespace std;

int main()
{
    int werte = 0; // Anzahl der umgerechneten Werte

    // Umrechnung wiederholen
    while( 1 )
    {
        double meter = 0.0;
        cout << "Meter (>0): ";
        cin >> meter;

        // Abbruch mit ungueltigem Wert
        if( meter <= 0 )
        {
            break;
        }

        cout << "Feet:  " << meter * 3.2808399 << endl;

        werte++;
    }

    // Zusammenfassung anzeigen
    cout << werte << " Werte umgerechnet." << endl;

    return 0;
}
