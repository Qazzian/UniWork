/* quench.c *//* quench.c */

#include <stdio.h>
#include "location.h"
#include "movable_object.h"
#include "fan_err.h"

int main(int argc, char **argv) {
   movable_object_t *thing;
   if (argc < 2) {
      perror(FAN_ERR_LIGHT);
      exit(-1);
   }
   if (chdir(PLAYERS_STUFF)) {
      perror(FAN_ERR_PLAY_STUFF);
      exit(-1);
   }
   if ((thing = read_movable_object(argv[1])) == NULL) {
      perror(FAN_ERR_LIGHT);
      exit(-1);
   }
   if (!strcmp(thing -> class,LIGHT_SOURCE)) {
      thing -> value = 0;
      if (write_movable_object(PLAYERS_STUFF,thing) == -1) {
        perror(FAN_ERR_PLAY_STUFF);
        exit(-1);
      }
      if (system("look") == -1) {
      perror(FAN_ERR_LOOK);
      exit(-1);
      }
      exit(0);
   }
   printf("Can't quench %s\n",thing -> name);
   exit(-1);
}

