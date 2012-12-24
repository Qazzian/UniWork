/* dragon.c */

#include <errno.h>
#include <sys/types.h>
#include <signal.h>
#include <stdio.h>
#include <string.h>
#include "location.h"
#include "movable_object.h"
#include "fan_err.h"

void make_changes() {
	int file_id;
	char *new_des = "You are standing in a gloomy cavern filled with a slight mist. The cave mouth is high above you and now impossible to reach from here. \n\nThere is a small lake at the bottom and a wooden door glows with blue-green phosphorescence in the darkest part of the cave.\n"; 
	
	file_id = creat(DESCRIPTION, 0655);
	write(file_id, new_des, strlen(new_des) );
}

void exit_prog(int i) { 
	if (!strcmp(get_current_location(PLAYERS_LOCATION), 
														get_current_location(DRAGONS_LOCATION))) {
		chdir(get_current_location(DRAGONS_LOCATION));
		printf("ooer - someone's put out my fire ... sssssss\n");
		unlink("dragon");
		chdir(get_current_location(PLAYERS_LOCATION));
		if ( !strcmp(get_current_location(DRAGONS_LOCATION), DRAGONS_LAIR)){
			printf("As the steam clears you  see that the water from the fire extinguisher \nhas collected to form a small lake at the bottom of the cavern.\nWhere the dragon was, there now stands a goose. \nThe goose runs to a small nest next to the lake.\n");
			make_changes();
			system("eggs");
		}
		exit(0);
	}
}

void move_off(int i) {
	movable_object_t *the_dragon;
	char *dragon = "dragon";
	char *exit;
	char *newloc= (char *)malloc(MAX_LOCATION_LENGTH);
	
	if (!strcmp(get_current_location(PLAYERS_LOCATION),
													get_current_location(DRAGONS_LOCATION))) {
		chdir(get_current_location(DRAGONS_LOCATION));
		printf("I'm off ... see you around ... \n heh, heh, heh ... \n");
		the_dragon = read_movable_object(dragon);
		unlink(dragon);
		exit = any_exit("."); 
		realpath(exit,newloc);
		printf("The dragon lumbers towards the exit %s\n",exit);
		set_current_location(DRAGONS_LOCATION,newloc);
		write_movable_object(get_current_location(DRAGONS_LOCATION),the_dragon);
	}
	sigset(SIGUSR1,exit_prog);
}

int main(void) {
	pid_t pid;
	char *location, *tmpchar;
	int attack;
 
	if ((pid = fork()) == -1)	 {
		perror(FAN_SYS_FORK);
		exit(1);
	}
	if (pid == 0)	 {
		setpgrp();
		write_movable_object(DRAGONS_LAIR,
			construct_movable_object("dragon",DENIZEN,
										"an enormous fire breathing dragon",5000,getpid()));
		/*location = malloc(MAX_LOCATION_LENGTH);
		tmpchar = DRAGONS_LAIR;
		realpath(tmpchar,location);*/
		set_current_location(DRAGONS_LOCATION,DRAGONS_LAIR);
		sigset(SIGUSR1, exit_prog);
		sigset(SIGUSR2, move_off);
		for(;;) {
			if (!strcmp(get_current_location(PLAYERS_LOCATION),
														get_current_location(DRAGONS_LOCATION))) {
				printf("\nI am a fire-breathing dragon with pid %d\n", getpid());
				attack++;
			} else {
				attack=0;
			} /* Kill the player if they don't act first */
			if(attack==10) { 
				printf("\nThe Dragon opens its mouth and engulfs you with fire\nYou are dead\n");
				system("quit");
			}
			sleep(3);
		}
	}
}


