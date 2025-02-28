#include <iostream>

using namespace std;

int main()
{
    // 5 Werte einlesen ...
    int werte0 = 0;
    cout << "Wert 0: ";
    cin >> werte0;

    int werte1 = 0;
    cout << "Wert 1: ";
    cin >> werte1;

    int werte2 = 0;
    cout << "Wert 2: ";
    cin >> werte2;

    int werte3 = 0;
    cout << "Wert 3: ";
    cin >> werte3;

    int werte4 = 0;
    cout << "Wert 4: ";
    cin >> werte4;

    // ... und in umgekehrter Reihenfolge ausgeben
    cout << "Wert 4: " << werte4 << endl;
    cout << "Wert 3: " << werte3 << endl;
    cout << "Wert 2: " << werte2 << endl;
    cout << "Wert 1: " << werte1 << endl;
    cout << "Wert 0: " << werte0 << endl;

    return 0;
}
