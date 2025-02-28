#include <iostream>

using namespace std;

int main()
{
    int wert = 0.0;

    do
    {
        cout << "Wert: ";
        cin >> wert;

        // Bei unerlaubten Eingaben ...
        if( cin.fail() )
        {
            cin.clear();              // ... Fehlerflags löschen und ...
            cin.ignore( 1000, '\n' ); // ... fehlerhafte Zeichen ignorieren
        }
    }
    while( wert <= 0 );

    return 0;
}
