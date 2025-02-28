#include <iostream>
#include "circle.h"

using namespace std;

int main()
{
    Circle circ( 3.0 );

    cout << "Flaeche:     " << circ.Area() << endl;
    cout << "Umfang:      " << circ.Perimeter() << endl;
    cout << "Durchmesser: " << circ.Diameter() << endl;

    return 0;
}
