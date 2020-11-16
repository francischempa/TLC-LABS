#include <iostream>

#define cout std::cout
#define cin std::cin
#define endl std::endl

enum {RED, YELLOW, AMBER=YELLOW, GREEN};

int main(){
    cout << "RED = " << RED << endl;
    cout << "YELLOW = " << YELLOW << endl;
    cout << "AMBER = " << AMBER << endl;
    cout << "GREEN = " << GREEN << endl; 
}