#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>
#include <string>
#include <fstream>

using namespace std;

// Grundlage einer Signal-Klasse mit vector als Attribut
class Signal
{
public:
    Signal( size_t nrvals = 0, double initval = 0.0 );
    Signal( const string& filename );
    bool load( const string& filename );
    double get( int pos ) const;
    void set( int pos, double val );
    int size() const;
    double sum() const;
    double average() const;

private:
    vector<double> values;
};

Signal::Signal( size_t nrvals, double initval )
// Erzeugt ein Signal mit nrvals Werten, die mit initval initialisiert sind
    : values( nrvals, initval )
{
}

Signal::Signal( const string& filename )
// Erzeugt ein Signal mit den in der Datei filename gespeicherten Werte
{
    load( filename );
}

bool Signal::load( const string& filename )
// Löscht alle Werte und lädt die Werte aus der Textdatei filename.
// Liefert true, wenn mindestens ein Wert geladen wurde, sonst false
{
    values.clear();

    // Quelldatei öffnen
    ifstream src( filename );

    if( !src )
    {
        return false;
    }

    // Werte zeilenweise aus Datei lesen
    double value = 0.0;
    while( src >> value )
    {
        values.push_back( value );
    }

    return ( size() > 0 );
}

double Signal::get( int pos ) const
// Liefert den Wert an der Stelle pos oder
// den nächstliegenden Wert, wenn der Wert nicht existiert
{
    // Feld ist leer
    if( size() == 0 )
    {
        return 0.0;
    }

    return values[ clamp<int>( pos, 0, size()-1 ) ];
}

void Signal::set( int pos, double val )
// Ändert den Wert an der Stelle pos auf den Wert value,
// existiert das zu ändernde Element nicht, geschieht nichts
{
    // Nicht vorhandene Elemente ignorieren
    if( pos < 0 ||
        pos >= size() )
    {
        return;
    }

    values[ pos ] = val;
}

int Signal::size() const
// Liefert die Größe des Signals
{
    return values.size();
}

double Signal::sum() const
// Liefert die Summe der enthaltenen Werte
{
    return accumulate( values.begin(), values.end(), 0.0 );
}

double Signal::average() const
// Liefert den Mittelwert der enthaltenen Werte
{
    if( size() <= 0 )
    {
        return 0.0;
    }

    return accumulate( values.begin(), values.end(), 0.0 ) / double( size() );
}


int main()
{
    // Werte aus values.dat laden
    Signal sig( "values.dat" );

    // Summe anzeigen
    cout << "values.dat: " << endl
         << "Mittelwert: " << sig.average() << endl;

    // Werte aus nofile.dat laden
    if( !sig.load( "nofile.dat" ) )
    {
        cerr << "nofile.dat wurde nicht geladen." << endl;
    }

    return 0;
}
