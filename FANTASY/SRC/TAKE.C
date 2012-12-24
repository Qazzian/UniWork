/* take.c */

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


int carrying_capacity() {
   struct stat buf;
   DIR *dir_pointer;
   struct dirent *dp;
   int count = MAX_THINGS_HELD;
   if (chdir(PLAYERS_STUFF)) /* see if we're holding the object */ {
      perror(FAN_ERR_PLAY_STUFF);
      exit(-1);
   }
   dir_pointer = opendir(".");
   for (dp = readdir(dir_pointer); dp != NULL; dp = readdir(dir_pointer)) {
      if (stat(dp->d_name,&buf)) {
        perror(FAN_ERR_PLAY_STUFF);
        exit(-1);
      }
      if (S_ISREG(buf.st_mode)) count--;
   }
   closedir(dir_pointer);
   return(count);
}

int weight_capacity() {
   struct stat buf;
   DIR *dir_pointer;
   struct dirent *dp;
   movable_object_t *this_thing;
   int weight = MAX_WEIGHT_HELD;
   if (chdir(PLAYERS_STUFF)) /* see if we're holding the object */ {   
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
         if ((this_thing = read_movable_object(dp->d_name)) == NULL) {
            perror(FAN_ERR_PLAY_STUFF);
            exit(-1);
         }
         weight-= this_thing -> weight;
      }
   }
   if (closedir(dir_pointer)) {
    perror(FAN_ERR_PLAY_STUFF);
    exit(-1);
   }
   return(weight);
}

int main(int argc, char **argv) {
   movable_object_t *thing;
   int weight_left;
   if (argc != 2) {
     printf("Take what?\n");
     exit(-1);
   }
   if (carrying_capacity() == 0) {
      printf("Can't carry anything else\n");
      exit(-1);
   }
   chdir(get_current_location(PLAYERS_LOCATION));
   if ((thing = read_movable_object(argv[1]))==NULL) {
      printf("Can't take %s\n",argv[1]);
      exit(-1);
   }
   if ((weight_left = weight_capacity()) < thing -> weight) {
      printf("%s weighs %d kilograms\nI can only carry %d kilograms",
        thing -> name, thing -> weight, weight_left);
      exit(-1);
   }
   chdir(get_current_location(PLAYERS_LOCATION));
   unlink(argv[1]);
   chdir(PLAYERS_STUFF);
   write_movable_object(PLAYERS_STUFF,thing);
	printf("You have taken %s\n", thing->name);
   exit(0);
}
