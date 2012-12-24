/* movable_object.c */

#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <stdio.h>
#include <errno.h>
#include "movable_object.h"
#include "fan_err.h"

/* reads the text relating to a movable object, and returns the object   */
/* weak on error checking, and has not been seriously tested             */

/* assumes the first line of the input file gives the class of the object,
   the following lines up to a % sign describe the object, and 
   the two lines after that contain numbers to represent 
   its weight and value 
*/

movable_object_t *read_movable_object(char *thing) {

    const char *newline = "\n";
    const char *end_description = "%";
    const char *numeric = "0123456789";
    movable_object_t *the_object;

    char input_buffer[MAX_MOVABLE_OBJECT];
    int thing_file_d, in;
    char *pos;

    if ((the_object = malloc(sizeof(movable_object_t))) == NULL) {
       perror(FAN_SYS_MALLOC);
       return NULL;
    }

    the_object -> name = (char *)strdup(thing);

    if ((thing_file_d = open(thing, O_RDONLY)) == -1) 
       return NULL;
    
    if ((in = read(thing_file_d, input_buffer, MAX_MOVABLE_OBJECT))== -1) {
       perror(FAN_SYS_READ);
       if (close(thing_file_d) == -1)
          perror(FAN_SYS_CLOSE);
       return NULL;
    }

    /* read class */
    pos = strtok(input_buffer,newline);
    the_object -> class = (char *)strdup(pos);

    /* read description */
    pos = strtok(NULL,end_description);
    the_object -> description = (char *)strdup(pos);

    /* and weight */
    pos = strtok(NULL,newline); 
    the_object -> weight = atoi(pos);

    /* and value */
    pos = strtok(NULL,newline); 
    the_object -> value = atoi(pos);

    if (close(thing_file_d) == -1) {
       perror(FAN_SYS_CLOSE);
       return NULL;
    }

    return the_object;
}


/* displays a movable object */
void display_movable_object(movable_object_t *the_object){ 

   printf("%s is a %s - %s\n",
          the_object -> name,
          the_object -> class,
          the_object -> description);

   printf("It weighs %d kg\n",the_object -> weight);

   if (!strcmp(the_object -> class, DENIZEN)) 
      printf("Its pid is %d\n",the_object -> value);

   else if (!strcmp(the_object -> class, TREASURE))
      printf("Its value is %d\n",the_object -> value);

   else if (!strcmp(the_object -> class, LIGHT_SOURCE))

      if (the_object -> value) printf("The %s is glowing gently\n",
         the_object -> name);

      else printf("It is not alight\n");
}

/* constructs a movable object from its components: name, class, description,
   weight and value 
*/
movable_object_t *construct_movable_object(char *the_name, char *the_class, 
    char *the_description, int the_weight, int the_value) {

    movable_object_t *the_object;

    if ((the_object = malloc(sizeof(movable_object_t))) == NULL) {
      perror(FAN_SYS_MALLOC);
      return NULL;
    }

    the_object -> name = (char *)strdup(the_name);
    the_object -> class = (char *)strdup(the_class);
    the_object -> description = (char *)strdup(the_description);
    the_object -> weight = the_weight;
    the_object -> value = the_value;

    return the_object;
}

/* writes a movable object to file in the current working directory */
int write_movable_object(char *place, movable_object_t *the_object){
    const char *newline = "\n";
    const char *end_description = "%";
    const char *numeric = "0123456789";

    char output_buffer[MAX_DESCRIPTION];
    int thing_file_d;

    if (chdir(place)) {
      perror(FAN_SYS_CHDIR);
      return -1;
    }
    if ((thing_file_d = creat(the_object -> name, 0655)) == -1) {
      perror(FAN_SYS_CREAT);
      return -1;
    }
    if (lockf(thing_file_d, F_LOCK, 0)) {
      perror(FAN_SYS_LOCKF);
      return -1;
    }
   
    /* write class */
    strcpy(output_buffer,the_object -> class);
    strcat(output_buffer,newline);
    if (write(thing_file_d, output_buffer, 
						  1+strlen(the_object -> class)) == -1) {
      perror(FAN_SYS_WRITE);
      return -1;
    }
   
    /* and description */
    strcpy(output_buffer,the_object -> description);
    strcat(output_buffer,end_description);
    strcat(output_buffer,newline);
    if (write(thing_file_d, output_buffer, 
              2+strlen(the_object -> description)) == -1) {
      perror(FAN_SYS_WRITE);
      return -1;
    }
   
    /* and weight */
    sprintf(output_buffer, "%d\n", the_object -> weight);
    if (write(thing_file_d, output_buffer, 
             1+strcspn(output_buffer,newline)) == -1) {
      perror(FAN_SYS_WRITE);
      return -1;
    }

    /* and value, pid, or signal */
    sprintf(output_buffer, "%d\n", the_object -> value);
    if (write(thing_file_d, output_buffer,
             1+strcspn(output_buffer,newline)) == -1) {
      perror(FAN_SYS_WRITE);
      return -1;
    }

    return 0;
}
    
