
/**
 * Algorithm to equip the shop
 *
// Create instances of the SupplierList
SupplierList allSuppliers = new SupplierList();

// Create instances of the ProductList
ProductList Safeways = new ProductList();
ProductList Coop = new ProductList();


// First create all the suppliers
for each supplier that we require
{
     create a new supplier    e.g. Supplier temp = new Supplier()

     ask for details & assign to supplier
          e.g.  temp.setName(t.promptForString("Enter name "));

     add to supplier list
          e.g. allSuppliers.addSupplier(temp)
}

// Now create the products
for each product that we require
{
     create a new product    e.g. Product temp = new Product()

     ask for details & assign to product
          e.g.  temp.setName(t.promptForString("Enter name "));
                temp.setNumInStock(t.promptForInt("Enter stock no "));

     set the supplier details
         First ask for supplier name e.g. Kelloggs
         Search Supplier list for a supplier whose name matches
                theSupplier = allSuppliers.findSupplier("Kelloggs");
                temp.setSupplier(theSupplier)

     add to product list
          e.g. safeways.addProduct(temp)
}


}