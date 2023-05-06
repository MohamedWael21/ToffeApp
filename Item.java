import java.io.Serializable;

/**
 * This class stores Item data such as id, name ,description, price and available quantity
 */
public class Item implements Serializable {
    private int id;
    private String name;
    private String description;

    private float price;

    private int avaliableQuantity;

    /**
     * Constructor to set data
     * @param id of item
     * @param name of item
     * @param description of item
     * @param price of item
     * @param avaliableQuantity of item
     */
    public Item(int id, String name, String description, float price, int avaliableQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.avaliableQuantity = avaliableQuantity;
    }

    /**
     *
     * @return id of item
     */
    public int getId() {
        return id;
    }

    /**
     * sets id of item
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return name of item
     */
    public String getName() {
        return name;
    }

    /**
     * sets name of item
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return description of item
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return price of item
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price of item
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     *
     * @return current available quantity of item
     */
    public int getAvaliableQuantity() {
        return avaliableQuantity;
    }

    /**
     *
     * @param avaliableQuantity
     */
    public void setAvaliableQuantity(int avaliableQuantity) {
        this.avaliableQuantity = avaliableQuantity;
    }

}
