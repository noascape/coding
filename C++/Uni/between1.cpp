#include <iostream>

using namespace std;

bool between( int val, int minval, int maxval )
// Liefert true, wenn minval <= val <= maxval ist
{
    return minval <= val && val <= maxval;
}

bool between( double val, double minval, double maxval )
// Liefert true, wenn minval <= val <= maxval ist
{
    return minval <= val && val <= maxval;
}

bool between( const string& val, const string& minval, const string& maxval )
// Liefert true, wenn minval <= val <= maxval ist
{
    return minval <= val && val <= maxval;
}

int main()
{
    cout << between( 2, 0, 5 ) << endl;  // true
    cout << between( 2, 4, 7 ) << endl;  // false

    cout << between( 1.2, -0.5, 1.2 ) << endl;  // true
    cout << between( -0.5, 5.4, 6.1 ) << endl;  // false

    cout << between( "Carl", "Anna", "David" ) << endl;  // true
    cout << between( "Anna", "Ben", "Emma" ) << endl;    // false

    return 0;
}
