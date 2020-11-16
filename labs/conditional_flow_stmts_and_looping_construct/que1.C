#include <iostream>

#define string std::string
#define cout std::cout
#define cin std::cin
#define endl std::endl

int main(){
    
    const string input_line {"zz?aa?bb?cc?dd?ee?ff"};
    const int max_length = input_line.length();

    int count = 0;
    int i = 0;
    while(i < max_length){
        if(input_line[i] == '?') ++count;
        i++;
    }
    cout << count << endl ;

}