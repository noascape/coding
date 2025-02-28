#include <iostream>
#include <random>
#include <chrono>

using namespace std;

int main()
{
    // Mersenne-Twister Generator anlegen
    mt19937 randgen( chrono::system_clock::now().time_since_epoch().count() );

    // Gleichförmig verteilte Ganzzahl von 1 bis 6 (Würfel)
    uniform_int_distribution<> uniform( 1, 6 );

    // Würfeln
    cout << uniform( randgen ) << endl;

    return 0;
}
