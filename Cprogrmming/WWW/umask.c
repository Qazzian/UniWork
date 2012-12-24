#include <fcntl.h>    /* Required for flags O_RDWR etc */
#include <stdio.h>    /* Defines IO library functions */
#include <unistd.h>   /* Declares unlink */
#include <sys/stat.h> /* Declares umask */

int main(int argc, char **argv)
{
  int fd;
  FILE *filedes;
  int origmask, mask = 0033, zero_mask = 0;

  /* Remove any previous files created */

  unlink(argv[1]);    unlink(argv[2]);
  unlink(argv[3]);    unlink(argv[4]);

  /* Set the mask to prevent writing and execution permissions for
     group users or other users being set when creating new files.
     Keep hold of the original mask. */

  origmask = umask(mask);

  /* Create a new file using open system call and specify the mode as rw-r-xr-x.
     The mask will be applied implicitly and filter out
     the two execute permissions for group and other users.
     The file will actually be created with: rw-r--r-- */

  if (( fd = open(argv[1], O_RDWR | O_CREAT, 0655)) == -1) {
    fprintf(stderr, "open failed\n");
    exit(1);
  }
  close(fd);

  /* Create a new file using the fopen library function with
     the intention of appending. Although
     we don't explicitly say what the mode is for each of the three
     groups of users, the system will set the mode to read and write
     for each kind of user. However because the mask is set to screen
     out rw for group and others the file will actually be created with
     permissions: rw-r--r-- */

  if ((filedes = fopen(argv[2], "a+")) == NULL)    {
    fprintf(stderr, "fopen failed\n");
    exit(1);
  }
  fclose(filedes);

  /* We now set the mask to zero so that file modes are unaffected during
     file creation */

  origmask = umask(zero_mask);

  /* This time the file will be created with rw-r-xr-x */

  if (( fd = open(argv[3], O_RDWR | O_CREAT, 0655)) == -1) {
    fprintf(stderr, "open failed\n");
    exit(1);
  }
  close(fd);

  /* This time the file will be created with rw-rw-rw- */

  if (( filedes = fopen(argv[4], "a+")) == NULL)    {
    fprintf(stderr, "fopen failed\n");
    exit(1);
  }

  fclose(filedes);
  return 0;
}

