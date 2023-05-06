import java.io.Serializable;

/**
 * This is responsible for store customer data
 */
public class Customer implements Serializable {
    private static final long serialVersionUID = -449549085472883272L;

    private String username;
    private String password;

    private Address address;


    private ShoppingCart shoppingCart;

    /**
     *
     * @param username
     * @param password
     * @param address
     */
    public Customer(String username, String password, Address address) {
        this.username = username;
        this.password = password;
        this.address = address;
        shoppingCart = new ShoppingCart();
    }

    /**
     *
     * @return username of the customer
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return password of the customer
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return address object of the customer
     */
    public Address getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     *
     * @return shopping-cart object of customer
     */
    public ShoppingCart getShoppingCart(){ return shoppingCart; }

    /**
     * 
     * @param cart
     */
    public void setShoppingCart(ShoppingCart cart){ this.shoppingCart = cart; }

}
