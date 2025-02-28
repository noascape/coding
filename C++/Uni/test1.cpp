#include <fstream>
using namespace std;

int main() {
    ofstream dst("test.txt");
    if (!dst) {
        return -1;
    }
    for (int i = 0; i < 1000000; i++) {
        dst << i << endl;
    }
    return 0;
}


// \n
//execution time: 0.027s  -- 10000
//execution time: 0.039s  -- 100000
//execution time: 0.105s  -- 1000000

// endl
//execution time: 0.049s  -- 10000
//execution time: 0.203s  -- 100000
//execution time: 0.029s  -- 1000000
