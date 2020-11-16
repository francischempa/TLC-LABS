#include <iostream>
using namespace std;

int getIntFromUser(){
	int value;
	cin>> value ;
	return value;
}

void multiplication(int value){
	for (int index = 0; index < 20 ; index ++ ){
		cout << value << " x " << index+1 <<  " = " << (index+1)*value << endl;
	}
}

void notDivisibleByX(int value){
	if(value <= 1 ){
		cout << "Enter a different number !!! \n";
		return;
	}
	int count = 0;
	int index = 1;
	while(1){
		if(count == 30) break;
		if(index % value){
			count++;
			cout << count << ") " << index << " is not divisible by " << value << endl;
		}
		index++;
	}
}


int main(){
	int value = 0;
	while(1){
		cout << "Enter a number : " << endl << ">>> " ;
		value = getIntFromUser();
		if( value % 2 != 0){
			notDivisibleByX( value );
		}else{
			multiplication( value );
		}
	}

}
