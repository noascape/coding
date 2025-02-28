#include <cmath>
#include <iostream>
#include <fstream>

using namespace std;

class Point
{
public:
    Point( double nx = 0.0, double ny = 0.0 );
    double distTo( const Point& p ) const;

    double x;
    double y;

    friend istream& operator>>( istream& in,  const Point& p );
    friend ostream& operator<<( ostream& out, const Point& p );
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

istream& operator>>( istream& in, Point& p )
// Liest einen Punkt in der Form x y ein
{
    in >> p.x >> p.y;

    return in;
}

ostream& operator<<( ostream& out, const Point& p )
// Gibt einen Punkt in der Form x|y aus
{
    out << p.x << "|" << p.y;

    return out;
}


int main()
{
    // Quelldatei öffnen
    ifstream src( "points.dat" );

    if( !src )
    {
        cerr << "Quelldatei nicht bereit." << endl;
        return -1;
    }

    // Punkte aus Datei lesen und anzeigen
    Point p;

    while( src >> p )
    {
        cout << p << endl;
    }

    return 0;
}
