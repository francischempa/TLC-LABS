#include <iostream>

#define string std::string
#define cout std::cout
#define cin std::cin
#define endl std::endl

int main(){

    int operand1,operand2;
    char opp = '0';

    while(1){
        
        cout << "\nPlease input two operands: " ; 
        cin >> operand1 >> operand2 ;
        cout << "input an operator: " ;
        cin >> opp ;

        switch (opp){
            case '+':{
                cout << "Adding " << operand1 << " and " << operand2 << " = " << operand2 + operand1 << endl;
            }
            break;
            case '*':{
                cout << "Multiplying " << operand1 << " and " << operand2 << " = " << operand2 * operand1 << endl;
            }
            break;
            case '-':{
                cout << "Stubtracting " << operand2 << " from " << operand1 << " = " << operand1 - operand2 << endl;
            }
            break;
            case '/':{
                cout << "Dividing " << operand2 << " by " << operand1 << " = " << operand1 / (double)operand2 << endl;
            }
            break;
            default:{
                cout << "Unknown Operator ! \n";
            }
        } 
    }

}