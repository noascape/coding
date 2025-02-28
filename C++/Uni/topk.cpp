#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <string>

using namespace std;

// Anzahl der K größten Werte
const int NumberK = 3;

int main()
{
    // Name der Quelldatei einlesen
    string src_filename;

    cout << "Quelldatei: ";
    getline( cin, src_filename );

    // Quelldatei zum Lesen öffnen
    ifstream src( src_filename );

    if( !src )
    {
        cerr << "Quelldatei " << src_filename << " nicht bereit." << endl;
        return -1;
    }

    // Werte aus Quelldatei einlesen
    vector<double> values;
    double         value = 0.0;

    while( src >> value )
    {
        values.push_back( value );
    }

    if( values.size() < NumberK )
    {
        cout << "Zu wenige Werte vorhanden." << endl;
        return 0;
    }

    // Werte sortieren
    sort( values.begin(), values.end() );

    // Größte Werte ausgeben
    cout << "Top " << NumberK << "-Werte:" << endl;

    for( int ranknr = 1; ranknr <= NumberK; ranknr++ )
    {
        cout << ranknr << ": " << values[ values.size() - ranknr ] << endl;
    }

    return 0;
}
