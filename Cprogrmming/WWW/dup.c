#include <fcntl.h>  /* Definition of flags */
#include <unistd.h> /* Definition of read & dup & dup2 */
#include <stdio.h>
#include <stdlib.h>
#define NUM_CHRS 10

int main(int argc, char **argv)
{
   int fd1, fd2;
   char *cp;

   cp =  malloc((NUM_CHRS+1) * sizeof(char));

   fd1 = dup(0);        /* duplicated standard in */

   printf("enter a string : \n");
   read(fd1, cp, NUM_CHRS);

   printf("fd1 = %d : \tcharacters = %s\n", fd1, cp);

   fd2 = open(argv[1], O_WRONLY);
   dup2(fd2, 1);

   printf("fd2 = %d : \tcharacters = %s\n", fd2, cp);

   return 0;
}

