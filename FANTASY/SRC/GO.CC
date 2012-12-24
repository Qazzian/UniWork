/* go.c */

#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/param.h>
#include <unistd.h>
#include <sys/stat.h>
#include <errno.h>
#include <stdio.h>
#include <dirent.h>
#include "location.h"
#include "fan_err.h"

int main(int argc, char **argv) {
   char *newloc;
   struct stat buf;

   if (argc != 2) {
     fprintf(stderr,"Can't go!\n");
     exit(-1);
   }

   if ((newloc = malloc(MAX_LOCATION_LENGTH)) == NULL) {
      perror(FAN_SYS_MALLOC);
      exit(-1);
   }
   if (chdir(get_current_location(PLAYERS_LOCATION))) {
      perror(FAN_ERR_PLAY_LOC);
      exit(-1);
   }
   if (stat(argv[1],&buf) || 
       strstr(argv[1],"..") || strstr(argv[1],"/")) {
      printf("Can't go %s\n",argv[1]);
      exit(-1);
   }
   if (realpath(argv[1],newloc) == NULL) {
      perror(FAN_SYS_LOC);
      exit(-1);
   }
   if (set_current_location(PLAYERS_LOCATION,newloc) == -1) {
      printf("Can't go %s\n",argv[1]);
      exit(-1);
   }
   system("look");
   exit(0);

}


