#include <iostream>
#include <sstream> 
#include <algorithm>

#define replace std::replace
#define string  std::string
#define stringstream std::stringstream
#define cout std::cout
#define cin std::cin
#define endl std::endl

int main(){
    freopen("data","r",stdin);
    string line;
    cin >> line;
    replace( line.begin(), line.end(), '-', '\n');
    stringstream sstream;
    sstream <<line;
    while(sstream>>line){
        cout << "[" << line.length() << "]  " << line << endl ; 
    }
}