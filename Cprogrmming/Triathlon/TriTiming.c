/* TriTiming
   A simple program for storing the name of a Triathlon competitor 
   and his/her times for the three parts in the race, Cycling, swimming 
   and running. It then outputs the total for the whole event.
   
   Author: Ian Wallis (ifw9)
*/



#include <stdio.h> /* Needed to take input from the keyboard and print results to the screen. */

/* Declare constants */
/* The number of seconds in a minute. */
#define MIN_TO_SEC 60	
/* The number of seconds in an hour. */
#define HR_TO_SEC 3600
/* The number of competitors in thetriathlon. */
#define COMP_NUM 2
/* The maximum number of characters allowed in a name. */
#define NAME_LENGTH 25

/*
	Competitor is a structure holding the name of a competitor their competitor number
	and their times in seconds for the different sections of the triathlon as well as 
	the total time.
	All the times are stored in seconds.
*/
struct competitor{
	char name[NAME_LENGTH];
	int competitor_number;
	int cycle_time;	
	int swim_time;		
	int run_time;		
	int total_time;	
} the_racers[COMP_NUM];

/* Declare functions so that their scope is visible throught the file */
struct competitor setcompetitor();
void setrace();
void printcomptimes(struct competitor *);
void sort(struct competitor *);
void printtable(struct competitor *);
void printrow(struct competitor *);
void printtime(int);

/*
	Creates a Competitor and returns it. Uses stdio to take the input from the user 
	and to print the questions. Once the competitor has been created printtimes() is 
	called.
	Return; 			struct competitor - the competitor created by the user.
*/
struct competitor setcompetitor(){
		/* Declare a new competitor and some temporary variables*/
	struct competitor new_racer;
	int cycle_hr, cycle_min, cycle_sec;
	int swim_hr, swim_min, swim_sec;
	int run_hr, run_min, run_sec;
	
	  /* Get the input and store them in the temps */ 
	printf("competitor's name (First_Second): \n");
	scanf("%s", &new_racer.name[0]);
	printf("Time for cycle race (hours minutes seconds):\n");
	scanf(" %d %d %d", &cycle_hr, &cycle_min, &cycle_sec);
	printf("Time for the swim (hours minutes seconds):\n");
	scanf(" %d %d %d", &swim_hr, &swim_min, &swim_sec);
	printf("Time for the running race (hours minutes seconds):\n");
	scanf(" %d %d %d", &run_hr, &run_min, &run_sec);
	printf("Can we even get to this point?\n");
		/* convert all the times to seconds */
	new_racer.cycle_time = (cycle_hr * HR_TO_SEC) + (cycle_min * MIN_TO_SEC) + 
									cycle_sec;
	new_racer.swim_time = (swim_hr * HR_TO_SEC) + (swim_min * MIN_TO_SEC) + swim_sec;
	new_racer.run_time = (run_hr * HR_TO_SEC) + (run_min * MIN_TO_SEC) + run_sec;
	new_racer.total_time = new_racer.cycle_time + new_racer.swim_time + 
									new_racer.run_time;
	printf("It works so far.\n");
	printcomptimes(&new_racer);
	return new_racer;
}	
	
/*
	Prints the name and race time of the given competitor.
	First the function gets the total time of the competitor and converts it to 
	hours, minuits, seconds.
	Once that is done the output is printed directly to the screen using printf().
	Paramiters; 		comp:- 		A pointer to the competitor structure to print the 
											name and time of.
*/
void printcomptimes(struct competitor *comp){
	/* declare tmp vars */
	int hr = 0, min = 0, sec = 0, total_sec = comp->total_time;
	/* total_sec = *comp->total_time; */
		  /* Now split them back into hours, minuits and seconds */
	hr = total_sec / HR_TO_SEC;	/* This finds the num of hours taken */
	total_sec = total_sec % HR_TO_SEC;
	sec = total_sec % MIN_TO_SEC;
	min = total_sec / MIN_TO_SEC;
	
	printf("Competitor %s has a total time of %dHrs %dMins %dSecs.\n",
						*comp->name, hr, min, sec);
}

/*
	Starts creating the details of the race.
	First creates all the competitors storing them in the_racers array.
	Then calls printtable() so that all the competitors are printed out in the order 
	they were entered.
	Once that is printed the array is sorted in order of fastest (ie least time) and 
	again printed so
  	that the winner is at the top.
*/
void setRace(){
	int i,a;
	for ( i = 0; i< COMP_NUM; i++){
		the_racers[i] = setcompetitor();
	}
	/* struct competitor *pc = the_racers; */
	printtable(&the_racers[0]);
	sort(&the_racers[0]);
	printtable(&the_racers[0]);
}

/*	Compares two times of and returns an int indicating the difference between them.
	negative numbers mean that the second time is bigger than the first, positve 
	numbers 
	mean that the first is bigger than the second, Zero indicates that they are both 
	equal.
	Paramiters;		competitor a:- 	The competitor to compare against
						competitor b:-		The competitor to test
	Returns;			the difference between the competitors times.
						Positive values when b is faster or a is slower.
						negative values when b is slower or a is faster.
						0 when both the times are equale
*/
int comparetimes(struct competitor *a, struct competitor *b){
	/* This does a test based on the diffrence between the two times as well as 
		setting int i to that difference. */
	int i;
		/* if -ve, b is slower than a. if +ve b faster than a. */
	i = a->total_time - b->total_time; 
	return i; 	/* if +ve return the value to indicat a bigger than b */
}

/*
	Initialises the printing of the table of competitors stored in the array passed 
	by the pointer. Then calls printrow() for each competitor in the array by 
	incrementing the counter.
	Paramiters; 	competitor *pc:- 	A pointer to the array of competitors to print 
												in table form
*/
void printtable(struct competitor *pc){
	int i;
	printf("NAME        competitor number  Cycle time  swim time  run time   total time\n");
	printf("-=========================================================================-\n");
	for (i =0; i< COMP_NUM; i++) {
		printrow(&pc[i]);	
	}
}

/*
	Prints out the details of the competitor given by the pointer. The details are 
	printed in a form consistent with the printtable() function.
	Paramiters;		competitor *pc:-	A pointer to the competitor which is to be 
												printed in table formate.
*/
void printrow(struct competitor *pc){
	printf(" %27s %3.0d ", *pc->name, pc->competitor_number);
	printtime(pc->cycle_time);
	printtime(pc->swim_time);
	printtime(pc->run_time);
	printtime(pc->total_time);
}

/*
	Converts the given int to three seperate int's representing hours, minuits and 
	seconds and prints the result to the screen. 
	Paramiters		the_secs:-			The total number of seconds to convert.
	out				Prints the converted time in the form "hhH mmM ssS" 
						- where hh mm ss are the integral vlues and H M S are char values
*/
void printtime(int the_secs){
	int hr = 0, min = 0, sec = 0, total_sec = the_secs;
	
		  /* Now split them back into hours, minuits and seconds */
	hr = total_sec / HR_TO_SEC;	/* This finds the num of hours taken */
	total_sec = total_sec % HR_TO_SEC;
	sec = total_sec % MIN_TO_SEC;
	min = total_sec / MIN_TO_SEC;
	
	printf(" %2.0dH %2.0dM %2.0dS", hr, min, sec);
}

/*
	Arranges the competitors in order of speed for all the events in the Triathlon.
	Uses a bubble sort to switch the competitors round untill they are all in order.
	Paramiters;		competitor *pc:- A pointer to the head of the array of competitors 
	to be sorted.
*/
void sort(struct competitor *pc){
	struct competitor tmp_c;
	int done = 0;
	do{
		int i;
		done = 1;
		for(i = 0; i< (COMP_NUM - 1); i++){
			if( comparetimes(&pc[i],&pc[i+1]) ){
				tmp_c = pc[i];
				pc[i] = pc[i+1];
				pc[i+1] = tmp_c;
				done = 0;
			}
		}
	}while(done);
}

/*
	Calls the setrace() function.
	Returns 			an int with the value 1 for compatability with Unix.
*/
int main(){	
	setRace();
	return 1;
}
