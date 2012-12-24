/**
 * waitcmd.h
 */

#define INI_FILE "waitcmd.ini"	/* Name of file to store all commands. */

/**
 * type FILE_ENTRY defines a file a file to be moniterd 
 * and its assosiated commands
 */
typedef struct {
	int access;		/* Execute on access flag. */
	int modify;		/* Execute on modify flag. */
	char filepath[MAX_FILENAME_LEN];	/* Path of file to monitor. */
	char command[5][MAX_FILENAME_LEN];	/* The full command to execute. */
	char log_file[MAX_FILENAME_LEN];	/* The files associated error log file. */
}FILE_ENTRY;


/* Function Prototypes. */
int addToIni(int argc, char *argv[]);
void printHelp(void);
int runMonitor(void);
