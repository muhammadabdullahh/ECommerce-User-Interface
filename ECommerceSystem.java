/*Muhammad Abdullah
501112499
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;



public class ECommerceSystem
{
    private HashMap<String, Product>  products = new HashMap<String, Product>();
    private ArrayList<Customer> customers = new ArrayList<Customer>();	
    private ArrayList<ProductOrder> orders   = new ArrayList<ProductOrder>();
    private ArrayList<ProductOrder> shippedOrders   = new ArrayList<ProductOrder>();
    
    
    // These variables are used to generate order numbers, customer id's, product id's 
    private int orderNumber = 500;
    private int customerId = 900;
    private int productId = 700;
    
    // General variable used to store an error message when something is invalid (e.g. customer id does not exist)  
    String errMsg = null;
    
    // Random number generator
    Random random = new Random();
    
    public ECommerceSystem()
    {
    	// NOTE: do not modify or add to these objects!! - the TAs will use for testing
    	// If you do the class Shoes bonus, you may add shoe products
    	
    	// Create some products. Notice how generateProductId() method is used
        try {
            for( Product p: this.parseProducts())
                products.put(p.getId(), p);

        } catch (Exception e) {
            System.out.println("~ FileNotFoundException | IOException Caught.");
        }
		
    	
    	// Create some customers. Notice how generateCustomerId() method is used
    	customers.add(new Customer(generateCustomerId(),"Inigo Montoya", "1 SwordMaker Lane, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Prince Humperdinck", "The Castle, Florin"));
    	customers.add(new Customer(generateCustomerId(),"Andy Dufresne", "Shawshank Prison, Maine"));
    	customers.add(new Customer(generateCustomerId(),"Ferris Bueller", "4160 Country Club Drive, Long Beach"));

    }
    
    private ArrayList<Product> parseProducts() throws FileNotFoundException, IOException
    {
        ArrayList<Product> prods = new ArrayList<Product>();

        ArrayList<String> prsdLines = new ArrayList<String>();

        File document = new File("products.txt");
        Scanner scans = new Scanner(document);
            

        while (scans.hasNextLine())
            prsdLines.add(scans.nextLine());
        
        for(int i = 0; i < prsdLines.size() - 4; i+=5) {

            String category = prsdLines.get(i);
            String name = prsdLines.get(i+1);
            Double price = Double.valueOf(prsdLines.get(i+2));
            if (category.equals("SHOES")) {
                String[] stocks = prsdLines.get(i+3).split(" ");
                int
                    blackSize6 = Integer.valueOf(stocks[0]),
                    blackSize7 = Integer.valueOf(stocks[1]),
                    blackSize8 = Integer.valueOf(stocks[2]),
                    blackSize9 = Integer.valueOf(stocks[3]),
                    blackSize10 = Integer.valueOf(stocks[4]),
                    brownSize6 = Integer.valueOf(stocks[5]),
                    brownSize7 = Integer.valueOf(stocks[6]),
                    brownSize8 = Integer.valueOf(stocks[7]),
                    brownSize9 = Integer.valueOf(stocks[8]),
                    brownSize10 = Integer.valueOf(stocks[9]);

                String brand = prsdLines.get(i+4);
                i++;

                // add SHOE object to prods
                prods.add(new Shoes(name, this.generateProductId(), Double.valueOf(price), brand, blackSize6, blackSize7, blackSize8, blackSize9, blackSize10, brownSize6, brownSize7, brownSize8, brownSize9, brownSize10));
                continue;
            }
            else if (category.equals("BOOKS")) {
                String[] stocks = prsdLines.get(i+3).split(" ");
                String paperbackStock = stocks[0];
                String hardcoverStock = stocks[1];

                String[] misc = prsdLines.get(i+4).split(":");
                String title = misc[0];
                String author = misc[1];
                String publicationYear = misc[2];
                i++;

                // add BOOK object to prods
                prods.add(new Book(name, this.generateProductId(), Double.valueOf(price), Integer.valueOf(paperbackStock), Integer.valueOf(hardcoverStock), title, author, Integer.valueOf(publicationYear)));
                continue;
            }
            
            int stockCount = Integer.valueOf(prsdLines.get(i+3));

            // add NON-BOOK/SHOE object to prods
            prods.add(new Product(name, this.generateProductId(), price, stockCount, Product.Category.valueOf(category)));
            continue;
        }
        return prods;

    }


    private String generateOrderNumber()
    {
    	return "" + orderNumber++;
    }

    private String generateCustomerId()
    {
    	return "" + customerId++;
    }
    
    private String generateProductId()
    {
    	return "" + productId++;
    }
    
    public String getErrorMessage()
    {
    	return errMsg;
    }
    
    public void printAllProducts()
    {
    	for (Product p : products.values())
    		p.print();
    }
    
    // Print all products that are books. See getCategory() method in class Product
    public void printAllBooks()
    {
    	for (Product p : products.values()) {
            if (p.getCategory() == Product.Category.BOOKS)
                p.print();
        }	
    }
    public void printAllShoes()
    {
    	for (Product p : products.values()){
        if(p.getCategory() == Product.Category.SHOES)
          p.print();
      }
    }
    
    // Print all current orders
    public void printAllOrders()
    {
    	for (ProductOrder o : orders)
    		o.print();
    }
    // Print all shipped orders
    public void printAllShippedOrders()
    {
    	for (ProductOrder s : shippedOrders)
    		s.print();
    }
    
    // Print all customers
    public void printCustomers()
    {
    	for (Customer c : customers)
    		c.print();
    }
    /*
     * Given a customer id, print all the current orders and shipped orders for them (if any)
     */
    public boolean printOrderHistory(String customerId)
    {
			for(Customer c : customers) {
				if (c.getId().equals(customerId)) {
						// Print current orders of this customer 
						System.out.println("Current Orders of Customer " + customerId);

						for (ProductOrder po : orders) {
								if (po.getCustomer().getId() == c.getId()) {
										po.print();
								}
						}
						// Print shipped orders of this customer 
						System.out.println("\nShipped Orders of Customer " + customerId);
						for (ProductOrder so : shippedOrders) {
								if (so.getCustomer().getId() == c.getId()) {
										so.print();
								}
						}
						return true;
				}
		}
		
		// If customer doesn't exist, throw exception
        throw new UnknownCustomerException(customerId);
    }
    public String orderProduct(String productId, String customerId, String productOptions)
    {
      for(Customer c : customers) {
				if (c.getId().equals(customerId)) {  

						for(Product p : products.values()) {
								if (p.getId().equals(productId)) { 

										if (p.validOptions(productOptions)) {  

												if(p.getStockCount(productOptions) > 0) {  

														String orderNumStr = this.generateOrderNumber();    
														orders.add(new ProductOrder(orderNumStr, p, c, productOptions));
														p.reduceStockCount(productOptions);
                                                        p.incStat();
														return orderNumStr;

												} else {
														throw new ProductOutOfStockException(p.getId());
												}

										} else {
                                            throw new IllegalProductOptionsException(productOptions);
										}

								}
						}
						// if product not found
                        throw new UnknownProductException(productId);

				}
			}
			// if customer not found
			throw new UnknownCustomerException(customerId);
		}
    
    /*
     * Create a new Customer object and add it to the list of customers
     */
    
    public boolean createCustomer(String name, String address)
    {
    	// Check name parameter to make sure it is not null or ""
    	// If it is not a valid name, set errMsg (see video) and return false
    	// Repeat this check for address parameter
    	
    	// Create a Customer object and add to array list
			if(!name.isEmpty() && name != null){
				if(!address.isEmpty() && address != null){
					customers.add(new Customer(generateCustomerId(),name, address));
					return true;
				}
				throw new InvalidCustomerAddressException(address);
			}
			throw new InvalidCustomerNameException(name);
    }
    
    public ProductOrder shipOrder(String orderNumber)
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
    	// Retrieve the order from the orders array list, remove it, then add it to the shippedOrders array list
    	// return a reference to the order
			for(ProductOrder po : orders){
				if(po.getOrderNumber().equals(orderNumber)){
					shippedOrders.add(po);
					orders.remove(po);
					return po;
				}
			}
			throw new InvalidOrderNumberException(orderNumber);
    }
    
    /*
     * Cancel a specific order based on order number
     */
    public boolean cancelOrder(String orderNumber)
    {
      // Check if order number exists first. If it doesn't, set errMsg to a message (see video) 
    	// and return false
    	for(ProductOrder po : orders){
				if(po.getOrderNumber().equals(orderNumber)){
					orders.remove(po);
					return true;
				}
			}
			throw new InvalidOrderNumberException(orderNumber);
    }
    
    // Sort products by increasing price
    public void sortByPrice()
    {
  	  ArrayList<Product> mapList = new ArrayList<Product>(this.products.values());
        for (int i = 0; i < mapList.size() -1; i++) 
        {
            for (int j = 0; j < mapList.size() -i -1; j++) 
            {
                if (mapList.get(j).getPrice() > mapList.get(j+1).getPrice()) 
                {
                    Product temp = mapList.get(j);
                    mapList.set(j, mapList.get(j+1));
                    mapList.set(j+1, temp);
                }
            }
        }
        this.products.clear();

        for (Product p: mapList) {
            this.products.put(p.getId(), p);
            p.print();
        }
    }
    
    
    public void sortByName()
    {
        ArrayList<Product> mapList = new ArrayList<Product>(this.products.values());
        
        for (int i = 0; i < mapList.size() - 1; i++) 
        {    
            for (int j = i + 1; j < mapList.size(); j++) 
            {
                
                if (mapList.get(i).getName().compareTo(mapList.get(j).getName()) > 0) 
                {
                    Product tempHolder = mapList.get(i);
                    mapList.set(i, mapList.get(j));
                    mapList.set(j, tempHolder);
                }
            }
        }
         this.products.clear();
         for (Product p: mapList) {
             this.products.put(p.getId(), p);
             p.print();
         }
    }
    
        
    // Sort customers alphabetically by customer name
    public void sortCustomersByName()
    {
  	  Customer temp;
			int n = customers.size();
        for (int j = 0; j < n - 1; j++)
        {
            for (int i = j + 1; i < n; i++)
            {
                if (customers.get(j).getName().compareTo(customers.get(i).getName()) > 0)
                {
                    temp = customers.get(j);
                    customers.set(j, customers.get(i));
                    customers.set(i, temp);
                }
            }
        }
    }

	public void sortAuthorByYear(String author) {
        
        ArrayList<Product> authorBooks = new ArrayList<Product>();
        // Add book products of specified author to authorBooks array list
        for(Product p: products.values())
            if ( (p.getCategory() == Product.Category.BOOKS) && (((Book) p).getAuthor().equals(author)) )
                authorBooks.add(p);
        // Sort by increasing year
        for (int i = 0; i < authorBooks.size()-1; i++) {
            for (int j = 0; j < authorBooks.size()-i-1; j++) {

                if ( ((Book) authorBooks.get(j)).getYear() > ((Book) authorBooks.get(j+1)).getYear() ) {
                    Product temp = authorBooks.get(j);
                    authorBooks.set(j, authorBooks.get(j+1));
                    authorBooks.set(j+1, temp);
                }
            }
        }

        // print resulting arraylist
        for (Product p: authorBooks)
            p.print();
    }
    
    public boolean isThisBook(String productId){
        for(Product p: products.values()){
            if(p.getId().equals(productId)){
                if(p.getCategory() == Product.Category.BOOKS){
                    return true;
                }
            }
        }
        this.errMsg = "Product: " + productId + " Is Not A Book";
        return false;
    }

    public void addToCart(String customerID, String productID, String productOptions) {
        for(Customer cus : customers) 
        {
            if (cus.getId().equals(customerID)) 
            { 
                for (Product p : products.values()) {

                    if (p.getId().equals(String.valueOf(productID))) 
                    {  

                        if (p.validOptions(productOptions)) 
                        { 
                            cus.getCart().addToCart(customerID, p, productOptions);
                            return;
                            
                        } else throw new IllegalProductOptionsException(productOptions);
                    }
                }
                throw new UnknownProductException(productID);
            }
        }
        throw new UnknownCustomerException(customerID);
    }

    public void orderItems(String customerID) {
        for(Customer cus : customers) {
            if (cus.getId().equals(String.valueOf(customerID))) {
                for(CartItem item: cus.getCart().getCartItems()) 
                {

                    this.orderProduct(item.getProductID(), item.getCustomerID(), item.getProductOptions());
                    item.incStat();
                }                    
                cus.getCart().emptyCart();
                return;
            }
        }
        throw new UnknownCustomerException(customerID);
    }

    public void removeCartItem(String customerID, String productID) {
        for(Customer cus : customers) {
            if (cus.getId().equals(customerID)) 
            {
                for (Product p : products.values()) {
                    if (p.getId().equals(productID)) {  
                        cus.getCart().removeFromCart(customerID, productID);
                        return;
                    }
                }
                throw new UnknownProductException(productID);
            }
        }
        throw new UnknownCustomerException(customerID);
    }

    public void printCart(String customerID) {
        for(Customer cus : customers) {
            if (cus.getId().equals(String.valueOf(customerID))) { 
                for(CartItem item: cus.getCart().getCartItems())
                    item.getProduct().print();
                return;
            }
        }
        throw new UnknownCustomerException(customerID);
    }

    public void printStats(){
        ArrayList<Product> mapList = new ArrayList<Product>(this.products.values());
        for (int i = 0; i < mapList.size() -1; i++) 
        {
            for (int j = 0; j < mapList.size() -i -1; j++) 
            {
                if (mapList.get(j).getStat() < mapList.get(j+1).getStat()) 
                {
                    Product temp = mapList.get(j);
                    mapList.set(j, mapList.get(j+1));
                    mapList.set(j+1, temp);
                }
            }
        }
        this.products.clear();

        for (Product p: mapList) {
            this.products.put(p.getId(), p);
            p.printStat();
        }
    }
}


// Classes for error exceptions
class ProductOutOfStockException extends RuntimeException 
{
  	
    public ProductOutOfStockException() {
        super("Product out of stock.");
    }
      
    public ProductOutOfStockException(String productID) {
        super("Product (id: " + productID + ") out of stock");
    }
}

class InvalidCustomerNameException extends RuntimeException 
{
  	
    public InvalidCustomerNameException() {
        super("Invalid Customer Name.");
    }
      
    public InvalidCustomerNameException(String invalidName) 
    {
        super("Invalid Customer Name: \'" + invalidName + "\'");
    }
}

class InvalidCustomerAddressException extends RuntimeException {	
    public InvalidCustomerAddressException() {
        super("Invalid Customer Address.");
    }   
    public InvalidCustomerAddressException(String invalidAddress) {
        super("Invalid Customer Address: \'" + invalidAddress + "\'");
    }
}

class InvalidOrderNumberException extends RuntimeException {	
    public InvalidOrderNumberException() {
        super("Invalid Product Order Number.");
    }   
    public InvalidOrderNumberException(String invalidOrderNumber) 
    {
        super("Invalid Product Order Number: \'" + invalidOrderNumber + "\'");
    }
}

class UnknownCustomerException extends RuntimeException {	
    public UnknownCustomerException() {
        super("Customer Not Found ");
    }    
    public UnknownCustomerException(String customerID) 
    {
        super("Customer (id: " + customerID + ") Not Found ");
    }
}

class UnknownProductException extends RuntimeException {	
    public UnknownProductException() {
        super("Product Not Found ");
    }  
    public UnknownProductException(String productID)  {
        super("Product (id: " + productID + ") Not Found ");
    }
}

class IllegalProductOptionsException extends RuntimeException {
    public IllegalProductOptionsException() {
        super("Invalid Product Options ");
    } 
    public IllegalProductOptionsException(String productOptions) {
        super("Invalid Product Options: \'" + productOptions + "\'");
    }
}