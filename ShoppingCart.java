import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart implements Serializable {

    Map<Item, Integer> cart;

    public ShoppingCart(){
        cart = new HashMap<>();
    }

    public void addItem(Item item, int quantity){
        cart.put(item, quantity);
    }

    public void  removeItem(Item item){
        cart.remove(item);
    }

    public void clearCart(){
        cart.clear();
    }

    public Map<Item, Integer> getItemsInCart(){
        return  cart;
    }

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

}
