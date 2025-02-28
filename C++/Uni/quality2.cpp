#include <iostream>
using namespace std;

int main()
{
    // Anzahl der Werte mit 0 initialisieren
    // Anzahl der Nicht-OK-Werte mit 0 initialisieren

    // Messwerte eingeben, bis zur Eingabe von 0
    while( 1 )
    {
        // Nächsten Wert einlesen
        double messwert = 0.0;
        cout << "Wert: ";
        cin >> messwert;

        // Abbruch mit Eingabe von 0
        if( messwert == 0.0 )
        {
            break;
        }

        // Erhöhe Anzahl der Werte um 1
        // Wenn Wert nicht in Normbereich [45;55]
            // Erhöhe Anzahl der Nicht-OK-Werte um 1
    }

    // Wenn keine Werte vorhanden
        // Fehlermeldung
        // Programm beenden

    // Zeige Anzahl der Werte an
    // Zeige Anteil der Nicht-OK-Werte als Prozentwert an

   return 0;
}
