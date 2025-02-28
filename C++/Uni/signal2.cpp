#include <iostream>
#include <vector>
#include <algorithm>
#include <numeric>

using namespace std;

// Grundlage einer Signal-Klasse mit vector als Attribut
class Signal
{
public:
    Signal( size_t nrvals = 0, double initval = 0.0 );
    double get( int pos ) const;
    void set( int pos, double val );
    int size() const;
    double sum() const;

private:
    vector<double> values;
};

Signal::Signal( size_t nrvals, double initval )
// Erzeugt ein Signal mit nrvals Werten, die mit initval initialisiert sind
    : values( nrvals, initval )
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

    // Summe anzeigen
    cout << "Summe: " << sig.sum() << endl;

    return 0;
}
