#include <stdio.h>
#include <unistd.h>

int main(int argc, char **argv)
{
  execl("/bin/ls", "ls", argv[1], argv[2], argv[3], (void *)0);

  printf("EXECL has failed\n");
  return 1;
}