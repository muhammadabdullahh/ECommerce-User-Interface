/*Muhammad Abdullah
501112499
*/

import java.util.*;

public class Cart {
    private ArrayList<CartItem> cartItems;

    public Cart() {
        this.cartItems = new ArrayList<CartItem>();
    }
    
    public ArrayList<CartItem> getCartItems(){
        return this.cartItems;
    }

    public void emptyCart(){
        this.cartItems.clear();
    }

    public boolean removeFromCart(String customerID, String productID){
        for(int i = 0; i < this.cartItems.size(); i++){
            if((this.cartItems.get(i).getProductID().equals(productID)) && this.cartItems.get(i).getCustomerID().equals(customerID)){
                this.cartItems.remove(i);
                return true;
            }
        }
        return false;
    }

    public void addToCart(String customerID, Product product, String productOptions){
        this.cartItems.add(new CartItem(customerID, product , productOptions));
    }

}