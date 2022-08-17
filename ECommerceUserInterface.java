/*Muhammad Abdullah
501112499
*/
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple E-Commerce System (like Amazon)

public class ECommerceUserInterface
{
	public static void main(String[] args)
	{
		// Create the system
		ECommerceSystem amazon = new ECommerceSystem();

		Scanner scanner = new Scanner(System.in);
		System.out.print(">");
		
		// Process keyboard actions
		while (scanner.hasNextLine())
		{
			String action = scanner.nextLine();
			
			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;

			else if (action.equalsIgnoreCase("PRODS"))	// List all products for sale
			{
				amazon.printAllProducts(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books for sale
			{
				amazon.printAllBooks(); 
			}
			else if (action.equalsIgnoreCase("SHOES"))	// List all shoes for sale
			{
				amazon.printAllShoes(); 
			}
			else if (action.equalsIgnoreCase("CUSTS")) 	// List all registered customers
			{
				amazon.printCustomers();	
			}
			else if (action.equalsIgnoreCase("ORDERS")) // List all current product orders
			{
				amazon.printAllOrders();	
			}
			else if (action.equalsIgnoreCase("SHIPPED"))	// List all orders that have been shipped
			{
				amazon.printAllShippedOrders();	
			}
			else if (action.equalsIgnoreCase("NEWCUST"))	// Create a new registered customer
			{
				String name = "";
				String address = "";
				
				System.out.print("Name: ");
				if (scanner.hasNextLine())
					name = scanner.nextLine();
				
				System.out.print("\nAddress: ");
				if (scanner.hasNextLine())
					address = scanner.nextLine();
				
				try 
				{
					amazon.createCustomer(name, address);
				} 
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}
			}
			else if (action.equalsIgnoreCase("SHIP"))	// ship an order to a customer
			{
					String orderNumber = "";
        
					System.out.print("Order Number: ");
					// Get order number from scanner
					if (scanner.hasNextLine())
						orderNumber = scanner.nextLine();
					// Ship order to customer (see ECommerceSystem for the correct method to use
					try 
					{
						amazon.shipOrder(orderNumber);
						System.out.println("Order: " + orderNumber + " Shipped");
					} 
					catch (Exception e) 
					{
						System.out.println(e.getMessage());
					}
						
			}
			else if (action.equalsIgnoreCase("CUSTORDERS")) // List all the current orders and shipped orders for this customer id
			{
				String customerId = "";

				System.out.print("Customer Id: ");
				// Get customer Id from scanner
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				// Print all current orders and all shipped orders for this customer
				try 
				{
                    amazon.printOrderHistory(customerId);
                } 
				catch (Exception e) 
				{
                    System.out.println(e.getMessage());
                }
				
			}
			else if (action.equalsIgnoreCase("ORDER")) // order a product for a certain customer
			{
				String productId = "";
				String customerId = "";

				System.out.print("Product Id: ");
			  	// Get product Id from scanner
			  	if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
			  	// Get customer Id from scanner
			  	if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				// Order the product. Check for valid orderNumber string return and for error message set in ECommerceSystem
				try 
				{
					String orderNumber = amazon.orderProduct(productId, customerId, "");
					System.out.println("Order: " + orderNumber);
				} 
				catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}
				
			}
			else if (action.equalsIgnoreCase("ORDERBOOK")) // order a book for a customer, provide a format (Paperback, Hardcover or EBook)
			{
				String productId = "";
				String customerId = "";
				String options = "";

				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				System.out.print("\nFormat [Paperback Hardcover EBook]: ");
				// get book forma and store in options string
				if (scanner.hasNextLine())
					options = scanner.nextLine();
				
				// Order product. Check for error mesage set in ECommerceSystem
				// Print order number string if order number is not null
				//Check if book
				if(!amazon.isThisBook(productId)){
					System.out.println(amazon.getErrorMessage());
				}
				else{
					try {
                        String orderNumber = amazon.orderProduct(productId, customerId, options);
                        System.out.println("Order: " + orderNumber);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
				}
			}
			else if (action.equalsIgnoreCase("ORDERSHOES")) // order shoes for a customer, provide size and color 
			{
				String productId = "";
				String customerId = "";
				String options = "";
				
				System.out.print("Product Id: ");
				// get product id
				if (scanner.hasNextLine())
					productId = scanner.nextLine();
				System.out.print("\nCustomer Id: ");
				// get customer id
				if (scanner.hasNextLine())
					customerId = scanner.nextLine();
				System.out.print("\nSize: \"6\" \"7\" \"8\" \"9\" \"10\": ");
				// get shoe size and store in options
				if (scanner.hasNextLine())
					options = scanner.nextLine();
					options+=" ";
				System.out.print("\nColor: \"Black\" \"Brown\": ");
				// get shoe color and append to options
				if (scanner.hasNextLine())
					options += scanner.nextLine();
				//order shoes
				try {
                    String orderNumber = amazon.orderProduct(productId, customerId, options);
                    System.out.println("Order: " + orderNumber);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
				
			}
			
			
			else if (action.equalsIgnoreCase("CANCEL")) // Cancel an existing order
			{
				String orderNumber = "";

				System.out.print("Order Number: ");
				// get order number from scanner
				if (scanner.hasNextLine())
					orderNumber = scanner.nextLine();
				// cancel order. Check for error
				try {
                    amazon.cancelOrder(orderNumber);
                    System.out.println("Order: " + orderNumber + " Canceled");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
			}	
			else if (action.equalsIgnoreCase("SORTBYPRICE")) // sort products by price
			{
				amazon.sortByPrice();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort products by name (alphabetic)
			{
				amazon.sortByName();
			}
			else if (action.equalsIgnoreCase("SORTCUSTS")) // sort products by name (alphabetic)
			{
				amazon.sortCustomersByName();
			}
			else if (action.equalsIgnoreCase("BOOKSBYAUTHOR")) 
			{ 
                String authorName = "";
                System.out.print("Enter Author Name: ");
                if (scanner.hasNextLine())
                    authorName = scanner.nextLine();
                if (!authorName.isEmpty())
                    amazon.sortAuthorByYear(authorName);
                else
                    System.out.println("Invalid Author Name");
            }

			else if (action.equalsIgnoreCase("ADDTOCART")) { // sort products by name (alphabetic)
                String
                    productID = "",
                    customerID = "",
                    productOptions = "";
                System.out.print("Product ID: ");
                if (scanner.hasNextLine())
                    productID = scanner.nextLine();
                // get cust ID from scanner
                System.out.print("Customer ID: ");
                if (scanner.hasNextLine())
                    customerID = scanner.nextLine();

                // get productOptions
                System.out.print("Product Options: ");
                if (scanner.hasNextLine())
                    productOptions = scanner.nextLine();
                try {
                    amazon.addToCart(customerID, productID, productOptions);
                    System.out.printf("Product (id: %s) added to Customer (id: %s) cart!\n", productID, customerID);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

			else if (action.equalsIgnoreCase("REMCARTITEM")) { // sort products by name (alphabetic)
                String
                    productID = "",
                    customerID = "";
                System.out.print("Product ID: ");
                if (scanner.hasNextLine())
                    productID = scanner.nextLine();
                System.out.print("Customer ID: ");
                if (scanner.hasNextLine())
                    customerID = scanner.nextLine();
                try {
                    amazon.removeCartItem(customerID, productID);
                    System.out.printf("Product (id: %s) has been removed from Customer (id: %s) cart!\n", productID, customerID);
                } 
				catch (Exception e) 
				{
                    System.out.println(e.getMessage());
                }
            }

			else if (action.equalsIgnoreCase("ORDERITEMS")) { // sort products by name (alphabetic)
                String customerID = "";
                // get cust ID from scanner
                System.out.print("Customer ID: ");
                if (scanner.hasNextLine())
                    customerID = scanner.nextLine();

                try {
                    amazon.orderItems(customerID);
                    System.out.println("All items in cart were ordered!");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

			else if (action.equalsIgnoreCase("PRINTCART")) { // sort products by name (alphabetic)
                String customerID = "";
                // get cust ID from scanner
                System.out.print("Customer ID: ");
                if (scanner.hasNextLine())
                    customerID = scanner.nextLine();

                try {
                    amazon.printCart(customerID);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

			else if(action.equalsIgnoreCase("STATS")){
				amazon.printStats();
			}

			System.out.print("\n>");
		}
	}
}
