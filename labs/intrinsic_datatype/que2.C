#include <iostream>

#define cout std::cout
#define cin std::cin
#define endl std::endl

int main(){
    int value;
    int *pointerToValue = &value;
    int &referenceToValue = value;
    const int constantValue = 1001;

    value = 5;
    (*pointerToValue)++;

    referenceToValue++;

    cout << value << endl;

    // compiler output
    // error: assignment of read-only variable 'constantValue'
    // constantValue = 101;

    int literalValue = 0xf3f2;
    cout << "0xf3f2 = " << literalValue << endl ;
    
    literalValue = 0437;
    cout << "0437 = " << literalValue << endl ;

    literalValue = 'a';
    cout << "'a' = " << literalValue ;
    
     

}
