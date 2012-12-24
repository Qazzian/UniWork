#include <ftw.h>
#include <stdio.h>
#include <sys/stat.h>
#include <sys/types.h>

int list(const char *name, const struct stat *status, int type)
{
  if(type == FTW_NS)  /* test for unknown type */
     return(0);

   if (type == FTW_F)  /* is it a file */
      printf("%-30s\t0%3lo\n",name,status->st_mode & 0777);
   else                /* must be something else  put a star by it */
      printf("%-30s*\t0%3lo\n",name,status->st_mode & 0777);

   return(0);
}

int main(int argc, char **argv)
{
   int list();

   if(argc <= 1)
     ftw(".",list, 1);
   else
     ftw(argv[1], list, 1);
   return(0);
}

