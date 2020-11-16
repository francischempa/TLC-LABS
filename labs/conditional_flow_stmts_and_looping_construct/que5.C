#include <iostream>
#include <sstream>
#include <iterator>
#include <vector>
#include <algorithm>

#define string std::string
#define cout std::cout
#define cin std::cin
#define endl std::endl
#define vector std::vector
#define istringstream std::istringstream
#define istream_iterator std::istream_iterator


int main(){

    string line;

    getline(cin,line);

    istringstream iss(line);

    vector<string> results((istream_iterator<string>(iss)), istream_iterator<string>());    

    string *bestElement;
    int bestLength = 0;
    for ( auto& element : results ){
        if(element.length() > bestLength){
            bestLength = element.length();
            bestElement=&element;
        }
    }

    cout << "Longest Word is " << *bestElement <<endl;
    cout << "Length is " << bestLength <<endl;

}