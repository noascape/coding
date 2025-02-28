#include <iostream>
#include <string>

using namespace std;

double temperatureFromMsg( string msg, bool& ok )
// Ermittelt aus der Wetternachricht msg die enthaltene Temperatur
// und setzt ok bei Erfolg auf true, sonst false
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
        ok = false;
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
        ok = false;
        return 0.0;
    }

    // Temperatur als Zeichenkette entnehmen
    string tempstr = msg.substr( temppos, endpos - temppos );

    // Konvertiere String in Zahlenwert
    double tempval = 0.0;

    ok = true;

    try
    {
        tempval = stod( tempstr );
    }
    catch( const invalid_argument& e )
    {
        ok = false;
        tempval = 0.0;
    }

    return tempval;
}

int main()
{
    // Beispielnachrichten verarbeiten
    for( string msg : {  "MSG ID: 42 COND: Sonnig TEMP: 23.8 END",
                         "MSG ID: 42 COND: Schneefall TEMP: -2.0 END",
                         "MSG ID: 42 COND: Starker Regen, windig END",
                         "MSG ID: 42 COND: Viel Sonne, sehr windig",
                         "MSG ID: 42 COND: Sehr windig TEMP:END",
                         "MSG ID: 42 COND: Sonnig TEMP: yyc5a$ END", } )
    {
        // Erfolgstatus der Verarbeitung
        bool   ok = false;

        // Ermittelte Temperatur
        double temp = temperatureFromMsg( msg, ok );

        cout << "Nachricht: " << msg << "\t";

        // Erfolgsstatus prüfen
        if( ok )
        {
            cout << "Temperatur: " << temp << endl;
        }
        else
        {
            cout << "Fehler gefunden." << endl;
        }
    }

    return 0;
}
