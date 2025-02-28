#include <iostream>

using namespace std;

void vertausche( int* a, int* b )
// Vertauscht die Werte a und b
{
    int h = *a;
    *a = *b;
    *b = h;
}

int main()
{
    // Beispieldaten
    int a = 2;
    int b = 5;

    // Vertauschen
    vertausche( &a, &b );

    // Zeige das Ergebnis
    cout << "a:  " << a << endl;
    cout << "b:  " << b << endl;

    return 0;
}
