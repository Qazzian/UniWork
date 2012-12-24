/* go.c */
#include <stdio.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>
#include <errno.h>
#include <dirent.h>
#include <string.h>
#include "location.h"
#include "fan_err.h"
int main(int argc, char **argv) {
   DIR *dir_pointer;
	char *current_location;
   char *moving_to;
	 
	if (argc != 2) { 
	   printf("Go Where?\n");
		exit(-1);
	}
	
	
}
