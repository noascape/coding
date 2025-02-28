#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

// Grundlage einer Signal-Klasse auf der Basis von vector
class Signal : public vector<double>
{
public:
    Signal( size_t nrvals = 0, double initval = 0.0 );
    double get( int pos ) const;
    void set( int pos, double val );
};

Signal::Signal( size_t nrvals, double initval )
// Erzeugt ein Signal mit nrvals Werten, die mit initval initialisiert sind
    : vector<double>( nrvals, initval )
{
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

    return (*this)[ clamp<int>( pos, 0, size()-1 ) ];
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

    (*this)[ pos ] = val;
}

int main()
{
    Signal sig( 3, 10.0 );

    // Beispielhafte Änderungen
    sig.set( -1, 2.0 );
    sig.set( 0, 0.0 );
    sig.set( 2, 2.0 );
    sig.set( 5, -1.0 );

    // Werte anzeigen
    cout << "i\tsig[i]" << endl;
    for( int i = -2; i <= 4; i++ )
    {
        cout << i << '\t' << sig.get( i ) << endl;
    }

    return 0;
}
