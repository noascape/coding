#include <iostream>

using namespace std;

int main()
{
    // Ohne Initialisierung
    double wert1;
    cout << wert1 << endl; // Ausgabe: 7.90505e-323 (oder anderer Wert)

    // Mit Initialisierung
    double wert2 = 0.0;
    cout << wert2 << endl; // Ausgabe: 0

    return 0;
}
