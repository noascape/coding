#include <iostream>
#include <chrono>

using namespace std;

void longrunning_function()
// Eine lang laufende Funktion
{
    for( int i = 0; i < 10000000; i++ );
}

int main()
{
    // Startzeitpunkt ermitteln
    auto start = chrono::high_resolution_clock::now();

    // --- Zu messender Abschnitt
    longrunning_function();
    // ---

    // Endzeitpunkt ermitteln
    auto stop = chrono::high_resolution_clock::now();

    // Laufzeit berechnen
    auto duration = chrono::duration_cast<chrono::microseconds>( stop - start );

    cout << "Dauer: " << duration.count() << " Mikrosekunden" << endl;

    return 0;
}
