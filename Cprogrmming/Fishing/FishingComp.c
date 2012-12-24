#include <ansi_c.h>
/***********************************************************************************
 * Fishingcomp.c
 * 
 *  DESCRIPTION:
 *		Records the scores for a fishing compitition 
 * 		and prints them to the screen.
 *
 * AUTHOR:	Ian Wallis (ifw9)
 * 
 *
 ***********************************************************************************/

#include <stdio.h>
#include <string.h>


/****	CONSTANT DEFINITIONS	****/

#ifndef NULL
	#define NULL '\0'
#endif

#define MAX_NAME_LENGTH  50
#define COMPETITOR_NUMBER 3

#define POUNDS_IN_STONE	14
#define OUNCES_IN_POUND 16
#define OUNCES_IN_STONE	OUNCES_IN_POUND * POUNDS_IN_STONE


/* The competitor structure */
typedef struct
{
	char name[MAX_NAME_LENGTH];
	int comp_num;
	int river_weight; /* in ounces */
	int sea_weight;
	int fly_weight;
}COMPETITOR;



COMPETITOR *entrants[COMPETITOR_NUMBER];	/* The competitors of the compatition. */


/**** 	FUNCTION PROTOTYPES 	****/

int getInput(COMPETITOR *new_competitor);
void sortCompetitors(void);
void printTable(void);
void printCompetitor(COMPETITOR *competitor);

int weightToOunces(int stones, int pounds, int ounces);
void ouncesToWeight(int total_ounces, int *stones, int *pounds, int *ounces);
int totalWeight(COMPETITOR *competitor);


/**************************************************************************************************
 *
 *************************************************************************************************/
int main (int argc, char *argv[])
{
	int i; /* For looping */
	
	for(i=0; i<COMPETITOR_NUMBER; i++)
	{	
		entrants[i] = (COMPETITOR *)malloc(sizeof(COMPETITOR));
		entrants[i]->comp_num = i + 1;
		getInput(entrants[i]);
	}
	printTable();
	sortCompetitors();
	printf("\n");		/* Just to seperate the two tables. */
	printTable();
	for(i=0; i<COMPETITOR_NUMBER; i++)
	{	
		free(entrants[i]);
	}
	
	
	return 0;
}

/**************************************************************************************************
 * Reads input from the standard input stream and converts it to data for the
 * give competitor.
 *
 *	INPUT:	*new_competitor - A reference to the competitor to store the entered data
 *
 *	OUTPUT:	Negative if error, NULL otherwise.
 *************************************************************************************************/
int getInput(COMPETITOR *new_competitor)
{
	int stones, pounds, ounces;
	
	/* Get name */
	printf("Competitors Name: ");
	scanf("%s", new_competitor->name);
	/* River weight */
	printf("Weight of fish from the River Competiton(Stones Pounds Ounces): ");
	scanf("%d %d %d", &stones, &pounds, &ounces);
	new_competitor->river_weight = weightToOunces(stones, pounds, ounces);
	/* Sea weight */
	printf("Weight of fish from the Sea Competiton(Stones Pounds Ounces): ");
	scanf("%d %d %d", &stones, &pounds, &ounces);
	new_competitor->sea_weight = weightToOunces(stones, pounds, ounces);
	/* Fly weight */
	printf("Weight of fish from the Fly Competiton(Stones Pounds Ounces): ");
	scanf("%d %d %d", &stones, &pounds, &ounces);
	new_competitor->fly_weight = weightToOunces(stones, pounds, ounces);
	return 0;
}

/**************************************************************************************************
 * Rearanges the commetitor array in order of winner to loser
 *************************************************************************************************/
void sortCompetitors(void) {
	int i, j;								/* Outer, inner loop counters. */
	int with_heaviest;						/* The competitor with the highest weight of fish. */
	COMPETITOR *sorted[COMPETITOR_NUMBER];	/* An Array of the sorted competitors. */
	
	for(i=0; i<COMPETITOR_NUMBER; i++) 
	{
		with_heaviest = 0;
		for(j=0; j < (COMPETITOR_NUMBER-1); ) 
		{
			if(totalWeight(entrants[j]) < totalWeight(entrants[++j])) /* find best and increment j */
			{
				with_heaviest = j;
			}
		}
		sorted[i] = entrants[with_heaviest];
		entrants[with_heaviest] = NULL;
	}
	for(i=0; i<COMPETITOR_NUMBER; i++)
		entrants[i] = sorted[i];
}


/**************************************************************************************************
 * Prints the contents of the competitor array in a table format to the standard output.
 *************************************************************************************************/
void printTable(void)
{
	int i;
	char *header;
	
	/* Print table header */
	header = "\nNAME        competitor number  river fishing   sea fishing     fly fishing     total weight\n";
	printf("%s", header);
	for(i=0; i < strlen(header); i++)
		printf("=");
	
	/* Print all the competitors details */
	for(i=0; i<COMPETITOR_NUMBER; i++)
	{	
		printCompetitor(entrants[i]);
	}
}

/**************************************************************************************************
 * Prints a single competitors details to the standard output.
 *************************************************************************************************/
void printCompetitor(COMPETITOR *competitor)
{
	int stones, pounds, ounces;
	
	/* print name */
	printf("\n%-21s", competitor->name);
	/* print number */
	printf("%-9d", competitor->comp_num);
	/* print river */
	ouncesToWeight(competitor->river_weight, &stones, &pounds, &ounces);
	printf("%2.0dSt %2.0dPd %2.0dOz  ", stones, pounds, ounces);
	/* print sea */
	ouncesToWeight(competitor->sea_weight, &stones, &pounds, &ounces);
	printf("%2.0dSt %2.0dPd %2.0dOz  ", stones, pounds, ounces);
	/* print fly */
	ouncesToWeight(competitor->fly_weight, &stones, &pounds, &ounces);
	printf("%2.0dSt %2.0dPd %2.0dOz  ", stones, pounds, ounces);
	/* print total */
	ouncesToWeight(totalWeight(competitor), &stones, &pounds, &ounces);
	printf("%2.0dSt %2.0dPd %2.0dOz", stones, pounds, ounces);
}



/*****************************************************************************************
 * Returns the given weight as the equivalent in just ounces.
 *
 * INPUTS
 *		stones - The number of stones to convert.
 *		pounds - The number of pounds to convert.
 *		ounces - The number of ounces to convert.
 *
 * OUTPUT -	The total weight converted to ounces.
 *****************************************************************************************/
int weightToOunces(int stones, int pounds, int ounces)
{
	pounds += (stones * POUNDS_IN_STONE);
	ounces += (pounds * OUNCES_IN_POUND);
	return ounces;
}

/***********************************************************************************
 * Converts the weight given in ounces to stones, pounds and ounces.
 * 
 * INPUTS:
 *		total_ounces:- An int holding the total weight in ounces to be converted.
 *		stones - A pointer to an int in which to store the converted stones value.
 *		pounds - A pointer to an int in which to store the converted pounds value.
 *		ounces - A pointer to an int in which to store the converted ounces value.
 ***********************************************************************************/
void ouncesToWeight(int total_weight, int *stones, int *pounds, int *ounces)
{
	*ounces = total_weight % OUNCES_IN_POUND;
	total_weight -= *ounces;
	total_weight /= OUNCES_IN_POUND;
	*pounds = total_weight % POUNDS_IN_STONE;
	total_weight -= *pounds;
	total_weight /= POUNDS_IN_STONE;
	*stones = total_weight;
	
	return;
}

/************************************************************************************
 * Returns the total weight in ounces of all the fish caught by the given competitor.
 *
 * INPUT: competitor - A reference to the competitor who's score is to be added up.
 *
 * OUTPUT: The total weight in ounces. -1 if competitor = NULL
 ************************************************************************************/
int totalWeight(COMPETITOR *competitor)
{
	if(competitor != NULL)
		return competitor->river_weight + competitor->sea_weight + competitor->fly_weight;
	else
		return -1;
}

