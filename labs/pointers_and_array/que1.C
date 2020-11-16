#include <iostream>
#include <cstring>
#define cout std::cout
#define cin std::cin
#define string std::string

int main(){
	string word;
	cin >> word ;
	const char * NAME = word.c_str();
	for ( int index = 0 ; index < strlen(NAME) ; index ++ ){
		cout << NAME[index];	
	}
}	
