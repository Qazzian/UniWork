
/* height conversion
   
   Takes a measurement in feet and inches as two seperate integers 
	and converts it to meteres
   
   Author: Ian Wallis (ifw9)
   
   */

#include <stdio.h>
int main(){

	int feet = 0;
	int inches = 0;
	int all_inches = 0;
	float meters = 0.0f;
	float cms_in_inches = 2.54f;

	/* Get the values of feet and inches */
	printf("Please enter your height in feet:");
	scanf("%d", &feet);
	printf("And now inches:");
	scanf(" %d", &inches);

	/* Now do the conversion */
	all_inches = (feet*12)+inches;
	meters = all_inches*cms_in_inches/100;
	printf("%d\'%d\"is %4.2f meters \n", feet, inches, meters);
	return 0;
}	
