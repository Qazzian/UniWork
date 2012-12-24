/*  
    drop.c
	Drops the named object from the players holding directory to the players current 
	location

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
#include "movable_object.h"
#include "location.h"
#include "fan_err.h"


int main(int argc, char **argv) {
   movable_object_t *thing;
	int weight_left;
	char *current_place = get_current_location(PLAYERS_LOCATION);
	
	if (argc != 2) {
		printf("Drop What?\n");
		exit(-1);
	}
	chdir(PLAYERS_STUFF);	/* Change to the holding dir then check that 
										the player holds the requested object. */
   if ( (thing = read_movable_object(argv[1])) == NULL ) {
      printf("Can't drop %s\n",argv[1]);
      exit(-1);
   }
	chdir(PLAYERS_STUFF);
   unlink(argv[1]);
   chdir(current_place);
   write_movable_object(current_place,thing);
	printf("You have dropped %s\n", thing->name);
   exit(0);
}
