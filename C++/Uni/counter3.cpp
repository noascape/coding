#include <iostream>
using namespace std;

// Eine einfache Zählerklasse (Zählerstand >= 0)
class Counter
{
public:
    Counter( int initial = 0 );
    void up();
    void down();
    int value() const;

    bool operator==( const Counter& other ) const;

private:
    int cnt;
};

Counter::Counter( int initial )
// Legt den Zähler mit dem Anfangswert initial an
{
    cnt = ( initial > 0 ) ? initial : 0;
}

void Counter::up()
// Erhöht den Zähler um 1
{
    cnt++;
}

void Counter::down()
// Verringert den Zähler um 1
{
    if( cnt > 0 )
    {
        cnt--;
    }
}

int Counter::value() const
// Liefert den aktuellen Zählerstand
{
    return cnt;
}

bool Counter::operator==( const Counter& other ) const
// Prüft, ob der Zähler other denselben Zählerstand hat
{
    return ( cnt == other.cnt );
}

int main()
{
    // Erzeugt einen Zähler mit Anfangswert 1
    Counter c1( 1 );

    c1.down();
    c1.down();

    // Erzeugt einen zweiten Zähler mit negativem Anfangswert
    Counter c2( -2 );

    cout << "c1: " << c1.value() << endl;
    cout << "c2: " << c2.value() << endl;

    // Vergleicht die beiden Zähler
    if( c1 == c2 )
    {
        cout << "c1 und c2 haben denselben Stand." << endl;
    }

    return 0;
}
