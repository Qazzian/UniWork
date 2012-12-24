#include <stdio.h>
#include <stdlib.h>
/* Set the home directory */
int main(int argc, char **argv)
{
  printf("HOME = %s\n\n", getenv("HOME"));
  putenv("HOME=/tmp");
  printf("HOME = %s\n", getenv("HOME"));

  return 0;
}
