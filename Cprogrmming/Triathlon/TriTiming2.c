/* TriTiming
   A simple program for storing the name of a Triathlon competitor 
   and his/her times for the three parts in the race, Cycling, swimming 
   and running. It then outputs the total for the whole event.
   
   Author: Ian Wallis (ifw9)
*/

#include <stdio.h> /* Needed to take input and print results */

/* Declare constants */

#define MIN_TO_SEC = 60;  /* The number of seconds in a minute */
#define HR_TO_SEC = 3600; /* The number of seconds in an hour */
#define COMP_NUM = 4; 	  /* The number of competitors in the Triathlon. */
#define NAME_LENGTH =25;  /* The maximum length for competitor names. */

/**
 * Competitor is a structure holding the name of a competitor, their number
 * and their times in seconds for the different sections of the  triathlon.
 */
struct competitor{
	char name[NAME_LENGTH];/* The name of the competitor */
	int lane_num; 		/* The competitors entry number. */
	int cycle_time;	/* The time for the cycle race in seconds. */
	int swim_time;		/* The time for the swimming in seconds. */
	int run_time;		/* The time for the running in seconds. */
	int total_time;	/* The total time for the competitor in seconds. */
};


/*
	Declare Functions. 
 */
struct competitor setcompetitor ();
void setrace ();
int comparetimes (struct competitor a, struct competitor b);
void sort(int *Pa);
void printtimes (int i);
/*void printtable(struct competitor[] *pa,int size);*/
void printrow (struct competitor the_comp);
void printtime(int the_seconds);


/* 
	Declare Globle Variables 
*/
	/* An array holding all the competitors */
struct competitor the_racers [COMP_NUM]; 

				 
/*
	Creates a Competitor and returns it. Just befor returning the  competitor 
	this function prints out the name of the competitor along with their total 
	time. Uses stdio.
*/
struct competitor setcompetitor(){
		/* Declare a new competitor and some temporary variables*/
	struct competitor new_racer;
	int cycle_hr, cycle_min, cycle_sec;
	int swim_hr, swim_min, swim_sec;
	int run_hr, run_min, run_sec;
	
	  /* Get the input and store them in the temps */ 
	printf("competitor's name (First_Second): ");
	scanf("%s", &new_racer.name[0]);
	printf("Time for cycle race (hours minutes seconds):");
	scanf(" %d %d %d", &cycle_hr, &cycle_min, &cycle_sec);
	printf("Time for the swim (hours minutes seconds):");
	scanf(" %d %d %d", &swim_hr, &swim_min, &swim_sec);
	printf("Time for the running race (hours minutes seconds):");
	scanf(" %d %d %d", &run_hr, &run_min, &run_sec);
		/* convert all the times to seconds */
	new_racer.cycle_time = (cycle_hr * HR_TO_SEC) + (cycle_min * MIN_TO_SEC) +
									cycle_sec;
	new_racer.swim_time = (swim_hr * HR_TO_SEC) + (swim_min * MIN_TO_SEC) + 
									swim_sec;
	new_racer.run_time = (run_hr * HR_TO_SEC) + (run_min * MIN_TO_SEC) + 
									run_sec;
	new_racer.total_time = new_racer.cycle_time + new_racer.swim_time + 
									new_racer.run_time;
	return new_racer;
}	

/*
	Creates and sets up the race as well as printing the results of the input.
*/
void setrace(){
	int in, a;
	for (in = 0; i< COMP_NUM; i++){
		the_racers[i] = setcompetitor();
		the_racers[i].lane_num = i;
		printtimes(i);
	}
	printtable(&the_racers[0], COMP_NUM);
	sort(&the_racers[0], COMP_NUM);
	printtable(&the_racers[0], COMP_NUM);
}

/*	
	Compares two times of and returns an int indicating the difference between 
	them. negative numbers mean that the second time is bigger than the first, 
	positve numbers mean that the first is bigger than the second, Zero 
	indicates that they are both equal. 
*/
int comparetimes (struct competitor a, struct competitor b);
	int i;
	i = a.total_time - b.total_time; 
					/* if -ve, b is slower than a. if +ve b faster than a. */
	return i;
}

/*
	Sorts the competitors into speed order starting with the fastest.
*/
void sort(int *pa, size){
	int done=0;
	struct competitor tmp_com;
	while(done){
		done = 1;
		int i;
		for(i=0; i< size;i++){
			if( (pa[i]).total_time > (pa[i+1]).total_time ){
				tmp_com = pa[i];
				pa[i] = pa[i+1];
				pa[i+1] = tmp_com;
				done = 0;
			}
		}
	}
	
}

/*
	Prints the name and total time of the competitor refferd to by the index
*/
void printtimes(int i){
	printf("Competitor %s has a total time of ",the_racers[i].name);
	printtime(the_racers[i].total_time);
}

/*
	Starts printing the Table of competitors. first prints the heddings for the 
	columns, then calls printrow(int) for each competitor in the array. The 
	pointer pa represents the start of the array of which to print. Size is the 
	number of competitors to print.
*/
void printtable(int *pa,int size){
	printf("NAME        competitor number cycle time   swim time   run time   total time\n");
	printf("-=============================================================================-\n");
	int i;
	for(i = 0; i < size;i++){
		printrow(pa[i]);
	}
}

/*
	Prints a competitor in a format which is compatibal with the printtable 
	function. the pointer pa represents a pointer to the competitor in the array 
	to print.
*/
void printrow (struct competitor the_comp){
	struct competitor pc = the_comp;
	/* Print all 25 characters of the name string to keep the column width */
	printf("%-25s", pc.name[0]);
	printf("%5d", pc.lane_num); /* Print the lane number */
	printtime(pc.cycle_time);
/* printf("  ");*/
	printtime(pc.swim_time);	
/*	printf("  ");*/
	printtime(pc.run_time);	
/*	printf("  ");*/
	printtime(pc.total_time);	
/*	printf("  ");*/
   
}

/*
	Splits the int given from seconds to hours, minutes and seconds, printing the 
	results to the screen.
*/
void printtime(int the_seconds){
	/* declare tmp vars */
	int hr, min, sec, total_sec, tmp;
	total_sec = the_seconds;
		  /* Now split them back into hours, minutes and seconds */
	hr = total_sec / HR_TO_SEC;	/* This finds the num of hours taken */
	total_sec = total_sec % HR_TO_SEC;
	sec = total_sec % MIN_TO_SEC;
	min = total_sec / MIN_TO_SEC;
	printf("%2.0dH %2.0dM %2.0dS",hr,min,sec);
}

/*
	Starts the Program by calling setrace()
*/
int main(){	
	setrace();
	return 1;
}
