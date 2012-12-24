#include <stdio.h>

main() {
    int a,b,c;
    
    a = 5;
    b = 45;

    c = add(a,b);
    
    printnumber(a);
    
    printnumber(b);
    
    printnumber(c);
}

int add(int x, int y) {
    
    int temp = x + y;
    return temp;
}

int printnumber(int n) {
    printf("The Number is %d\n", n);
}
    


