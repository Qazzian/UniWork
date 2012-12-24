/**
 * waitcmd.c
 *
 * moniters files and invokescertain commands when accessed or modified.
 * If no command line argumants are given then commands are read from the 
 * waitcmd.ini file and monitoring starts for all files mentioned.
 * If command line argumants are given then they are added to the ini file.
 *	If an instance of waitcmd is already running then it will be refreshed 
 *  to include the new commands. 
 *  Monitoring is not started when new commands are given.
 */
int main (int argc, char *argv[])
{
	if(argc > 1) {
		if(argv[1] == "-h")
			printHelp();
		else
			addToIni(argc, argv);
	}
	else
		readIni()
		runMonitor();
		printf("Monitoring files (for help type \"waitcmd -h\")\n");
	return 0;
}

/**
 * Adds the new command to the log file.
 */
int addToIni(int argc, char *argv[])
{

}

/**
 * Reads the Ini file and interprets the commands.
 */
int readIni(void)
{
	
}
		
/**
 * runs the file monitor
 */
int runMonitor(void)
{

}

/**
 * Prints a usefull help message.
 */
void printHelp(void)
{

}
