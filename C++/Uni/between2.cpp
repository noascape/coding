#include <iostream>

using namespace std;

template< typename T>
bool between( const T& val, const T& minval, const T& maxval )
// Liefert true, wenn minval <= val <= maxval ist
{
    return minval <= val && val <= maxval;
}

int main()
{
    // Aufruf mit Template-Parameter
    cout << between<int>( 2, 0, 5 ) << endl;  // true
    cout << between<int>( 2, 4, 7 ) << endl;  // false

    cout << between<double>( 1.2, -0.5, 1.2 ) << endl;  // true
    cout << between<double>( -0.5, 5.4, 6.1 ) << endl;  // false

    cout << between<string>( "Carl", "Anna", "David" ) << endl;  // true
    cout << between<string>( "Anna", "Ben", "Emma" ) << endl;    // false

    // Aufruf ohne Template-Parameter
    cout << between( 2, 0, 5 ) << endl;  // true
    cout << between( 1.2, -0.5, 1.2 ) << endl;  // true
    cout << between( "Carl"s, "Anna"s, "David"s ) << endl;  // true

    return 0;
}
