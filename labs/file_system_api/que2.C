#include <iostream>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>
#include <time.h>
#include <string>
using namespace std;

void file_type(struct stat &sbuf){
	switch(sbuf.st_mode & S_IFMT){
		case S_IFREG:
			cout << "-" ;
			break;
		case S_IFDIR:
			cout << "d" ;
			break;
	}
}

void file_mode(struct stat &sbuf){
	string m = "---------";
	m[0] = (0400 & sbuf.st_mode)?'r':'-';
	m[3] = (0040 & sbuf.st_mode)?'r':'-';
	m[6] = (0004 & sbuf.st_mode)?'r':'-';
	
	m[1] = (0200 & sbuf.st_mode)?'w':'-';
	m[4] = (0020 & sbuf.st_mode)?'w':'-';
	m[7] = (0002 & sbuf.st_mode)?'w':'-';

	m[2] = (0100 & sbuf.st_mode)? ( 04000&sbuf.st_mode)?'s':'x'  : (04000&sbuf.st_mode)?'S':'-';
	m[5] = (0010 & sbuf.st_mode)? ( 02000&sbuf.st_mode)?'s':'x'  : (02000&sbuf.st_mode)?'S':'-';  
	m[8] = (0001 & sbuf.st_mode)? ( 01000&sbuf.st_mode)?'t':'x'  : (01000&sbuf.st_mode)?'T':'-';  
	cout << m ;
}

int main(int argc,char ** argv){
	
	struct stat sbuf;
	for (int i = 2;i<argc;i++){
		stat(argv[i],&sbuf);
		switch(sbuf.st_mode & S_IFMT ){
			case S_IFREG:
				cout << "-" ;
				break;
			case S_IFDIR:
			       	cout << "d" ;
				break;
		}
		file_mode(sbuf);
	
        	cout << " " << sbuf.st_nlink << " ";
		cout << sbuf.st_uid << " ";
		cout << sbuf.st_gid << " ";
		cout << sbuf.st_rdev << " ";
		cout << sbuf.st_size << " ";
		cout << sbuf.st_blksize << " ";
		cout << sbuf.st_blocks << " ";
	
	
		time_t t = sbuf.st_atim.tv_sec;
		char *dt = ctime(&t);
		cout << " " << argv[i] << " 		" <<dt;
	}

	/*cout << sbuf.st_mtim.tv_sec << endl;
	cout << sbuf.st_ctime.tv_sec << endl;
*/

}
