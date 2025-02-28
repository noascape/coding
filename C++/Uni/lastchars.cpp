#include <iostream>
#include <string>

using namespace std;

string endstr( string s, size_t n )
// Liefert die letzten n Zeichen der
// Zeichenkette s oder s, wenn n größer
// als dessen Länge ist.
{
    if( n >= s.size() )
    {
        return s;
    }

    return s.substr( s.size()-n, n );
}

int main()
{
    string s = "Hallo";

    for( int i = 0; i <= 6; i++ )
    {
        cout << i << ": " << endstr( s, i ) << endl;
    }

    return 0;
}

