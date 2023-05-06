import java.io.*;
import java.util.ArrayList;

/**
 * This class is used to get Customers and save Customers in file
 */
public class CustomerFileManager {

    /**
     *
     * @param fileName
     * @return list of customers that loaded from file
     * load customers from file
     */
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

    /**
     *
     * @param fileName
     * @param customers
     * save customers into file
     */
    public static void saveCustomers(String fileName, ArrayList<Customer>customers){

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(customers);
        } catch (IOException e) {
            System.err.println("Error reading object from file: " + e.getMessage());
        }

    }


}
