import java.io.Serializable;

/**
 * This Class is used for Store Address info of customer
 */
public class Address implements Serializable {
    private String street;

    private String city;
    private String governorate;

    private int floor;

    /**
     *
     * @param street
     * @param city
     * @param governorate
     * @param floor
     */
    public Address(String street, String city, String governorate, int floor) {
        this.street = street;
        this.city = city;
        this.governorate = governorate;
        this.floor = floor;
    }

    /**
     *
     * @return Street of the customer
     */
    public String getStreet() {
        return street;
    }

    /**
     *
     * @param street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     *
     * @return City of the customer
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return Governorate of The Customer
     */
    public String getGovernorate() {
        return governorate;
    }

    /**
     *
     * @param governorate
     */
    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    /**
     *
     * @return floor of the customer
     */
    public int getFloor() {
        return floor;
    }

    /**
     *
     * @param floor
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }
}
