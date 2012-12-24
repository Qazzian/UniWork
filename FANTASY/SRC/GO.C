/*  
    go.c
	Moves the player to the given location (directory).

    Author: Ian Wallis (ifw9)
*/
#include <stdio.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <errno.h>
#include <dirent.h>
#include <string.h>
#include "location.h"
#include "fan_err.h"

/*
	Prints out a message indicating that the given location 
	is not valid and then exits the program.
*/
void no_go() {
	printf("\tCan't get there from here!\n");
	exit(-1);
}

int main(int argc, char **argv) {
	struct stat buf;
	char *moving_to;
	
	if (argc == 1) {
		/* choose a random exit using any_exit() function in location.c */
		argv[1] = any_exit(get_current_location(PLAYERS_LOCATION));
	}
	 
	if (argc > 2) { 
	   printf("\t\tGo Where?\n");
		exit(-1);
	}
		/* Allocate some memory to store the destinations name.
			If malloc fails, it returns a NULL value */
	if ((moving_to = malloc(MAX_LOCATION_LENGTH)) == NULL) {
		no_go();
	}
		/* Change to the players current location */
	if (chdir(get_current_location(PLAYERS_LOCATION))) {
		no_go();
	}
		/* Get info on the file. good check to see if the dir exists */
	if (stat(argv[1],&buf)) {
		no_go();
	}
		/* Get the actual path name for the new location. */
   if (realpath(argv[1], moving_to) == NULL) {
		no_go();
	}
		/* Test to see that the new location is within the game worlds limits 
			ie. there is no illegal acces outside of the game.
			Do this by compering the first 31 charactures of the file name with the 
			starting location as that is the length of the starting locations pathname. * /
	if (strncmp(moving_to, STARTING_LOCATION, 31) == 0) {
		no_go();
	}
		/* If all the previous tests pass, change the players location to the new
			location. */
	if ((set_current_location(PLAYERS_LOCATION, moving_to)) == -1) {
		no_go();
	}
	
	/* Look at the new location */
	if (system("look") == -1) {
		perror(FAN_ERR_LOOK);
		exit(-1);
	}
	exit(0);
	/*}*/
}
