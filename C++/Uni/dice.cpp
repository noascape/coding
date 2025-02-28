#include <iostream>
#include <random>

using namespace std;

int main()
{
    // Seed für Generator erzeugen
    random_device seed;

    // Mersenne-Twister Generator anlegen
    mt19937 randgen( seed() );

    // Gleichförmig verteilte Ganzzahl von 1 bis 6 (Würfel)
    uniform_int_distribution<> uniform( 1, 6 );

    // Würfeln
    cout << uniform( randgen ) << endl;

    return 0;
}
