#include <iostream>
using namespace std;

int main()
{
    // Anzahl der Werte mit 0 initialisieren
    int anzahl_ges = 0;

    // Anzahl der Nicht-OK-Werte mit 0 initialisieren
    int anzahl_nok = 0;

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
        anzahl_ges++;

        // Wenn Wert nicht in Normbereich [45;55]
        if( messwert < 45.0 || messwert > 55.0 )
        {
            // Erhöhe Anzahl der Nicht-OK-Werte um 1
            anzahl_nok++;
        }
    }

    // Wenn keine Werte vorhanden
        // Fehlermeldung
        // Programm beenden

    // Zeige Anzahl der Werte an
    // Zeige Anteil der Nicht-OK-Werte als Prozentwert an

   return 0;
}
