#include <stdio.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>

/* A static global means that the variable is only visible to code
   in this file */

static int level_limit = 2;
static int present_level = 1;
void walk_dir(char *);

int main(int argc, char **argv)
{
   /* argv[1] should contain the root pathname of the tree to be traversed.
      A real program would analyse the command line args for errors.*/

   walk_dir(argv[1]);
   return 0;
}

void walk_dir(char *path)
{
   struct dirent *dp;
   struct stat buf;
   char full_path[80];
   DIR *fd;

   if ((fd = opendir(path)) == NULL)     {
      perror("error opening dir %s\n");
      exit(1);
   }

   /* Collect directory entries. dp being set to NULL indicates that
      the tree has been fully traversed */

   for (dp = readdir(fd); dp != NULL; dp = readdir(fd))
   {
      /* Concatenate the directory entry onto the existing path.
         Should really check for the special case of path being "/"
         since the current code prints too many "/"s for this case */

      sprintf(full_path,"%s/%s", path, dp->d_name);

      /* Obtain status information about the file/directory. stat
         may fail if the path refers to a symbolic link which doesn't point
         to a valid destination. In this case we use lstat
         to get information */

      if((stat(full_path, &buf)) == -1)
      {
         if((lstat(full_path, &buf)) == -1) {
            perror("stat failed");
            exit(2);
         }
      }
      /* Now display the type of the directory entry: symbolic link,
         file or directory */

      if(S_ISLNK(buf.st_mode))
         printf("%s is a symbolic link\n",full_path);

      if (S_ISREG(buf.st_mode))
         printf("%s is a file\n",full_path);

      /* If a directory then we recurse. However, we don't won't to go
         round in circles so don't traverse across the ".."
         or "." links. */

      if (S_ISDIR(buf.st_mode))  { /* lets do some recursion */
         if (!(strcmp(".", dp->d_name) == 0 || strcmp("..", dp->d_name) == 0))
         {
            printf("%s is a directory\n", full_path);

            /* Only recurse if we haven't hit our maximum depth
               of recursion limit */

            if( present_level < level_limit)
            {
               present_level++;
               walk_dir(full_path);  /* recursion */

               /* Here we drop out of recursion (go up a level in the file
                  tree, so we decrement the level counter */

               present_level--;
            }
         } /* if (!(strcmp */
      } /* if(S_ISDIR */
   } /* for */

   /* This part of the program is contrived, but illustrates the use of
      other directory functions */

   rewinddir(fd); /* Takes us to the start of the directory file */
   printf("location in directory %s is %ld\n", path, telldir(fd));

   seekdir(fd, 2); /* We move entry pointer to second directory entry */
   printf("location in directory %s is %ld\n", path, telldir(fd));

   rewinddir(fd);
   printf("location in directory %s is %ld\n", path, telldir(fd));
   closedir (fd);
}

