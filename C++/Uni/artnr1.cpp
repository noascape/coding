#include <iostream>
#include <string>

using namespace std;


string extractCountryName( string artnr )
// Liefert den deutschen Landesnamen,
// der in der Artikelnummer artnr codiert ist
{
    // Anzahl der Zeichen ermitteln
    int nr_chars = artnr.size();

    // Zu wenige Zeichen
    if( nr_chars < 1 )
        return "-";

    // Land ermitteln
    char country_char = artnr[ nr_chars-1 ];

    switch( country_char )
    {
        case 'd':
        case 'D':
            return "Deutschland";
        case 'u':
        case 'U':
            return "USA";
        case 'c':
        case 'C':
            return "China";
        default:
            return "?";
    }

    return "";
}

int main()
{
    // Artikelnummer einlesen
    string artnr;
    cout << "Artikelnummer: ";
    getline( cin, artnr );

    // Land anzeigen
    cout << "Zuordnung von " << artnr << ": "
         << extractCountryName( artnr ) << endl;

    return 0;
}
