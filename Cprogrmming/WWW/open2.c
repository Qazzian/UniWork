#include <stdlib.h>
#include <fcntl.h>

#define PERMS 0644

char *workfile = "tmp.txt";

main()
{
   int fd;
   if (-1 == (fd = open(workfile, O_RDWR | O_CREAT, PERMS)))
   {
      printf("Couldn't open %s\n", workfile);
      exit(1);
   }
   printf("Opened %s successfully\n", workfile);
   exit(0);
}
