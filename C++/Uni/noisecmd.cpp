#include <iostream>
#include <string>
#include <fstream>
#include <random>
#include <sstream>

using namespace std;

int main( int argc, char* argv[] )
{
    // Anzahl der Parameter prüfen
    if( argc < 4 )
    {
        cerr << "Aufruf: noisecmd <Zieldatei> <Anzahl> <Std.Abw.>" << endl;
        return -1;
    }

    // Zieldatei öffnen
    string dest_filename( argv[ 1 ] );

    ofstream dest( dest_filename );

    if( !dest )
    {
        cerr << "Zieldatei " << dest_filename << " nicht bereit." << endl;
        return -1;
    }

    // Anzahl umwandeln und prüfen
    int nr_values = 0;
    if( !( istringstream( argv[ 2 ] ) >> nr_values ) || nr_values <= 0 )
    {
        cerr << "Ungültige Anzahl " << argv[ 2 ] << endl;
        return -1;
    }

    // Standardabweichung umwandeln und prüfen
    double stddev = 0.0;
    if( !( istringstream( argv[ 3 ] ) >> stddev ) || stddev <= 0 )
    {
        cerr << "Ungültige Standardabweichung " << argv[ 3 ] << endl;
        return -1;
    }

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
