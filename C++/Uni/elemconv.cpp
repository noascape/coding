#include <iostream>
#include <string>
#include <iomanip>
#include <fstream>

using namespace std;

// Trennzeichen für Zieldatei
const char CSV_Separator = ',';

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

    // Spaltenüberschrift in Zieldatei schreiben
    dest << "Ordnungszahl" << CSV_Separator
         << "Symbol" << CSV_Separator
         << "Dichte" << endl;

    // Werte lesen und kommagetrennt in Zieldatei schreiben
    int    elem_atomic = 0;
    string elem_symbol;
    double elem_density = 0.0;

    while( src >> elem_atomic >> elem_symbol >> elem_density )
    {
        dest << elem_atomic << CSV_Separator
             << elem_symbol << CSV_Separator
             << fixed << setprecision( 2 ) << elem_density << endl;
    }

    return 0;
}
