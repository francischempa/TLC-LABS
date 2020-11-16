#include <stdio.h>
#include <iostream>
#include <sstream> 
#include <algorithm>

#define replace std::replace
#define string  std::string
#define stringstream std::stringstream
#define cout std::cout
#define cin std::cin
#define endl std::endl

typedef string stenArray[10];

int main(){
    stenArray stringArray;
    string line;
    int count = 0;
    while(cin>>line){
        stringArray[count++] = line;
        if(count==10)break;
    }
    int size = stringArray[0].length();
    int bestIndex = 1;
    for( int index = 1 ; index < 10 ; index++ ){
        int length = stringArray[index].length();
        if( length > size ){
            bestIndex = index;
            size = length;
        }
    }

    cout << "String with largest size is " << stringArray[bestIndex] <<endl;

}