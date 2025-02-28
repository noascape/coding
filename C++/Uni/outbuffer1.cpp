#include <iostream>

using namespace std;

int test()
// Eine endlos laufende Funktion
{
    // Endlosschleife
    while( 1  )
    {
    }

    return 42;
}

int main()
{
    cout << "Ergebnis: " << test() << endl;

    return 0;
}
