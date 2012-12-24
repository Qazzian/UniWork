/* drop.c */
#include <stdio.h>




int main(int argc, char **argv) {
   movable_object_t *thing;
	int weight_left;
	char *current_place = ;
	if (argc != 2) {
		printf("Drop What?\n");
		exit(-1);
	}
	
	
	int i;
    printf("This is the command %s",argv[0]);
    if (argc > 1) 
      { printf(", with arguments:\n");
        for (i=1;i<argc;i++) printf("%s\n",argv[i]);
      };
    printf("\n... not yet implemented ...\n");
    exit(0);
}
