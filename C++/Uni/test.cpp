#include <iostream>
#include <fstream>   // für Dateioperationen
using namespace std;

int numbers();
int sum(int a);
int manuellSum();

int result = 0;
int result1 = 0;

ofstream outfile("result.txt");      //wird jedes mal angepasst und nicht neu erstellt :)

int main() {

    if(!outfile) {cout << "File not ready." << endl; return -1; };
    numbers();
    manuellSum();
    outfile.close();
    return 0;
}

int numbers(){
    for (int i = 0; i <= 100; i++) {
        cout << i << "\n";
        outfile << i << "\n";
        sum(i);
    }
    return 0;
}

int sum(int a) {
    result += a;
    cout << "Summe: " << result << "\n";
    outfile << "Summe: " << result << "\n";
    return 0;
}

int manuellSum() {
    int val;
    cout << "\nBitte gib Zahlen ein, die aufsummiert werden sollen (Beende die Eingabe mit x):\n";

    while (cin >> val) {  // Liest Zahlen aus der Standardeingabe
        result1 += val;
    }

    cout << "Manuelle Summe: " << result1 << "\n";
    outfile << "\n Manuelle Summe: " << result1 << "\n";
    return 0;
}
