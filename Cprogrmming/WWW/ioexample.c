#include <fcntl.h>
#include <stdio.h>    /* only for BUFSIZ definition and sprintf */
#include <malloc.h>
#include <sys/types.h>
#include <unistd.h>
#include <errno.h>
#include <stdlib.h> /* Defines system function */

#define PROG_BUFSIZE BUFSIZ     /* buffer size, same as block size 1024 */
#define COMMAND "/bin/ls -l"    /* command to be executed */

char *thing_to_write = "a thing to write\n";
extern char *sys_errlist[];       /* external list of all error messages */

int main(int argc, char **argv)
{
   int filedes;      /* A file descriptor */
   char *inbuf;      /* buffer to read into */
   char command[40]; /* command to execute  ls -l */

   (void) sprintf(command, "%s %s",COMMAND, argv[1]);

   /* open file for reading and writing, append output to end of file */

   if ((filedes = open(argv[1], O_RDWR | O_APPEND | O_CREAT, 0644)) == -1) {
      (void) fprintf(stderr, "error opening file %s : %s\n",
                              argv[1], sys_errlist[errno]);
      exit(1);
   }

   /* Write out some text. Note that we find the size of char
      when calculating the number of bytes is to be written. This isn't
      strictly necessary since char = 1 byte, but it makes our code more
      portable (just in case another machine perversely used 2 bytes to
      implement a char!) */

   if ((write(filedes, thing_to_write,
              strlen(thing_to_write) * sizeof(char))) == -1)   {
      (void)fprintf(stderr, "error writing to file %s : %s\n",
                             argv[1], sys_errlist[errno]);
      exit(1);
   }

  lseek(filedes, 0, SEEK_SET); /* set file pointer to start of file */

  /* Create a buffer of an optimal size */

  inbuf = (char *) malloc(PROG_BUFSIZE * sizeof(char));
  if (inbuf == NULL) {  /* test if malloc failed */
     (void)fprintf(stderr, "malloc failed allocating inbuf :
                            %s\n", sys_errlist[errno]);
     exit(1);
  }

  /* Now try and fill inbuf with data from the file */

  if((read(filedes, inbuf, PROG_BUFSIZE)) == -1) { /*read file descriptor*/
     (void)fprintf(stderr, "error reading from file %s : %s\n",
                            argv[1], sys_errlist[errno]);
     exit(1);
  }

  close(filedes);

  /* system is a quick way of starting other processes from this process */

  system(command);
  unlink(argv[1]);

  /* This time, ls -l will not list the file */
  system(command);

  /* Now write directly to standard output. No test needed. */
  write(1,inbuf, strlen(inbuf) * sizeof(char));

  free(inbuf);   /* deallocated memory allocated by malloc */

  /* The following is very dangerous since inbuf is now deallocated. It
     may produce the same output but it may also write garbage
     or cause a segmentation violation (outside process' address space)*/

  write(1, inbuf, strlen(inbuf));

  return 0;
}

