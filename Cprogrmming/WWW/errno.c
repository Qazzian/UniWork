#include <stdio.h>
#include <errno.h>
#include <stdio.h>

#define MAXERRNO 90

extern int errno;
extern char *sys_errlist[];

int main()
{
  int x;

  for ( x=0;x < MAXERRNO;x++)    {
     printf("%d %s \n", x, sys_errlist[x]);
  }
  return (0);
}

