#include <fcntl.h>
#include <stdlib.h>

char *workfile = "myfile.txt";

main()
{
   int fd;
   if ((fd = open(workfile, O_RDWR | O_CREAT, 0744)) == -1)
   {
      printf("Didn't Succeed\n");
      exit(1);
   }
   exit(0);
}