/*							__RetailP2.c__
			___Carols Cabbage Rides Again, the C Version___
			
	This Program answers part two of Assignment Three.
	It is built on tp of RetailP1.c and adds to it the functions for asking 
	customers what they want to buy.
	
	Constants Defined;
		FALSE, TRUE
	Structres and functions created;
		ask_quantity(), sell_stock(). 
	Modifid from RetailP1.c;
		main().
	
	Author:- Ian Wallis
	Last revised:- 24th March, 2001
	Copyright:- Ian Wallis, U.W.A. Dept. Computer Science.
*/

#include <stdlib.h>
#include <errno.h>

#define POUNDS "pound"
#define SINGLE "individual"
#define FALSE 0
#define TRUE  1

/*
	Poduct is a structure defining a node in a tree of products on sale in a 
	grocery shop. Each product has a name and a name for it's units, both 
	represented as character arrays. Three integers representing the number of 
	units currently in stock, the number of units ordered and waiting to be 
	dilivered and the price per unit. There are also two pointers to other 
	products. The first product pointer points to a product which defines a more 
	accurate variety of this product. The second product is one which comes 
	under the same heading as this product. These variables are called name, 
	units, in_stock, new_stock, unit_price, varietys and others respectivly
*/
struct product{
	char name[80];
	char units [12];
	int in_stock, new_stock, unit_price;
	struct product * varietys, * others;
};


/*
	Makes a new node for the tree. Most of the fields of the new product are 
	filled with the paramiters given to this function.
	Paramiters	char the_name		A char array holding the name of the new 
											product.
					char the_units		A char array holding the name of te unis for 
											the new product.
					int cur_stock		The number of items currently in stock.
					int on_order		The number of items currently on order.
					int price			The price for one unit of the product.
					struct product * the_variety	
											A pointer to a specific variety of the product.
					struct product * the_other		
											A pointer to other products under the same 
											heading.
	Returns 		struct product*	A pointer to the newly created product.
*/
struct product* make_node(char the_name[], char the_units[], 
								  int cur_stock, int on_order, int price,
								  struct product * the_variety, struct product * the_other
								  ) {
	struct product * tmp_ptr;
	
	tmp_ptr = malloc( sizeof (struct product) );
	strcpy( tmp_ptr->name  , the_name  );
	strcpy( tmp_ptr->units , the_units );
	tmp_ptr->in_stock 	= cur_stock;
	tmp_ptr->new_stock	= on_order;
	tmp_ptr->unit_price 	= price;
	tmp_ptr->varietys		= the_variety;
	tmp_ptr->others 		= the_other;
}

/*
	Prints the details of the product pointed to by the given pointer.
	All the details are printed straight to the command prompt.
	Paramiters		struct product *pp		A pointer to the product to print the 
														details of
*/
void print_product(struct product *pp) {
	printf("%s sold at %dp per %s. %d in stock, %d on order\n",
				pp->name, pp->unit_price, pp->units, pp->in_stock, pp->new_stock);
}

/*
	Prints the details of each node in the tree with the given node as the root.
	The details are printed by calling print_product() on each node.
	All the details are printed to the command prompt.
	Paramiters	struct product *pp	A pointer to the root node of the tree.
*/
void print_tree(struct product *pp) {
	print_product(pp);
	if( pp->varietys != NULL ) {
		print_tree(pp->varietys);
	} else {
		printf("There are no varieties\n\n");
	}
	if( pp->others != NULL ) {
		print_tree( pp->others );
	}
}

/*
	Askes the customer for the number of units they want to buy.
	Prints out the details of the product pointed to by the given pointer. Then, 
	using printf() and scanf(), it asks the user to input an amount. This input 
	is checked against the current stock of the product and will only continue 
	if the amount is in stock. This function then returns the number of items 
	orderd.
	Paramiters		struct product *pp		The product which is for sale.
	Returns			int 							The number of units of the product 
														the customer wants to buy.
*/
int ask_quantity(struct product *pp) {
	int quantity;
	int valid = FALSE;
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

/*
	Asks the customer how much of each item of stock they want to buy.
	Goes through each product in turn displaying its name, price per unit and 
	how many units are in stock. Keeps a running total of how much the customer 
	has spent as well as changing the records of how much of each product is in 
	stock.
	Paramiters	struct product *pp		A pointer to the root of the tree of 
													products
	Returns 		int							the total cost of all the items to buy
*/
int sell_stock(struct product *pp) {
	int quantity, price;
	if(pp->varietys == NULL) {
		quantity = ask_quantity(pp);
		price = quantity * (pp->unit_price);
		pp->in_stock -= quantity;
	} else {
		printf("%s\n", pp->name);
		price = sell_stock(pp->varietys);
		printf("sub totle  %dp\n", price);
	}
	if(pp->others !=NULL) {
		price += sell_stock(pp->others);
	}
	return price;
}

/*
	Creates the tree by feeding values into the make_node function. Then prints 
	a menu to the screen asking if the user would like to see what is in stock 
	or if they would like to buy some stock. If the user selects to print the 
	stock list print_tree() is called. If the user selects to buy some products 
	sell_stock() is called. Once the user has selected all the items they wish 
	to buy, the total cost of the transaction is printed to the screen.
	Returns			int 		Always returns a value of 1.
*/
int main() {
	int option, cost;
	struct product * top_product_p;
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
			break;
		 default:
		 	break;
		}
	}while(option);
	return option;
}

