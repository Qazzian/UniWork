/* start.c */

#include <stdio.h>
#include <errno.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <dirent.h>
#include "location.h"
#include "fan_err.h"

int main(int argc, char **argv) {
   struct stat buf;
   DIR *dir_pointer;
   struct dirent *dp;

   if (chdir(PLAYERS_STUFF)) {
      perror(FAN_ERR_PLAY_STUFF);
      exit(-1);
   }
   if ((dir_pointer = opendir(".")) == NULL) {
      perror(FAN_ERR_PLAY_STUFF);
      exit(-1);
   }
   for (dp = readdir(dir_pointer); dp != NULL; dp = readdir(dir_pointer)) {
     if (stat(dp->d_name,&buf) == -1) {
        perror(FAN_ERR_PLAY_STUFF);
        exit(-1);
     }
     if (S_ISREG(buf.st_mode)) unlink(dp->d_name);
   }
   if (closedir(dir_pointer)) {
      perror(FAN_ERR_PLAY_STUFF);
      exit(-1);
   }
   if (chdir(FANTASY_ETC)) {
      perror(FAN_ERR_ETC);
      exit(-1);
   }
   if (system("tar xf initial_world.tar") == -1) {
      perror(FAN_ERR_WORLD);
      exit(-1);
   }
   if (set_current_location(PLAYERS_LOCATION,STARTING_LOCATION) == -1) {
      perror(FAN_ERR_START);
      exit(-1);
   }
   if (set_current_location(DRAGONS_LOCATION,DRAGONS_LAIR) == -1) {
      perror(FAN_ERR_LAIR);
      exit(-1);
   }
   /*commented out - to be re-introduced when Practical Sheet 9 is done*/
   if (system("dragon") == -1)
      perror(FAN_WARN_DRAGON);

   if (system("look") == -1) {
      perror(FAN_ERR_LOOK);
      exit(-1);
   }
   exit(0);
}
