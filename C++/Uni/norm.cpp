#include <iostream>
#include <fstream>
#include <vector>
#include <numeric>
#include <algorithm>
#include <string>

using namespace std;

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

    // Name der Zieldatei einlesen
    string dest_filename;

    cout << "Zieldatei:  ";
    getline( cin, dest_filename );

    // Zieldatei zum Schreiben öffnen
    ofstream dest( dest_filename );

    if( !dest )
    {
        cerr << "Zieldatei " << dest_filename << " nicht bereit." << endl;
        return -1;
    }

    // Werte aus Quelldatei einlesen
    vector<double> values;
    double         value = 0.0;

    while( src >> value )
    {
        values.push_back( value );
    }

    // Minimum und Maximum ermitteln
    double minv = *min_element( values.begin(), values.end() );
    double maxv = *max_element( values.begin(), values.end() );

    // Eingelesene Werte normieren
    transform( values.begin(), values.end(), values.begin(),
              [minv,maxv]( double val ) { return ( val - minv ) / ( maxv-minv ); } );

    // Normierte Werte ausgeben
    for( double norm : values )
    {
        dest << norm << endl;
    }

    return 0;
}
