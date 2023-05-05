import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name;
    private String description;

    private float price;

    private int avaliableQuantity;

    public Item(int id, String name, String description, float price, int avaliableQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.avaliableQuantity = avaliableQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getAvaliableQuantity() {
        return avaliableQuantity;
    }

    public void setAvaliableQuantity(int avaliableQuantity) {
        this.avaliableQuantity = avaliableQuantity;
    }

}
