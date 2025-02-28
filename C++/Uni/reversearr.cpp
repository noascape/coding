#include <iostream>

using namespace std;

const int AnzahlWerte = 5;

int main()
{
    // 5 Werte einlesen ...
    int werte[ AnzahlWerte ] = {};

    for( int i = 0; i < AnzahlWerte; i++ )
    {
        cout << "Wert " << i << ": ";
        cin >> werte[ i ];
    }

    // ... und in umgekehrter Reihenfolge ausgeben
    for( int i = AnzahlWerte-1; i >= 0; i-- )
    {
        cout << "Wert " << i << ": " << werte[ i ] << endl;
    }

    return 0;
}
