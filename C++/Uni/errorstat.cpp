#include <iostream>
#include <fstream>
#include <map>
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

    // Häufigkeit der Fehlercodes zählen
    map<string,int> codefreq;
    string          errcode;

    while( getline( src, errcode ) )
    {
        codefreq[ errcode ]++;
    }

    // Häufigkeiten ausgeben
    for( auto freq : codefreq )
    {
        cout << freq.first << ": " << freq.second << endl;
    }

    return 0;
}
