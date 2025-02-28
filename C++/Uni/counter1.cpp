#include <iostream>
using namespace std;

// Eine einfache Zählerklasse
class Counter
{
public:
    Counter( int initial = 0 );
    void up();
    void down();
    int value() const;
private:
    int cnt;
};

Counter::Counter( int initial )
// Legt den Zähler mit dem Anfangswert initial an
{
    cnt = initial;
}

void Counter::up()
// Erhöht den Zähler um 1
{
    cnt++;
}

void Counter::down()
// Verringert den Zähler um 1
{
    cnt--;
}

int Counter::value() const
// Liefert den aktuellen Zählerstand
{
    return cnt;
}

int main()
{
    // Erzeugt einen Zähler mit Anfangswert 0
    Counter c1;

    c1.up();
    c1.up();

    // Erzeugt einen zweiten Zähler mit Anfangswert 10
    Counter c2( 10 );

    c2.up();
    c2.up();
    c1.down();

    cout << "c1: " << c1.value() << endl;
    cout << "c2: " << c2.value() << endl;

    return 0;
}
