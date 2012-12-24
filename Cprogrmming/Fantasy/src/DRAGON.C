/* dragon.c */

#include <errno.h>
#include <sys/types.h>
#include <signal.h>
#include <stdio.h>
#include <string.h>
#include "location.h"
#include "movable_object.h"
#include "fan_err.h"

void exit_prog(int i) {
  if (!strcmp(get_current_location(PLAYERS_LOCATION),
              get_current_location(DRAGONS_LOCATION))) {
    chdir(get_current_location(DRAGONS_LOCATION));
    printf("ooer - someone's put out my fire ... sssssss\n");
    unlink("dragon");
    chdir(get_current_location(PLAYERS_LOCATION));
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
    printf("I'm off ... see you around ... \
      heh, heh, heh ... \n");
    the_dragon = read_movable_object(dragon);
    unlink(dragon);
    exit = any_exit("."); 
    realpath(exit,newloc);
    printf("The dragon lumbers towards the exit %s\n",exit);
    set_current_location(DRAGONS_LOCATION,newloc);
    write_movable_object(
      get_current_location(DRAGONS_LOCATION),the_dragon);
  }
  sigset(SIGUSR1,exit_prog);
}

int main(void) {
  pid_t pid;
 
  if ((pid = fork()) == -1)    {
     perror(FAN_SYS_FORK);
     exit(1);
  }
 
  if (pid == 0)    {
    setpgrp();
    write_movable_object(DRAGONS_LAIR,
       construct_movable_object("dragon",DENIZEN,
         "an enormous fire breathing dragon",5000,getpid()));
    set_current_location(DRAGONS_LOCATION,DRAGONS_LAIR);
    sigset(SIGUSR1, exit_prog);
    sigset(SIGUSR2, move_off);
    for(;;) {
      if (!strcmp(get_current_location(PLAYERS_LOCATION),
                  get_current_location(DRAGONS_LOCATION))) 
         printf("\nI am a fire-breathing dragon with pid %d\n",
           getpid());
      sleep(3);
    }
  }
}


