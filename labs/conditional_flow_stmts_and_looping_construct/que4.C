#include <iostream>
#include <vector>
#include <algorithm>

#define string std::string
#define cout std::cout
#define cin std::cin
#define endl std::endl

int main(){
    string line = "";

    char c;
    while (cin.get(c))
        line.push_back(c);

    reverse(line.begin(),line.end());
    cout <<line<<endl;

}