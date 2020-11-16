#include <iostream>
#include <iomanip>

#define cout std::cout
#define cin std::cin
#define endl std::endl

unsigned int rotate (unsigned int s, unsigned int r);

void printResults( unsigned int s, unsigned int R){
	cout << "original: " << s << endl << "rotated: " ;
	cout << std::hex << R << std::dec  <<endl;
}

int main(){
	unsigned int rotated=0;
	unsigned int original = 4;
	unsigned int n_rotations = 2;

	rotated = rotate( original, n_rotations );

	printResults(original,rotated);

	while(1){
		cout << "input two values: ";
		cin >> original >> n_rotations;
		rotated = rotate(original, n_rotations);
		printResults(original,rotated);
	}
}

unsigned int rotate (unsigned int s, unsigned int r){
	unsigned int NBITS = sizeof(int) * 8; 
	unsigned int bitsToSwitch = r % NBITS;
	s = (s << bitsToSwitch) | ( s >> (NBITS - bitsToSwitch));
	return s;
}
