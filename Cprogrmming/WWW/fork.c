#include <sys/types.h>
#include <stdio.h>
#include <unistd.h>

int main(void)
{
  pid_t pid;

  if ((pid = fork()) == -1)    {   /* create a new process. */
     perror("fork failed");
     exit(1);
  }

  if (pid == 0)    {
     printf("I'm the child and my process id is %ld\n", getpid());
     printf("My parent's process id is %ld\n", getppid());
  }
  else
     printf("I am the parent and my process id is %ld\n", getpid());

  return 0;
}

