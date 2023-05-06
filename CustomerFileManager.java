import java.io.*;
import java.util.ArrayList;

public class CustomerFileManager {

    public static ArrayList<Customer> loadCustomers(String fileName) {
        ArrayList<Customer> customers = new ArrayList<Customer>();

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            customers = (ArrayList<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return  new ArrayList<Customer>();
        }

        return  customers;
    }
    public static void saveCustomers(String fileName, ArrayList<Customer>customers){

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(customers);
        } catch (IOException e) {
            System.err.println("Error reading object from file: " + e.getMessage());
        }

    }


}
