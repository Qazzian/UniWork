#include <sys/stat.h>
#include <stdio.h>

int main(int argc, char **argv)
{
  struct stat buf;

  if (stat(argv[1], &buf) == -1)    {
     perror("stat failed");  /* perror will display a system generated
                                message followed by the string argument */
     exit(1);
  }

  if (buf.st_mode & S_IWUSR) {
     printf("%s has write permission\n", argv[1]);
     printf("%lo\n",buf.st_mode & S_IWUSR);
  }
  return 0;
}