/*Muhammad Abdullah
501112499
*/
public class CartItem extends Product{
    private Product product;
    private String customerID;
    private String productOptions;

    public CartItem(String customerID, Product product, String productOptions) {
        this.productOptions = productOptions;
        this.customerID = customerID;
        this.product = product;
    }

    public String getCustomerID() { 
        return this.customerID; 
    }
    
    public Product getProduct() { 
        return this.product; 
    }

    public String getProductOptions() { 
        return this.productOptions; 
    }
	public void setProductOptions(String productOptions) { 
        this.productOptions = productOptions; 
    }

	public void setProduct(Product product) { 
        this.product = product; 
    }

    public String getProductID() { 
        return this.product.getId(); 
    }
    
}