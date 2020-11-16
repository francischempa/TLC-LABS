#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <vector>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

using namespace std;

void changeDir(vector<char*>& args ){   
    if(  chdir( args[1] ) == 0){
        char buf[500] {}; 
        getcwd(buf,500);
        setenv("PWD",buf,1);
    }
}

int main(int argc, char ** argv){

    vector<char*> args;
    int pid;
    int status; 

    while(1){    
        args.clear();
        cout << "user@localhost $ ";    
        char command[1000];
        cin.getline(command,1000); 


        char* token = strtok(command, " ");  
        while (token != NULL){ 
            args.push_back(token);
            token = strtok(NULL, " ");  
        }  

        if( strcmp(args[0],"exit") == 0 ) break;
        if( strcmp(args[0],"cd") == 0 ) {
            changeDir(args);
            continue;
        }

        args.push_back((char *) NULL);

        pid = fork();

        if(pid == 0){

            execvp(args[0], args.data());
            exit(-1);
        }
        pid = wait (&status);

    }

}