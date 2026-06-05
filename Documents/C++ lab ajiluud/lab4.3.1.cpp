#include <iostream>
#include <cstdlib>   // rand(), srand()
#include <ctime>     // time()
using namespace std;

int main() {
    srand(time(0));              
    int secret = rand() % 10 + 1; 
    int guess;                   
    int attempts = 0;            

    do {
        cout << "toogoo oruul: ";
        cin >> guess;
        attempts++;

        if (guess > secret) {
            cout << "oroldlogo " << attempts << ": " << guess << " ih baina" << endl;
        } 
        else if (guess < secret) {
            cout << "oroldlogo " << attempts << ": " << guess << " baga baina" << endl;
        } 
        else {
            cout << "oroldlogo " << attempts << ": " << guess << " zov taalaa!" << endl;
        }
    } while (guess != secret);

    return 0;
}
    
