/*  
    score.c
	Adds up the score for the player.

    Author: Ian Wallis (ifw9)
*/
#include <stdio.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <dirent.h>
#include "movable_object.h"
#include "location.h"
#include "fan_err.h"

/*
	This is a simple function which does a test to see if the give 
	movable_object_t is of class TREASURE. 
	Paramiter	movable_object_t *thing		A pointer to the object to test
	returns		int 					1		If the object is of class TREASURE
											0		Otherwise.
*/
int is_treasure(movable_object_t *thing) {
    if(thing->class == TREASURE){
	    return 1;
	} else return 0;
}

int main(int argc, char **argv) {
	int the_score = 0;		/* Stops random initial value */
	movable_object_t *thing;
	struct stat buf;
	DIR *dir_pointer;
	struct dirent *dp;
	
	if (chdir(PLAYERS_STUFF)) { /* Check and change to Players holding dir */
		perror(FAN_ERR_PLAY_STUFF);
		exit(-1);
	}
	if ((dir_pointer = opendir(".")) == NULL) {
		perror(FAN_ERR_PLAY_STUFF);
		exit(-1);
	}
	for (dp = readdir(dir_pointer); dp != NULL; dp = readdir(dir_pointer)) {
		if (stat(dp->d_name,&buf)) {
			perror(FAN_ERR_PLAY_STUFF);
			exit(-1);
		}
		if (S_ISREG(buf.st_mode)) {
			if(( thing = read_movable_object(dp->d_name))!=NULL);
			if(is_treasure(thing)) {
				the_score += thing->value;
			}
		}
	}
	if (closedir(dir_pointer)) {
		perror(FAN_ERR_PLAY_STUFF);
		exit(-1);
	}

	printf("Your score is %i\n",the_score);
	exit(0);
}
