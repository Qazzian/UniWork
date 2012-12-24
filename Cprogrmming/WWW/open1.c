#include <stdlib.h>
#include <fcntl.h>

char *workfile = "tmp.txt";

main()
{
   int fd;
   if (-1 == (fd = open(workfile, O_RDWR)))
   {
      printf("Couldn't open %s\n", workfile);
      exit(1);
   }
   printf("Opened %s successfully\n", workfile);
   exit(0);
}