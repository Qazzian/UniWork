#include <stdio.h>
#include <sys/types.h>
#include <sys/statvfs.h>   /* for statvfs */
#include <sys/stat.h>
#include <errno.h>

extern int errno;   /* declaration of system error messages */
extern char *sys_errlist[];
void file_sys_stats(char *);

int main(int argc, char **argv)
{
  struct stat buf;

  if (stat(argv[1], &buf) == -1)      {
     fprintf(stderr, "stat failed - %s\n", sys_errlist[errno]);
     exit(1);
  }

  if (S_ISDIR(buf.st_mode))   /* test if the file is a directory */
     file_sys_stats(argv[1]);
  else
     printf("File %s has a uid = %d and a  gid = %d\n",
                   argv[1], (int)buf.st_uid, (int)buf.st_gid);

  return 0;

}

void file_sys_stats(char *file_system)
{
  struct statvfs buf;

  if (statvfs(file_system, &buf) == -1)   {
     fprintf(stderr, "statfs failed - %s\n",sys_errlist[errno]);
     exit(1);
  }

  printf("\n\t FILE SYSTEM STATISTICS FOR %s\n", file_system);
  printf("\tfundamental system block size     - %ld\n",buf.f_bsize);
  printf("\ttotal blocks in the system        - %ld\n",buf.f_blocks);
  printf("\tfree blocks                       - %ld\n",buf.f_bfree);
  printf("\tfree blocks available to non-root - %ld\n",buf.f_bavail);
  printf("\ttotal file nodes in the system    - %ld\n",buf.f_files);
  printf("\tfree file nodes in fs             - %ld\n",buf.f_ffree);
  printf("\tfile system id\t\t\t  - %ld\n",buf.f_fsid);

}

