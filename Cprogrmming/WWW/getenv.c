#include <stdio.h>
#include <stdlib.h> /* Definition of getenv */

/* Print parts of the environment of a program */
char *to_find[] = { "HOME","USER","DISPLAY", (char *)0 };
int main(int argc, char **argv)
{
  char **tmp;
  tmp = to_find;

   while(*tmp != (void *)0)
     printf("%s = %s\n", *tmp, getenv(*tmp++));

   return 0;
}
