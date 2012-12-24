/* light.c */
#include <stdio.h>

int main(int argc, char **argv) {
    int i;
    printf("This is the command %s",argv[0]);
    if (argc > 1) 
      { printf(", with arguments:\n");
        for (i=1;i<argc;i++) printf("%s\n",argv[i]);
      };
    printf("\n... not yet implemented ...\n");
    exit(0);
}
