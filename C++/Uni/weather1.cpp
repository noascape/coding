#include <iostream>
#include <string>

using namespace std;

double temperatureFromMsg( string msg )
// Ermittelt aus der Wetternachricht msg
// die enthaltene Temperatur, liefert 0.0
// bei Fehlern
{
    // Tag für die Temperatur
    const string temptag = "TEMP:";

    // Tag für das Nachrichtenende
    const string endtag = "END";

    // Beginn des Temperatur-Tags suchen
    size_t temppos = msg.find( temptag );

    // Tag nicht vorhanden
    if( temppos == string::npos )
    {
        return 0.0;
    }

    // Wert beginnt nach dem Doppelpunkt
    temppos += temptag.size();

    // Beginn des Ende-Tags suchen
    size_t endpos  = msg.find( endtag );

    // Tag nicht vorhanden oder vor Temperatur
    if( endpos == string::npos ||
        endpos <= temppos )
    {
        return 0.0;
    }

    // Temperatur als Zeichenkette entnehmen
    string tempstr = msg.substr( temppos, endpos - temppos );

    // Konvertiere String in Zahlenwert
    return stod( tempstr );
}

int main()
{
    // Beispielnachrichten verarbeiten
    for( string msg : {  "MSG ID: 42 COND: Sonnig TEMP: 23.8 END",
                         "MSG ID: 42 COND: Schneefall TEMP: -2.0 END",
                         "MSG ID: 42 COND: Starker Regen, windig END",
                         "MSG ID: 42 COND: Viel Sonne, sehr windig",
                         "MSG ID: 42 COND: Sehr windig TEMP:END" } )
    {
        cout << "Nachricht: " << msg
             << "\tTemperatur: " << temperatureFromMsg( msg ) << endl;
    }

    return 0;
}
