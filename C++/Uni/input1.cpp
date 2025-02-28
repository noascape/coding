#include <iostream>

using namespace std;

int main()
{
    int wert = 0.0;

    do
    {
        cout << "Wert: ";
        cin >> wert;
    }
    while( wert <= 0 );

    return 0;
}
