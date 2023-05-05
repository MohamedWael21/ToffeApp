import java.io.Serializable;

public class Address implements Serializable {
    private String street;

    private String city;
    private String governorate;

    private int floor;

    public Address(String street, String city, String governorate, int floor) {
        this.street = street;
        this.city = city;
        this.governorate = governorate;
        this.floor = floor;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGovernorate() {
        return governorate;
    }

    public void setGovernorate(String governorate) {
        this.governorate = governorate;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }
}
