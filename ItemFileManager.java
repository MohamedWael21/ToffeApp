import java.io.*;
import java.util.ArrayList;

public class ItemFileManager {
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
    public static void saveItems(String fileName, ArrayList<Item>items){

        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(items);
        } catch (IOException e) {
            System.err.println("Error reading object from file: " + e.getMessage());
        }

    }
}
