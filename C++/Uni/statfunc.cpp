#include <iostream>
#include <fstream>
#include <vector>
#include <numeric>
#include <algorithm>
#include <string>

using namespace std;

class NotInRange
{
public:
    // Legt [l;u] als Bereich fest
    NotInRange( double l, double u )
        : lower( l ), upper( u )
    {
    }

    // Liefert true, wenn value nicht in [lower;upper] liegt
    bool operator()( double value ) const
    {
        return value < lower || value > upper;
    }

private:
    double lower;
    double upper;
};

int main()
{
    // Name der Quelldatei einlesen
    string src_filename;

    cout << "Quelldatei: ";
    getline( cin, src_filename );

    // Quelldatei zum Lesen öffnen
    ifstream src( src_filename );

    if( !src )
    {
        cerr << "Quelldatei " << src_filename << " nicht bereit." << endl;
        return -1;
    }

    // Werte aus Quelldatei einlesen
    vector<double> values;
    double         value = 0.0;

    while( src >> value )
    {
        values.push_back( value );
    }

    if( values.size() < 1 )
    {
        cout << "Zu wenige Werte vorhanden." << endl;
        return 0;
    }

    // Kenngrößen berechnen
    int    cnt  = values.size();
    double sum  = accumulate( values.begin(), values.end(), 0.0 );
    double avg  = sum / double( cnt );
    double minv = *min_element( values.begin(), values.end() );
    double maxv = *max_element( values.begin(), values.end() );

    // Werte <0 oder >5 zählen
    int    nrout = count_if( values.begin(), values.end(),
                             NotInRange( 0, 5 ) );

    // Kenngrößen ausgeben
    cout << "Werte:      " << cnt << endl;
    cout << "Mittelwert: " << avg << endl;
    cout << "Spannweite: " << maxv - minv << endl;
    cout << "<0 oder >5: " << nrout << endl;

    return 0;
}
