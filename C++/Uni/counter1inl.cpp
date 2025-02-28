#include <iostream>
using namespace std;

// Eine einfache Zählerklasse
class Counter
{
public:
    // Legt den Zähler mit dem Anfangswert initial an
    Counter( int initial = 0 )
        : cnt( initial )
    {}

    // Erhöht den Zähler um 1
    void up()
    {
        cnt++;
    }

    // Verringert den Zähler um 1
    void down()
    {
        cnt--;
    }

    // Liefert den aktuellen Zählerstand
    int value() const
    {
        return cnt;
    }

private:
    int cnt;
};

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
