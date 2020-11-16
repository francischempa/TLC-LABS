#include <iostream> 

#define cout std::cout
#define cin std::cin
#define endl std::endl

namespace my{

	int strcmp(const char *l, const char *r);
	
	int strlen(const char *s);
 	
	char *strcat(char *l, const char *r);
	
	char *strcpy(char *l, const char *r);
	
	char *toupper(char *s);
}

int main(){
	char foo[100];
	char bar[] = "Hello World";
	my::toupper(bar);
	cout << bar <<endl;

}

int my::strcmp(const char *l, const char *r){
	int i {0};
	while(i++,l[i]==r[i] && l[i] && r[i]);
	return (l[i] - r[i]);
}

int my::strlen(const char *s){
	int i {0};
	while(i++,s[i]);
	return i;
}

char * my::strcat(char *l, const char *r){
	int i = my::strlen(l);
	int j = 0;
	while(l[i++]=r[j++]);
	return l;
}

char * my::strcpy(char *l, const char *r){
	int i = -1;
	while(i++,l[i] = r[i]);
	return l;
}

char * my::toupper(char *s){
	int i=-1;
	while( i++ ,  s[i] =   (   s[i] > 96 && s[i] < 173  )   ?    s[i]-32    :   s[i]   );
	return s;

}
