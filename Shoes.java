/*Muhammad Abdullah
501112499
*/
/* A shoe is a product with additional info such as brand

    A show also comes in different sizes and colours ("Black", " Brown") ("6", "7", "8", "9", "10")

    The format is specific as a specific "stock type" in get/set/reduce stockcount methods.
*/
public class Shoes extends Product {

    private String brand;

    //stock related info
    int blackSize6;
    int blackSize7;
    int blackSize8;
    int blackSize9;
    int blackSize10;
    int brownSize6;
    int brownSize7;
    int brownSize8;
    int brownSize9;
    int brownSize10;

    // Contructor for shoes
    public Shoes(String name, String id, double price, String brand, int blackSize6, int blackSize7, int blackSize8, int blackSize9, int blackSize10, int brownSize6, int brownSize7, int brownSize8, int brownSize9, int brownSize10){
        super(name, id, price, 0, Product.Category.SHOES);
        this.brand = brand;
        this.blackSize6 = blackSize6;
        this.blackSize7 = blackSize7;
        this.blackSize8 = blackSize8;
        this.blackSize9 = blackSize9;
        this.blackSize10 = blackSize10;
        this.brownSize6 = brownSize6;
        this.brownSize7 = brownSize7;
        this.brownSize8 = brownSize8;
        this.brownSize9 = brownSize9;
        this.brownSize10 = brownSize10;
    }

    //Check if a valid format
    public boolean validOptions(String productOption){
        if(productOption.equalsIgnoreCase("6 Black") || productOption.equalsIgnoreCase("7 Black") || productOption.equalsIgnoreCase("8 Black") || productOption.equalsIgnoreCase("9 Black") || productOption.equalsIgnoreCase("10 Black") || productOption.equalsIgnoreCase("6 Brown") || productOption.equalsIgnoreCase("7 Brown") || productOption.equalsIgnoreCase("8 Brown") || productOption.equalsIgnoreCase("9 Brown") || productOption.equalsIgnoreCase("10 Brown"))
            return true;
        return false;
    }

    // Override getstockcount() in super class
    public int getStockCount(String productOptions){
        if(productOptions.equalsIgnoreCase("6 Black"))
            return this.blackSize6;
        else if(productOptions.equalsIgnoreCase("7 Black"))
            return this.blackSize7;
        else if(productOptions.equalsIgnoreCase("8 Black"))
            return this.blackSize8;
        else if(productOptions.equalsIgnoreCase("9 Black"))
            return this.blackSize9;
        else if(productOptions.equalsIgnoreCase("10 Black"))
            return this.blackSize10;
        else if(productOptions.equalsIgnoreCase("6 Brown"))
            return this.brownSize6;
        else if(productOptions.equalsIgnoreCase("7 Brown"))
            return this.brownSize7;
        else if(productOptions.equalsIgnoreCase("8 Brown"))
            return this.brownSize8;
        else if(productOptions.equalsIgnoreCase("9 Brown"))
            return this.brownSize9;
        else
            return this.brownSize10;
    }

    // Set stock for each colour and size
    public void setStockCount(int stockCount, String productOptions){
        if(productOptions.equalsIgnoreCase("6 Black"))
            blackSize6 = stockCount;
        else if(productOptions.equalsIgnoreCase("7 Black"))
            this.blackSize7 = stockCount;
        else if(productOptions.equalsIgnoreCase("8 Black"))
            this.blackSize8 = stockCount;
        else if(productOptions.equalsIgnoreCase("9 Black"))
            this.blackSize9 = stockCount;
        else if(productOptions.equalsIgnoreCase("10 Black"))
            this.blackSize10 = stockCount;
        else if(productOptions.equalsIgnoreCase("6 Brown"))
            this.brownSize6 = stockCount;
        else if(productOptions.equalsIgnoreCase("7 Brown"))
            this.brownSize7 = stockCount;
        else if(productOptions.equalsIgnoreCase("8 Brown"))
            this.brownSize8 = stockCount;
        else if(productOptions.equalsIgnoreCase("9 Brown"))
            this.brownSize9 = stockCount;
        else
            this.brownSize10 = stockCount;
    }

    // Subtracts 1 stock count from given productOptions
    public void reduceStockCount(String productOptions){
        if(productOptions.equalsIgnoreCase("6 Black"))
            blackSize6--;
        else if(productOptions.equalsIgnoreCase("7 Black"))
            this.blackSize7--;
        else if(productOptions.equalsIgnoreCase("8 Black"))
            this.blackSize8--;
        else if(productOptions.equalsIgnoreCase("9 Black"))
            this.blackSize9--;
        else if(productOptions.equalsIgnoreCase("10 Black"))
            this.blackSize10--;
        else if(productOptions.equalsIgnoreCase("6 Brown"))
            this.brownSize6--;
        else if(productOptions.equalsIgnoreCase("7 Brown"))
            this.brownSize7--;
        else if(productOptions.equalsIgnoreCase("8 Brown"))
            this.brownSize8--;
        else if(productOptions.equalsIgnoreCase("9 Brown"))
            this.brownSize9--;
        else
            this.brownSize10--;
    }

    //Print
    public void print(){
        super.print();
        System.out.print(" Brand: " + this.brand);
    }
}