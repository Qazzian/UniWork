/*
	marks.c
	Takes a list of marks and prints out the average mark gained
	
	Auther: Ian Wallis (ifw9)
*/

#include <stdio.h>

int main(){
	int mark_entered = 0; 
	int sum = 0;
	int count = 0; /* the number of marks entered */
	float ave_mark = 0.0f;

	/* Ask for the first mark */
	printf("Enter marks one at a time.\nWhen you have finished use -1 to show the average mark.\n");
	printf("Enter first mark:");
	scanf("%d", &mark_entered);
	
	/* add the marks to the sum then get the next mark, 
		if the next mark is -1 exit the loop. */
	do{
		sum += mark_entered;
		count++;
		printf("Enter next mark:");
		scanf(" %d", &mark_entered);
	}while(mark_entered != -1);

	/* Now find the average mark and print it on the screen. */
	ave_mark = (float)sum/count;
	printf("\nThe average mark is %5.2f\n", ave_mark);
	return 0;
}
