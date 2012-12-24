#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

#define BUFSIZE 512

char *workfile = "tmp.txt";

main()
{
   int fd;
   long total = 0;
   size_t nread;
   char buffer[BUFSIZE];

   if (-1 == (fd = open(workfile, O_RDONLY)))
   {
      printf("Couldn't open %s\n", workfile);
      exit(1);
   }
   printf("Opened %s successfully\n", workfile);

   while ((nread = read(fd, buffer, BUFSIZE)) > 0)
   {
      total += nread;
      printf("Buffer = %s\n", buffer);
   }

   printf("Total chars in %s is %ld\n", workfile, total);
   exit(0);
}
