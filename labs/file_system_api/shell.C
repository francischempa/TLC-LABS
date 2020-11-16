#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>
#include <string>
#include <string.h>
#include <sstream>
#include <iostream>
#include <map>
#include <fcntl.h>
#include <vector>
#include <queue>
#include <regex>
#include <utility>
#include <signal.h>

#define MAX_CMD_LEN 10001

using namespace std;

void my_handler(int s){

}

namespace inbuilt{

    void quit(vector<char*>& args ){
        exit(0);
    }

    void echo(vector<char*>& args){
        for(vector<char*>::iterator it = args.begin(); it!=args.end() ; it++){
            if(it == args.begin()) continue;
            if(*it == (char*)NULL) break; 
            cout << *it << " ";
        }
    }

    void changeDir(vector<char*>& args ){  
        if(chdir(args[1]) == 0){
            char buf[500] {}; 
            getcwd(buf,500);
            setenv("PWD",buf,1);
        }
    }
}

namespace mystruct{

    struct ProcessFileDescriptors{
        int fd[3]={0,1,2};
    };

    struct Process{
        char *cmd;
        ProcessFileDescriptors fd;
    };

}
namespace util{

    void closeAll(vector<int> pipe){
        for(unsigned int index = 0; index< pipe.size();index++)
        close(pipe[index]);
    }

    vector<mystruct::Process> splitCommand(char *command){
        char *start=command;
        char *ptr = command;
        vector<mystruct::Process> readyProc;
        queue<mystruct::Process> temProcVec; 
        
        while(*ptr!='\0'){  
            if(*ptr == '|'){
                *ptr = '\0';
                mystruct::Process proc;
                proc.cmd = start;
                start = ptr+1;
                ptr = start;
                readyProc.push_back(proc); 
            }else{
                ptr+=1;
            }
        } 
        mystruct::Process proc;
        proc.cmd = start;
        readyProc.push_back(proc); 
        

        return readyProc;
    }

    void releaseOnFail(mystruct::ProcessFileDescriptors & fd){
        for(int index = 0; index < 3 ;index++){
            if(index != fd.fd[index]){ 
                close(index);
            }
        }
    }
    

    void resetDescriptors(mystruct::ProcessFileDescriptors & fd){
        for(int index = 0; index < 3 ;index++){
            
            if(index != fd.fd[index]){ 
                close(index); 
                dup(fd.fd[index]);  
            }   
        }
    }

    //  ls -al 

    string find(char *command,const char *find, int s_count){
        char *ptr;
        ptr =  strstr(command, find);
        if( ptr != NULL ) { 
            char * begin;
            begin = ptr;
            ptr = ( *( ptr+s_count ) == ' ' ) ? (ptr+s_count+1) : (ptr+s_count) ;
            char * end;
            end = ptr;
            while(end++, (*end) != ' ' && (*end) != '\0'); 
            string str = string(ptr,end); 
            while( (*begin)=' ', begin++ , begin!=end);
            *begin=' ';
            return str;
        }
        return "";
    }

    mystruct::ProcessFileDescriptors handleRedirections(char *command){
        mystruct::ProcessFileDescriptors fd;
        string token[] = {"<",">","2>"};
        for(int index = 0 ; index < 3 ; index++){
            string ret = util::find(command,token[index].c_str(),token[index].length());
            if(ret.length()){
                if(ret[0] == '&'){
                    fd.fd[index] = ret[1] - '0';
                }else{
                    fd.fd[index] = open(ret.c_str(), O_RDWR|O_CREAT|O_APPEND, 0600);
                    fcntl(fd.fd[index], F_SETFD, FD_CLOEXEC);
                }
            }
        }
        return fd;
    }
}

int main(int argc, char ** argv){

    signal (SIGINT,my_handler); 

    int pid;
    int status; 
    vector<char*> args;
    map<string,void (*)(vector<char*>&)> MAP; 

    MAP.insert(pair<string,void (*)(vector<char*>&)>("cd",inbuilt::changeDir));
    MAP.insert(pair<string,void (*)(vector<char*>&)>("echo",inbuilt::echo));
    MAP.insert(pair<string,void (*)(vector<char*>&)>("exit",inbuilt::quit));


    while(1){        
        char command[MAX_CMD_LEN];
        
        cout << "\033[1;31m" << endl << getenv("LOGNAME")  << "@localhost:" << "\033[34m" << getenv("PWD") << " $ " << "\033[0m";
        
        cin.getline(command,MAX_CMD_LEN);  
        if(strlen(command) == 0) continue;
         
        vector<mystruct::Process> processes = util::splitCommand(command);

        
        int pipePair[2];
        vector<int> pipes;
        for(unsigned int index=1; index < processes.size(); index++){
            pipe(pipePair); 
            pipes.push_back(pipePair[0]);
            pipes.push_back(pipePair[1]); 
        }

        for(unsigned int index = 0 ; index<processes.size() ; index++){
            args.clear();
            auto &proc = processes[index];
            
            mystruct::ProcessFileDescriptors fileDescriptors = util::handleRedirections(proc.cmd);

            char* token = strtok(proc.cmd, " ");   
            while (token != NULL){ 
                args.push_back(token);
                token = strtok(NULL, " ");  
            }  
            
            args.push_back( (char*)NULL ); 
            if(MAP.find(args[0]) != MAP.end()) {
                MAP[args[0]](args);
                continue;
            } 

            int usedPipes[2] = {-1,-1};
            pid = fork();
            
            if(pid == 0){
                // > < 2>
                util::resetDescriptors(fileDescriptors); 
                
                if(processes.size()>1){
                    if(index==0){ 
                        usedPipes[1] = pipes[pipes.size()-1];
                        close(1);
                        dup2(usedPipes[1],1);
                        pipes.pop_back();
                        util::closeAll(pipes); 
                        

                    }else if(index == processes.size()-1){
                        usedPipes[0] = pipes[pipes.size()-1];  
                        close(0); 
                        dup2(usedPipes[0],0); 
                        pipes.pop_back();
                        close(usedPipes[1]); 
                        util::closeAll(pipes); 
                        
                    }else{ 
                        usedPipes[0] = pipes[pipes.size()-1]; 
                        usedPipes[1] = pipes[pipes.size()-2]; 
                        dup2(usedPipes[0],0);  
                        dup2(usedPipes[1],1); 
                        pipes.pop_back();
                        pipes.pop_back();
                        util::closeAll(pipes); 
                        
                    } 
                } 

                int exitCode = execvp(args[0], args.data());
                util::releaseOnFail(fileDescriptors);
                cerr << "Exited with code ";
                cerr << exitCode ;
                cerr << "\nUnable to execute program\n\n";
                exit(EXIT_FAILURE);
            }
            if(processes.size()>1){
                if(index==0 || index == processes.size()-1){
                    close(pipes.back());
                    pipes.pop_back();
                }else{
                    close(pipes.back());
                    pipes.pop_back();
                    close(pipes.back());
                    pipes.pop_back();
                }
            }

            
 
        }
        for(unsigned int index =  0 ; index<processes.size(); index++){
            pid = wait (&status);
        }
    }

}
