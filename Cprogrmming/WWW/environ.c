#include <stdio.h>
/* Print the environment of a program */
extern char **environ;

int main(int argc, char **argv)
{
  while(*environ != (char *)0)
    printf("%s\n",*environ++);

  return 0;
}