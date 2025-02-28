#include <iostream>
#include "utils.h"

using namespace std;

int main()
{
    if( between( 7, 0, 42 ) )
    {
        cout << "7 ist zwischen 0 und 42" << endl;
    }

    cout << "Das Quadrat von 7 ist " << sqr( 7 ) << endl;

    return 0;
}
