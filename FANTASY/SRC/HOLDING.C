 /* holding.c */

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
     if (stat(dp->d_name,&buf)) {
       perror(FAN_ERR_PLAY_STUFF);
       exit(-1);
     }
     if (S_ISREG(buf.st_mode)) printf("%s\n",dp->d_name);
   }
   if (closedir(dir_pointer)) {
     perror(FAN_ERR_PLAY_STUFF);
     exit(-1);
   }
   exit(0);
}
