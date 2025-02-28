#include "circle.h"

Circle::Circle( double nradius )
         : radius( nradius )
{
}

double Circle::Area() const
{
    return PI * radius * radius;
}

double Circle::Perimeter() const
{
    return 2.0 * PI * radius;
}

double Circle::Diameter() const
{
    return 2.0 * radius;
}
