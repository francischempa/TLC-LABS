HINSTANCE gNoaaDllInst = 0;
char gMySignatureStr[] = "MySg"; // a 4 character identifier you wish to use for your application
HWND gMyMainWindowHWND = 0;
char gMyMainWindowClassName[256];


long MyStartupTasks(HWND myMainWindowHWND, char * myMainWindowClassName) // call this when you program is starting 
{
    long errorCode = 0;
    gMyMainWindowHWND = myMainWindowHWND; // record the value of your main window handle
    if (myMainWindowClassName)
        strcpy(gMyMainWindowClassName, myMainWindowClassName);
    return errorCode;
}


void MyShutdownTasks(void) {
    // say goodbye to ALOHA if it is running
    CallNEBye(); // this will unload the NOAA 32 dll
}


/////////////////////
/////////////////////
void LoadNoaaDll(void) {
    char dllPath[256];
    if (gNoaaDllInst)
        return; //if already loaded, don't reload
    GetWindowsDirectory(dllPath, 255);
    strcat(dllPath, "\\NOAA_32.DLL");
    gNoaaDllInst = LoadLibrary(dllPath);
}


void CallNERegister(void) {
    char sigStr[6];
    char fullPath[256];
    char humanName[64];
    FARPROC proc = NULL;
    LoadNoaaDll();
    if ((UINT) gNoaaDllInst > 32) {
        //we have the library
        proc = GetProcAddress(gNoaaDllInst, "NERegister");
        if (proc) {
            my_getindstring(humanName, 1000, 1); //ALOHA

            ( * proc)(gMySignatureStr,
                gMyMainWindowHWND,
                gMyMainWindowClassName,
                "", //messageStringForHola,unused
                "", // human name,unused
                "", //wakeUpTopicString,unused
                "", // fullPath,unused
                0, //SA_APPTASK,unused
                0, //unused
                0); //unused 
        }
    }
}



void CallNEBye(void) {
    char sigStr[6];
    FARPROC proc = NULL;
    if ((UINT) gNoaaDllInst > 32) {
        //we have the library
        proc = GetProcAddress(gNoaaDllInst, "NEBye");
        if (proc) {
            ( * proc)(gMySignatureStr,
                gMyMainWindowHWND,
                gMyMainWindowClassName,
                ""); // messageStringForBye, unused
        }
        FreeLibrary(gNoaaDllInst);
        gNoaaDllInst = NULL;
    }
}



long CallNESendMessage(char * toSigStr, char * messageStr) {
    FARPROC proc = NULL;
    long err = -1;
    if ((UINT) gNoaaDllInst > 32) {
        //we have the library
        proc = GetProcAddress(gNoaaDllInst, "NESendMessage");
        if (proc) {
            err = (long)( * proc)(toSigStr, messageStr, FALSE, NULL, NULL);
        }
        return err;
    }
}



BOOL CallNEAppIsRunning(char * toSigstr) {
    FARPROC proc = NULL;
    BOOL isRunning = FALSE;
    LoadNoaaDll();
    if ((UINT) gNoaaDllInst > 32) {

        //we have the library
        proc = GetProcAddress(gNoaaDllInst, "NEAppIsRunning");
        if (proc) {
            isRunning = (BOOL)( * proc)(toSigStr);
        }
    }
    return isRunning;
}



long HandleNEMessage(void) {
    // check for a message and handle it
    FARPROC proc = NULL;
    long err = -1;
    long len;
    long maxLength = 1023;
    char msgStr[1024] = "";
    LoadNoaaDll();
    if ((UINT) gNoaaDllInst > 32) {
        //we have the library
        proc = GetProcAddress(gNoaaDllInst, "NEGetNextMessageLength");
        if (proc) {
            len = (long)( * proc)(gMySignatureStr);
            if (len > 0) {
                //we have a message
                proc = GetProcAddress(gNoaaDllInst, "NEGetNextMessage");
                if (proc) {
                    BOOL gotIt;
                    gotIt = (BOOL)( * proc)(sigStr, messageString, maxLength);
                    if (gotIt) {
                        // code to handle the message goes here
                    }
                }
            }
        }
    }
    return err;
}