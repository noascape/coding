#include <iostream>
#include <vector>

using namespace std;

const int AnzahlWerte = 5;

int main()
{
    // 5 Werte einlesen ...
    vector<int> werte( AnzahlWerte, 0 );

    for( int i = 0; i < werte.size(); i++ )
    {
        cout << "Wert " << i << ": ";
        cin >> werte[ i ];
    }

    // ... und in umgekehrter Reihenfolge ausgeben
    for( int i = werte.size()-1; i >= 0; i-- )
    {
        cout << "Wert " << i << ": " << werte[ i ] << endl;
    }

    cout << werte.at(5) << endl;

    return 0;
}
