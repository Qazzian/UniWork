#include <ansi_c.h>
/*
   						__RetailP3.c__
							
			___Carols Cabbage Rides Again, the C Version___
			
   This Program answers part three of Assignment Three.
   It is built on top of RetailP2.c and adds to it the structures and functions for 
	taking money off the coustomer and giving change as well as keeping the till up 
	to date.
	
	Constants Defined;
		FIFTY_POUNDS
	Structres and functions created;
		till, tender_cash().
	Modifid from RetailP1.c;
		main().

	Author:- Ian Wallis
	Last revised:- 24th March, 2001
	Copyright:- Ian Wallis, U.W.A. Dept. Computer Science.
	
*/

#include <stdlib.h>
#include <errno.h>


/************************** 
 **** Constant Defines ****
 **************************/

/* Boolean values */
#define FALSE NULL
#define TRUE  !FALSE
/* The two diferent units used in the shop */
#define POUNDS "lb"
#define SINGLE "individual"

/* The index values for the different units of currency. */
typedef enum {FITY_POUNDS=0, TWENTY_POUNDS, TEN_POUNDS, FIVE_POUNDS, ONE_POUND, 
				FIFTY_PENCE, TWENTY_PENCE, TEN_PENCE, FIVE_PENCE, TWO_PENCE, ONE_PENCE,
				COIN_NUM /* COIN_NUM defines the number of different currency units available. */
			}COIN_DEFINE; 


/*************************** 
 **** Structure defines ****
 ***************************/
					
/* The values of the coins / notes starting with the highest value. */
int coin_values [] = {5000, 2000, 1000, 500, 100, 50, 20, 10, 5, 2, 1};
/* The names of the coins / notes starting with the highest value. */
char *coin_names [] = {"Fifty Pounds", "Twenty Pounds", "Ten Pounds", "Five Pounds", 
						"One Pound", "Fifty Pence", "Twenty Pence", 
						"Ten Pence", "Five Pence", "Two Pence", "One Pence"
					  };

/* Represents a product sold in the shop. */
struct product{
	char name[80];		/* The name of the product. */
	char units [12];	/* the units that theproduct is sold in. */
	int in_stock, new_stock, unit_price;
	struct product *varietys, *others;
};

/* Represents the money in the till. */
struct till{
	int the_coins [COIN_NUM];
};


/***************************** 
 **** Function Prototypes ****
 *****************************/
struct product *make_node(char the_name[], char the_units[], int cur_stock, int on_order, int price,
								  struct product * the_variety, struct product * the_other);


/**************************************************************************************************
 *	Makes a new node for the tree. Most of the fields of the new product are 
 *	filled with the paramiters given to this function.
 *	
 *		INPUT:	char the_name		A char array holding the name of the new product.
 *				char the_units		A char array holding the name of se unis for the new product.
 *				int cur_stock		The number of items currently in stock.
 *				int on_order		The number of items currently on order.
 *				int price			The price for one unit of the product.
 *				
 *				struct product *the_variety		A pointer to a specific variety of the product.
 *				struct product *the_other		A pointer to other products under the same heading.
 *	
 *	OUTPUT:		A pointer to the newly created product node.
 *
 *************************************************************************************************/
struct product *make_node(char the_name[], char the_units[], int cur_stock, int on_order, int price,
								  struct product *the_variety, struct product *the_other) 
{
	struct product *tmp_ptr;
	
	tmp_ptr = malloc( sizeof (struct product) );
	strcpy( tmp_ptr->name  , the_name  );
	strcpy( tmp_ptr->units , the_units );
	tmp_ptr->in_stock 	= cur_stock;
	tmp_ptr->new_stock	= on_order;
	tmp_ptr->unit_price 	= price;
	tmp_ptr->varietys		= the_variety;
	tmp_ptr->others 		= the_other;
	
	return tmp_ptr;
}

/**************************************************************************************************
 * Prints the details of the product pointed to by the given pointer.
 * All the details are printed straight to the command prompt.
 * 
 * INPUT:	struct product *pp - A pointer to the product to print the details of.
 *
 *************************************************************************************************/
void print_product(struct product *pp) {
	printf("%s sold at %dp per %s. %d in stock, %d on order\n",
				pp->name, pp->unit_price, pp->units, pp->in_stock, pp->new_stock);
}

/**************************************************************************************************
 * Prints the details of each node in the tree with the given node as the root.
 * The details are printed by calling print_product() on each node.
 * All the details are printed to the command prompt.
 *	
 * INPUT:	struct product *pp - A pointer to the root node of the tree.
 *
 *************************************************************************************************/
void print_tree(struct product *pp) {
	print_product(pp);				// Print the root
	if( pp->varietys != NULL ) {
		print_tree(pp->varietys);
	} else {
		printf("There are no varieties\n\n");
	}
	if( pp->others != NULL ) {
		print_tree( pp->others );
	}
}

/**************************************************************************************************
 * Askes the customer for the number of units they want to buy.
 * Prints out the details of the product pointed to by the given pointer. Then, using printf() 
 * and scanf(), it asks the user to input an amount. This input is checked against the current 
 * stock of the product and will only continue if the amount is in stock. This function then 
 * Returns the number of items.
 * orderd.
 *	
 *	INPUT:	struct product *pp - The product which is for sale.
 *	
 *	OUTPUT:	int - The number of units of the product the customer wants to buy.
 *
 *************************************************************************************************/
int ask_quantity(struct product * pp) {
	int quantity;
	int  valid = FALSE;
	
	while(valid == FALSE) {
		print_product(pp);
		printf("How much do you want to buy?  ");
		scanf("%d", &quantity);
		if( (0 <= quantity) && (quantity <= pp->in_stock) ) {
			valid = TRUE;
		} else {
			printf("\nNot Enough Stock.\n\n");
			valid = FALSE;
		}
	}
	return quantity;	
}

/*************************************************************************************************
 * Asks the customer how much of each item of stock they want to buy.
 * Goes through each product in turn displaying its name, price per unit and how many units 
 * are in stock. Keeps a running total of how much the customer has spent as well as changing 
 * the records of how much of each product is in Stock.
 *
 * INPUT:	struct product *pp - A pointer to the root of the tree of products.
 * 
 * OUTPUT:	int	- the total cost of all the items to buy
 *************************************************************************************************/
int sell_stock(struct product * pp) {
	int quantity, price;
	
	if(pp->varietys == NULL) {
		quantity = ask_quantity(pp);
		price = quantity * (pp->unit_price);
		pp->in_stock -= quantity;
	} else {
		printf("%s\n", pp->name);
		price = sell_stock(pp->varietys);
		printf("sub total  %dp\n", price);
	}
	if(pp->others !=NULL) {
		price += sell_stock(pp->others);
	}
	return price;
}

/*************************************************************************************************
 *	Calculates the amount of change to give the customer given the amount to pay 
 *	and that they can only pay with fifty pound notes.
 *
 * INPUT: 	int to_pay- The amount the coustomer is to pay.
 *			struct till *the_till - A pointer to the shops till.
 *	
 * OUTPUT:	struct till - The amount of each denomination to give the customer as change.
 *
 *************************************************************************************************/
struct till* tender_cash(int to_pay, struct till * the_till) {
	int change;
	int tmp_value, curr_coin, num_to_give;
	struct till *the_change;
	
	change = coin_values[FITY_POUNDS] - to_pay;
	the_till->the_coins[FITY_POUNDS] += 1;
	the_change = malloc(sizeof(struct till));
		/* starting with i=1 skips the fifties */
	for(curr_coin=1; curr_coin<COIN_NUM; curr_coin++) {
		tmp_value = change % coin_values[curr_coin];
		num_to_give = change / coin_values[curr_coin];
		if(num_to_give <= the_till->the_coins[curr_coin]) {
			the_till->the_coins[curr_coin] -= num_to_give;
			the_change->the_coins[curr_coin] = num_to_give;
		} else {
			the_change->the_coins[curr_coin] = the_till->the_coins[curr_coin];
			num_to_give -= the_till->the_coins[curr_coin];
			the_till->the_coins[curr_coin] = NULL;
			tmp_value += (num_to_give * coin_values[curr_coin]);
		}
		change = tmp_value;
	}
	return the_change;
}

/*************************************************************************************************
 * Sets up the till with an amount of change defined in this function.
 * At the moment it puts 30 of each coin and note in the till.
 *
 * INPUT:	struct till *the_till - A pointer to the shops till.
 * 
 *************************************************************************************************/
void init_till(struct till * the_till) {
	int i;
	for(i=0; i < COIN_NUM; i++) {
		the_till->the_coins[i] = 30;
	}
}

/*************************************************************************************************
 * Prints the contents of the till. Prints the number of each coin / note, the 
 * value of that coin/note and the total value of the till.
 *	
 * INPUT:	struct till *the_till - The till to print the contents of.
 *
 *************************************************************************************************/
void print_till(struct till * the_till) {
	int cur_coin, sub_value, total_value = 0;
	
	for(cur_coin=0; cur_coin<COIN_NUM; cur_coin++) {
		sub_value = coin_values[cur_coin] * the_till->the_coins[cur_coin];
		total_value += sub_value;
		printf("%s x %d = %d,\t", coin_names[cur_coin],
										  the_till->the_coins[cur_coin],
										  sub_value);
	}
	printf("\nTotal= %d\n",total_value);
}

/*************************************************************************************************
 *	Creates the tree by feeding values into the make_node function. Then prints 
 * 	a menu to the screen asking if the user would like to see what is in stock 
 * 	or if they would like to buy some stock. 
 *	If the user selects to print the stock list print_tree() is called. 
 *	If the user selects to buy some products sell_stock() is called. Once the 
 *	user has selected all the items they wish to buy, the total cost of the 
 *	transaction is printed to the screen. This total cost is then used to work 
 *	out the change that the till operator should give to the customer given that 
 *	they have to pay with a fifty Pound note. It then updates the till and 
 *	printing what is left in the till and what was give to the customer.
 *	
 *	OUTPUT		int - Always returns a value of 0.
 *
 *************************************************************************************************/
int main() {
	int option, cost;
	
	struct till* shops_till = malloc( sizeof (struct till) );
	struct till* customers_change;
	struct product * top_product_p;

	init_till(shops_till);
	top_product_p = 	make_node("All Stock","",0,0,0,
								make_node("Vegetables","",0,0,0,
									make_node("Carrots",POUNDS,50,20,10,NULL,
									make_node("Potatoes","",0,0,0,
										make_node("Maris Piper",POUNDS,200,50,3,NULL,
										make_node("Line White",POUNDS,150,30,4,NULL,NULL
										)),
									make_node("Cabbage",POUNDS,110,10,5,NULL,NULL
									))),
								make_node("Fruit","",0,0,0,
									make_node("Apples","",0,0,0,
										make_node("Granny Smith",POUNDS,50,15,22,NULL, 
										make_node("Disrouery",POUNDS,30,10,23,NULL,NULL
										)),
									make_node("Mellons",SINGLE,12,4,57,NULL, 
									make_node("Plums",POUNDS,50,10,25,NULL,NULL
									))),
								NULL)),
							NULL);
	do{					
		printf("Do you want to\n 0) Exit \n 1) Print tree \n 2) Buy Groceries \n\t? ");
		scanf(" %d", &option);
		switch(option) {
		 case 1:	
			print_tree(top_product_p);
			break;
		 case 2:
			cost = sell_stock(top_product_p);
			printf("Total: %dp \n", cost);
			customers_change = tender_cash(cost, shops_till);
			printf("Change given;\n");
			print_till(customers_change);
			printf("\nLeft in till;\n");
			print_till(shops_till);
			break;
		 default:
		 	break;
		}
	}while(option);
	return option;
}

