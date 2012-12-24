/* wield.c */

#include <stdio.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <errno.h>
#include <dirent.h>
#include <string.h>
#include <signal.h>
#include "movable_object.h"
#include "location.h"

void kill_targets(movable_object_t *the_weapon) {
  struct stat buf;
  DIR *dir_pointer;
  struct dirent *dp;
  movable_object_t *the_target;

  dir_pointer = opendir(".");
  for (dp = readdir(dir_pointer); dp != NULL; dp = readdir(dir_pointer)) {
    stat(dp->d_name,&buf);
    if (S_ISREG(buf.st_mode) && strncmp(".",dp->d_name,1) &&
       strcmp(dp->d_name,DESCRIPTION) && strcmp(dp->d_name,LIGHT)) {
       the_target = read_movable_object(dp->d_name);
       if (!strcmp(the_target -> class, DENIZEN)) 
          kill(the_target -> value, the_weapon -> value);
    }
  }
  closedir(dir_pointer);
}


int main(int argc, char **argv) {
  movable_object_t *the_weapon;

  if (argc != 2) {
    printf("Wield what?\n");
    exit(-1);
  };
  chdir(PLAYERS_STUFF);
  the_weapon = read_movable_object(argv[1]);
  chdir(get_current_location(PLAYERS_LOCATION));
  if (!strcmp(the_weapon -> class, WEAPON)) {
    kill_targets(the_weapon);
    exit(0);
  }
  fprintf(stderr,"Can't wield %s\n",the_weapon -> name);
  exit(-1);
}
