#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <unistd.h>

int main()
{
  pid_t pid1, pid2;
  int status;

  if ((pid1 = fork()) == -1)      {
     perror("fork failed");
     exit(1);
  }

  if (pid1 == 0)       {
     printf("Child 1 with a pid of %ld\n", getpid());
     sleep(5);
     printf("Child 1 exits\n");
     exit(0);
  }
  else
  {
     if ((pid2 = fork()) == -1)          {
        perror("fork failed");
        exit(1);
     }

     if (pid2 == 0)            {
        printf("Child 2 with a pid of %ld\n", getpid());
        printf("Child 2 exits\n");
        exit(0);
     }
  }

  waitpid(pid1, &status, 0);

  printf("parent %ld  continues and exits\n", getpid());

  return 0;
}

