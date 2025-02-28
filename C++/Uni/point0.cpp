#include <cmath>
#include <iostream>

using namespace std;

double dist( double x1, double y1, double x2, double y2 )
// Berechnet die Distanz zwischen den Punkten (x1|y1) und (x2|y2)
{
   return sqrt( ( x1 - x2 ) * ( x1 - x2 ) + ( y1 - y2 ) * ( y1 - y2 ) );
}

int main()
{
   double p_x = 0.0;
   double p_y = 0.0;

   double q_x = 4.0;
   double q_y = 3.0;

   cout << "Distanz zwischen P und Q: " << dist( p_x, p_y, q_x, q_y ) << endl;

   return 0;
}
