import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = -449549085472883272L;

    private String username;
    private String password;

    private Address address;


    private ShoppingCart shoppingCart;

    public Customer(String username, String password, Address address) {
        this.username = username;
        this.password = password;
        this.address = address;
        shoppingCart = new ShoppingCart();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ShoppingCart getShoppingCart(){ return shoppingCart; }

    public void setShoppingCart(ShoppingCart cart){ this.shoppingCart = cart; }

}
