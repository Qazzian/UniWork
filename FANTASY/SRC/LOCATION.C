/* location.c */

#include <stdio.h>
#include <errno.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <string.h>
#include "location.h"
#include <dirent.h>
#include <stdlib.h>
#include "fan_err.h"

/* reads the current location of the player or other denizen from file */
char *get_current_location(char *location_path) {

    const char *empty_string = ""; /* what it says! */
    const int bufsize = 80; /* input parameter for read */
    int current_loc_d;      /* file handle for current location */
    int in;                 /* return value of read */
    char input_buffer[80];  /* holds characters read */
    int space_left;         /* checks that location path fits where_am_i */
    char *where_am_i;       /* return value of get_current_location */
    
    if ((where_am_i = malloc(sizeof(char)*MAX_LOCATION_LENGTH)) == NULL) {
       perror(FAN_SYS_MALLOC);
       return NULL;
    }

    (void)strcpy(where_am_i,empty_string);
 
    /* read from location path storing result in where_am_i */

    if ((current_loc_d = open(location_path, O_RDONLY)) == -1) {
       perror(FAN_SYS_LOC);
       return NULL;
    }

    space_left = MAX_LOCATION_LENGTH;

    do { 
      if ((in = read(current_loc_d,input_buffer,bufsize))== -1) {
         perror(FAN_SYS_READ);
         if (close(current_loc_d) == -1) 
            perror(FAN_SYS_CLOSE);
         return NULL;
      }
      (void)strncat(where_am_i,input_buffer,in);
      space_left -= in;
    } while (in > 0 && space_left >= in);

    if (close(current_loc_d) == -1 ) {
       perror(FAN_SYS_CLOSE);
       return NULL;
    }

    /* and return the result - where_am_i */
    
    return where_am_i;
}


/* write a new value to location_path */

int set_current_location(char *location_path, char *new_location) {
    
    int current_loc_d; /* file handle for location_path */
    
    if ((current_loc_d = creat(location_path, O_WRONLY)) == -1) {
       perror(FAN_SYS_CREAT);
       return -1;
    }
    if (lockf(current_loc_d,F_LOCK,0) == -1) {
       perror(FAN_SYS_LOCKF);
       return -1;
    }
    if (write(current_loc_d,new_location,strlen(new_location)) == -1) {
       perror(FAN_SYS_WRITE);
       return -1;
    }
    if (close(current_loc_d) == -1 ) {
       perror(FAN_SYS_CLOSE);
       return -1;
    }
    return 0;
}

/* returns a random exit from from_location */

char *any_exit(char *from_location) {
   struct stat buf;    /* result of stat */
   DIR *dir_pointer;   /* return value of opendir */
   struct dirent *dp;  /* current directory entry */
   size_t ex;          /* number of exits found */

   char *way_out[MAX_EXITS_FROM_LOC]; /* names of exits found */

   if (chdir(from_location) == -1) {
      perror(FAN_SYS_LOC);
      return NULL;
   }

   /* search through current working directory for exits */
   if ((dir_pointer = opendir(".")) == NULL) {
      perror(FAN_SYS_OPENDIR);
      return NULL;
   }

   ex = -1;
   for (dp = readdir(dir_pointer); dp != NULL; dp = readdir(dir_pointer)) {
       if (stat(dp->d_name,&buf) == -1) {
          perror(FAN_SYS_STAT);
          return NULL;
       }
       if (S_ISDIR(buf.st_mode) && strncmp(".",dp->d_name,1)) 
          way_out[++ex] = (char *)strdup(dp->d_name);
   }

   if (closedir(dir_pointer) == -1) {
      perror(FAN_SYS_CLOSEDIR);
      return NULL;
   }
   /* end of loop through current working directory */

   if (ex!=-1) return way_out[(rand()%(ex+1))]; /* choose an exit */
   else return from_location; /* no exits found - return `from_location' */
}

