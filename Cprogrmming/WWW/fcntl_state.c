#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>

void fd_mode(int);

int main(int argc, char **argv)
{
  int filedes, newfdes;

  if (argc != 2) {
    fprintf(stdout, "format %s filename\n", argv[0]);
    exit(1);
  }

  if (( filedes = open(argv[1], O_RDWR | O_APPEND)) == -1)    {
    fprintf(stderr, "open failed\n");
    exit(1);
  }

  /* Show the access intentions associated with filedes */

  fd_mode(filedes);

  /* This simply duplicates the file descriptor returning the next free
     number (probably 4) */

  newfdes = fcntl(filedes,F_DUPFD,0);

  /* Show the access intentions associated with newfdes */

  fd_mode(newfdes);

  fprintf(stdout,"newfdes = %d\tfiledes = %d\n",newfdes,filedes);

  close(filedes);
  close(newfdes);
  return(0);
}

void fd_mode(int fd)
{
  int arg1, dummy;

  /* Use fcntl to get the flag attributes associated with the descriptor */

  if ((arg1 = fcntl(fd, F_GETFL, dummy)) == -1) {
    fprintf(stderr,"fcntl failed\n");
    exit(1);
  }
  printf("File Descriptor %d : ", fd);

  if (arg1 & O_WRONLY)
    printf("write only");
  else
     if (arg1 & O_RDWR)
       printf("read-write");
     else
       printf("read only");

  if(arg1 & O_APPEND)
    printf(" - append flag set");

  printf("\n");
}

