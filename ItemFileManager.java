import java.io.*;
import java.util.ArrayList;

/**
 * This class is responsible for loading and saving item data
 */
public class ItemFileManager {
    /**
     * This method is responsible for loading item data to an array
     * @param fileName name of the file to be opened
     * @return array of items
     */
    public static ArrayList<Item> loadItems(String fileName) {
        ArrayList<Item> items = new ArrayList<Item>();

        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            items = (ArrayList<Item>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return  new ArrayList<Item>();
        }

        return  items;
    }

    /**
     * This method is responsible for saving item data to the file
     * @param fileName name of the file to be opened
     * @param items array of items to be saved in file
     */
    public static void saveItems(String fileName, ArrayList<Item>items){

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(items);
        } catch (IOException e) {
            System.err.println("Error reading object from file: " + e.getMessage());
        }

    }
}
