#include <ansi_c.h>
/*
   Carols Cabbage, the C Version.
*/


struct product {
	char name[80];
	char units [12];
	int in_stock, new_stock, unit_price;
	struct product * variety, * other;
};

struct till {
	int fiftie_pounds, twenty_pounds, ten_pounds, five_pounds;
	int two_pounds, one_pounds, fifty_pence, twenty_pence;
   int ten_pence, five_pence, two_pence, one_pence;
};

/*
	Makes a new node for the tree. Most of the fields of the new product are filled with the paramiters given to this function.
	Paramiters	the_name				A char array holding the name of the new product.
					the_units			A char array holding the name of te unis for the 
											new product.
					cur_stock			The number of items currently in stock.
					price				The price for one unit of the product.
					the_variety			A pointer to a specific variety of the product.
					the_other			A pointer to other products under the same heading.
	Returns 		struct product*	A pointer to the newly created product.
*/
struct product* make_node(char the_name[], char the_units[], int cur_stock, int on_order, 
							int price, struct product * the_variety, struct product * the_other
						 )
{
	struct product * tmp_ptr;
	
	tmp_ptr = malloc( sizeof (struct product) );
	strcpy( tmp_ptr->name  , the_name  );
	strcpy( tmp_ptr->units , the_units );
	tmp_ptr->in_stock 	= cur_stock;
	tmp_ptr->new_stock	= on_order;
	tmp_ptr->unit_price 	= price;
	tmp_ptr->variety 		= the_variety;
	tmp_ptr->other 		= the_other;
}

int main() {
	struct product * new_product_p;
	
	new_product_p = 	make_node("ALL Stock","",0,0,0,
								make_node("Vegetables","",0,0,0,
									make_node("Carrots","Pounds",50,20,10,NULL,
									make_node("Potatoes","",0,0,0,
										make_node("Maris Piper","Pounds",200,50,3,NULL,
										make_node("Line White","Pounds",150,30,4,NULL,NULL
										)),
									make_node("Cabbage","Pounds",110,10,5,NULL,NULL
									))),
								make_node("Fruit","",0,0,0,
									make_node("Apples","",0,0,0,
										make_node("Granny Smith","Pounds",50,15,22,NULL, 
										make_node("Disrouery","Pounds",30,10,23,NULL,NULL
										)),
									make_node("Mellons","Each",12,4,57,NULL, 
									make_node("Plums","Pounds",50,10,25,NULL,NULL
									))),
								NULL)),
							NULL);
	return 1;
}
