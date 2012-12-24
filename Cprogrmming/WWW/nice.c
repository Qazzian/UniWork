#include <stdio.h>
#include <unistd.h>
int main()
{
   int x;
   nice(20);

   for (x = 0; x < 200; x++)
      printf("me slow\n");

   nice(0);

   for (x = 0; x < 200; x++)
      printf("me normal speed\n");

   if (nice(-20) == -1)     {
       fprintf(stdout,"I am not root, I can't do that\n");
       exit(1);
    }

    for (x = 0; x < 200; x++)
      printf("me Road Runner!\n");

    return 0;
}

