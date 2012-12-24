/* quit */

#include <sys/types.h>
#include <signal.h>
#include <stdio.h>
#include <errno.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <dirent.h>
#include "movable_object.h"
#include "location.h"
#include "fan_err.h"

int main(int argc, char **argv) {
  movable_object_t *the_dragon;

  if (chdir(get_current_location(DRAGONS_LOCATION)) == -1) {
     perror(FAN_ERR_DRAG_LOC);
     exit(-1);
  }
  if ((the_dragon = read_movable_object("dragon")) != NULL) {
    kill(the_dragon -> value, SIGKILL);
    unlink("dragon");
  }
  printf("Finishing the game\n");
  if (system("score") == -1) 
     perror(FAN_WARN_SCORE);
  printf("Don't forget to restore your path!\n"); 
  exit(0);
}
