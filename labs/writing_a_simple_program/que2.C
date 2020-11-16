#include <iostream>
#include <vector>
#include <algorithm>

#define intvec std::vector<int>
#define cout std::cout
#define cin std::cin
#define endl std::endl

// This function returns the mean value
double mean(intvec &listOfNumber);

// This function returns the mode value
int mode(intvec listOfNumber);

// This function returns the median value
int median(intvec listOfNumber);


int main(){  
    intvec listOfNumber;
    cout << "Please enter five numbers \n";
    for ( int index = 0 ; index < 5 ; index++ ){
        cout << index+1 << ".  >>  " ;
        int value;
        cin >> value ;
        listOfNumber.push_back(value);
    }
    
    cout << "Mean = " << mean(listOfNumber) << endl;
    cout << "Mode = " << mode(listOfNumber) << endl;
    cout << "Median = " << median(listOfNumber) << endl;
    
    return 0;
}

double mean(intvec &listOfNumber){
    int sum = 0;
    for(int index = 0 ; index < 5 ; index++ ){
        sum += listOfNumber[index];
    }
    return (sum/5.0);
}

int mode(intvec listOfNumber){
    sort(listOfNumber.begin(),listOfNumber.end()); 

    int current = listOfNumber[0];
    int best = current;

    int currrentCount = 1;
    int bestCount = currrentCount;

    for( int index = 1 ; index < 5 ; index++ ){
        if( current == listOfNumber[index]){
            currrentCount++;
            if(currrentCount > bestCount && best != current){
                best = current;
            }
        }else{
            current = listOfNumber[index];
            currrentCount = 1;
        } 
    }
    return best;
}

int median(intvec listOfNumber){
    sort(listOfNumber.begin(),listOfNumber.end());
    return listOfNumber[2];
}