
/* look.c */

#include <stdio.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <errno.h>
#include <dirent.h>
#include <string.h>
#include "movable_object.h"
#include "location.h"
#include "fan_err.h"

/* displays a movable object */
int examine_and_display(char *thing) {
  movable_object_t *the_object; /* to hold result of read_movable_object */
  struct stat buf; /* to hold the result of stat */
  if (stat(thing, &buf)) 
    if (chdir(PLAYERS_STUFF)) /* see if we're holding the object */ {
      perror(FAN_ERR_PLAY_STUFF);
      exit(-1);
    }
  if (stat(thing, &buf)) {
    perror(FAN_ERR_LOOK);
    exit(-1);
  }
  if (S_ISDIR(buf.st_mode)) { /* the file is a directory */
    printf("%s is an exit\n",thing);
    exit(0);
  }

  /* the file is not a directory - it should be a movable object */
  if((the_object = read_movable_object(thing))==NULL) {
    perror(FAN_ERR_LOOK);
    exit(-1);
  }
  display_movable_object(the_object);
  exit(0);
}

/* tests whether a location is light or dark */
int islight() {
  int src, in; /* file handle for LIGHT; return value of read */
  char buf[2]; /* to hold characters obtained by read */

  if ((src = open(LIGHT, O_RDONLY)) == -1) {
     perror(FAN_ERR_LIGHT);
     exit(-1);
  }
  if ((in = read(src,buf,2)) == -1) {
     perror(FAN_ERR_LIGHT);
     if (close(src)) 
       perror(FAN_ERR_LIGHT);
     exit(-1);
  }
  if (close(src)) {
     perror(FAN_ERR_LIGHT);
     exit(-1);
  }
  buf[1] = 0; /* terminate the string held in buf */
  return atoi(buf);
}

/* tests whether or not we're carrying a light source, and
   whether or not it is lit
*/
int havelight() {

  movable_object_t *the_object;	/* result of read_movable_object */
  struct stat buf;		/* result of stat */
  DIR *dir_pointer;		/* directory handle - result of opendir */
  struct dirent *dp;		/* current directory entry */
  int result = 0;		/* return value - initially false */

  if (chdir(PLAYERS_STUFF)) {
    perror(FAN_ERR_PLAY_STUFF);
    exit(-1);
  }

  /* search the current working directory for a lit light source */
  if ((dir_pointer = opendir(".")) == NULL) {
    perror(FAN_ERR_PLAY_STUFF);
    exit(-1);
  }
  for (dp = readdir(dir_pointer); dp != NULL; dp = readdir(dir_pointer)) {
    if (stat(dp->d_name,&buf)) {
      perror(FAN_ERR_PLAY_STUFF);
      exit(-1);
    }
    if (S_ISREG(buf.st_mode) && (strcmp(dp->d_name,DESCRIPTION)) &&
          (strcmp(dp->d_name,LIGHT))) {
       if ((the_object = read_movable_object(dp -> d_name)) == NULL) {
         perror(FAN_ERR_LIGHT);
         exit(-1);
       }
       if (result = ((!strcmp(the_object -> class,LIGHT_SOURCE)) && 
          (the_object -> value)))
       break; /* break from the loop if we have a light */
    }
  }
  if (closedir(dir_pointer)) {
    perror(FAN_ERR_PLAY_STUFF);
    exit(-1);
  }
  /* end of loop through current working directory */

  if (chdir(get_current_location(PLAYERS_LOCATION))) {
     perror(FAN_ERR_PLAY_LOC);
     exit(-1);
  }
  return result;
}

/* reads a file and writes it to standard output */

int showfile(char *source) {
  int src, dst, in, out;
  const int buf_size = 1024;
  char buf[1024];

  if ((src = open(source, O_RDONLY)) == -1) {
     perror("Fantasy Error: can't open file");
     exit(-1);
  }

  for (;;) {
    in = read(src,buf,buf_size);
    if (in <= 0) break;
    out = write(1,buf,in);
    if (out <= 0) break;
  }

  if (close(src)){
     perror(FAN_ERR_SHOWFILE);
     exit(-1);
  }
  return 0;
}

/* lists movable objects and fantasy exits */

int display_location() {
  struct stat buf;			/* result of stat */
  DIR *dir_pointer;			/* directory descriptor */
  struct dirent *dp;			/* current directory entry */
  size_t th,ex,i;			/* things, exits, loop counter */
  char *thing[MAX_THINGS_AT_LOC];	/* things found at location */
  char *way_out[MAX_EXITS_FROM_LOC];	/* exits found at location */

  if (!islight() && !havelight()) {
      printf("It's dark here!\n"); 
      return 0;
  }

  /* we can see; search directory for things and exits */

  if ((dir_pointer = opendir(".")) == NULL) {
     perror(FAN_ERR_PLAY_LOC);
     exit(-1);
   }

  th = -1; ex= -1;
  for (dp = readdir(dir_pointer); dp != NULL; dp = readdir(dir_pointer)) {
    if (!strcmp(dp->d_name,DESCRIPTION)) showfile(dp->d_name);
    else if (strcmp(dp->d_name,LIGHT)) {
      if (stat(dp->d_name,&buf)) {
         perror(FAN_ERR_PLAY_LOC);
         if (closedir(dir_pointer)) 
           perror(FAN_ERR_PLAY_LOC);
         exit(-1);
      }
      if (strncmp(".",dp->d_name,1) && S_ISDIR(buf.st_mode)) 
         way_out[++ex] = (char *)strdup(dp->d_name);
      else if (strncmp(".",dp->d_name,1) && S_ISREG(buf.st_mode)) 
         thing[++th] = (char *)strdup(dp->d_name);
    }
  }

  if (closedir(dir_pointer)) {
     perror("Fantasy Error:");
     exit(-1);
  } 
  /* end of loop through directory */

  /* display the names of the things found */
  if (th==0) printf("There is a %s here.\n",thing[0]);
  else if (th!=-1) {
    printf("You can see:\n");
    for (i=0;i<=th;i++) printf("%s\n",thing[i]);
  }

  /* and the names of the exits found */
  if (ex==0) printf("The only exit is %s\n",way_out[0]);
  else if (ex!=-1) {
    printf("The exits are:\n");
    for (i=0;i<=ex;i++) printf("%s\n",way_out[i]);
  }

  return 0;
}

int main(int argc, char **argv) {
   /* make PLAYERS_LOCATION the current working directory */
   if (chdir(get_current_location(PLAYERS_LOCATION))) {
     perror(FAN_ERR_PLAY_LOC);
     exit(-1);
   }
   if (argc > 1) { /* look at something */
     examine_and_display(argv[1]);
   }
   else{ /* look at the current location */
     display_location();
   }
   exit(0);
}
