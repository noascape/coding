#include <iostream>
#include <utility>

using namespace std;

pair<double,bool> relChange( double old_y, double new_y )
// Berechnet die relative Änderung von old_y zu new_y
// Liefert ein pair-Objekt aus Ergebnis und Erfolg
{
    if( old_y == 0.0 )
    {
        return make_pair( 0.0, false );
    }

    return make_pair( ( new_y - old_y)/old_y, true );
}

int main()
{
    // Erprobt relChange() für verschiedene Werte
    for( double val : { 1.0, 2.0, 0.0 } )
    {
        auto res = relChange( val, 1.0 );

        if( res.second )
        {
            cout << "relChange(" << val << ",1)=" << res.first << endl;
        }
        else
        {
            cout <<"relChange(" << val << ",1) verursacht Fehler." << endl;
        }
    }

    return 0;
}
