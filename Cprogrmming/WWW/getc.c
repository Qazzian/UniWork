#include <stdio.h>

int main()
{
  char c;

  for(;;) {
    c = getc(stdin);
    (void) printf("%c\n",c);
  }
  return 0;
}

