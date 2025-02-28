#include <cmath>
#include <iostream>

using namespace std;

class Point
{
public:
    Point( double nx = 0.0, double ny = 0.0 );
    double distTo( const Point& p ) const;

    double x;
    double y;
};

Point::Point( double nx, double ny )
// Erzeugt einen Punkt mit den Koordination (nx|ny)
{
    x = nx;
    y = ny;
}

double Point::distTo( const Point& p ) const
// Berechnet die Entfernung zu dem Punkt p
{
    return sqrt( ( x - p.x ) * ( x - p.x ) + ( y - p.y ) * ( y - p.y ) );
}


int main()
{
    Point p;
    Point q( 4.0, 3.0 );

    cout << "Distanz zwischen P und Q: " << p.distTo( q ) << endl;

    return 0;
}
