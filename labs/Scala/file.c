int main(){ 
	MyStartupTasks(HWND myMainWindowHWND, char * myMainWindowClassName);
	CallNERegister(void);
	CallNESendMessage(char * toSigStr, char * messageStr);
	HandleNEMessage(void);
}