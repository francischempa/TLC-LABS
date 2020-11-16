#include <iostream>

#define cout std::cout
#define cin std::cin
#define endl std::endl


int main(){
	constexpr int limit {10};
	char buffer[limit];
	
	char c{};
	int i = 0;
	int j = i;
	while(i < limit){
		c = getchar();
		j = i;
		buffer[i] = c;
		i = (c == '\n') ? limit  : i ;
		i = (c == 'A') ? limit : i ;
		i++;
	}
	buffer[j] = '\0';
	
	cout << j <<endl;
	cout << buffer <<endl;
}
