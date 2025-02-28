#include <iostream>
#include <string>
#include <fstream>
#include <random>

using namespace std;

int main()
{
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

    // Anzahl einlesen
    int nr_values = 0;
    cout << "Anzahl: ";
    cin >> nr_values;

    // Standardabweichung einlesen
    double stddev = 0.0;
    cout << "Standardabweichung: ";
    cin >> stddev;

    // Seed für Generator erzeugen
    random_device seed;

    // Mersenne-Twister Generator anlegen
    mt19937 randgen( seed() );

    // Normalverteilte Zufallszahlen mit Mittelwert 0 und
    // eingegebener Standardabweichung erzeugen
    normal_distribution<> normal( 0.0, stddev );

    // Zufallszahlen in Datei schreiben
    for( int i = 0; i < nr_values; i++ )
    {
        dest << normal( randgen ) << endl;
    }

    return 0;
}
