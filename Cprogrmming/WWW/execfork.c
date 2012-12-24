#include <sys/types.h>
#include <stdio.h>
#include <sys/wait.h>
#include <unistd.h>

int main(void)
{
  pid_t pid;
  int statusp;
  int exit_status;

  /* create a new process. */
  if ((pid = fork()) == -1)    {
     perror("fork failed");
     exit(1);
  }

  if (pid == 0)    { /* overlay ls on the child */
      execl("/bin/ls", "ls", "-l", ((void *)0));
  }
  else
  {

     /* The parent process waits for the child to complete.
        wait can return status information from the child, and
        in particular you will often want to get the child's exit value.
        statusp can be interrogated for this. However, the exit status
        gets placed in the top 8 bits of this variable. To get them you
        can use the WEXITSTATUS macro. However, you need to first check
	that the status bits have been set with the WIFEXITED macro. */

     wait(&statusp);

     /* Test to see how the child died */
     if (WIFEXITED(statusp))
     {
        exit_status = WEXITSTATUS(statusp);
        printf("status of child %ld was %d\n", pid, exit_status);
     }
  }
  return(0);
}
