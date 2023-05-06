import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This class is responsible for holding current shopping cart and manipulating it
 */
public class ShoppingCart implements Serializable {

    Map<Item, Integer> cart;

    /**
     * Default constructor to initialize cart with LinkedHashMap
     */
    public ShoppingCart(){
        cart = new LinkedHashMap<>();
    }

    /**
     * This method adds object of item and its quantity to the cart
     * @param item  object of class Item
     * @param quantity quantity needed to be purchased
     */
    public void addItem(Item item, int quantity){
        cart.put(item, quantity);
    }

    /**
     * This method removes object of item from the cart
     * @param item object of class Item
     */
    public void  removeItem(Item item){
        cart.remove(item);
    }

    /**
     * This method clears the cart
     */
    public void clearCart(){
        cart.clear();
    }

    /**
     *
     * @return cart
     */
    public Map<Item, Integer> getItemsInCart(){
        return  cart;
    }

    /**
     * This method is responsible for displaying current cart
     */
    public void displayCart(){
        if(cart.isEmpty()){
            System.out.println("No Items In Cart");
            return;
        }
        System.out.println("Number of Items In Cart: " + cart.size());
        // Define the table data
        Object[][] data = new Object[cart.size()][3];
        int index=0;
        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
            data[index][0] = entry.getKey().getName();
            data[index][1] = entry.getKey().getPrice()*entry.getValue();
            data[index][2] = entry.getValue();
            index++;
        }

        // Define the column headers
        String[] headers = {"Item", "Price", "Quantity"};



        // Define the column widths
        int[] widths = {20, 20, 20};

        // Print the column headers
        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%-" + widths[i] + "s", headers[i]);
        }
        System.out.println();

        // Print the table data
        for (int i = 0; i < data.length; i++) {
            System.out.print((i+1)+".");
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%-" + widths[j] + "s", data[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     *
     * @return cart size
     */
    public int cartSize(){
        return cart.size();
    }

}
