#include <sys/stat.h>
#include <stdio.h>

main(int argc, char **argv)
{
   struct stat buf;
   if (-1 == stat(argv[1], &buf))
   {
      perror("stat failed");
      exit(1);
   }
   if (buf.st_mode & S_IWUSR)
   {
      printf("%s has write permission\n", argv[1]);
      if (S_ISDIR(buf.st_mode))
      {
         printf("and is a directory\n");
      }
   }
}