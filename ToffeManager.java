import java.util.ArrayList;
import java.util.Scanner;

public class ToffeManager {
    private Customer currentCustomer;
    private ArrayList<Customer>customers;
    private ArrayList<Item> items;

    private final String ITEMS_FILE_NAME = "items";
    private final String CUSTOMERS_FILE_NAME = "customers";

    public  ToffeManager(){
        currentCustomer = null;
        customers = loadCustomers();
        items = loadItems();
    }

    private ArrayList<Customer> loadCustomers(){
        return CustomerFileManager.loadCustomers(CUSTOMERS_FILE_NAME);
    }

    private ArrayList<Item> loadItems(){
        return ItemFileManager.loadItems(ITEMS_FILE_NAME);
    }


    public void signUp(){
        Scanner scanner = new Scanner(System.in);
        Boolean isDuplicate;
        String username;
        do {
            isDuplicate = false;
            System.out.println("Please Enter username: ");
            username = scanner.nextLine();
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getUsername().equals(username)) {
                    System.out.println("Username Duplicate");
                    isDuplicate = true;
                    break;
                }
            }

        }while(isDuplicate);

         System.out.println("Please Enter Password: ");
         String password = scanner.nextLine();

         Address address;
         String city;
         String street;
         String governorate;
         int floor;

         System.out.println("Please Enter City: ");
         city = scanner.nextLine();

         System.out.println("Please Enter Street: ");
         street = scanner.nextLine();

         System.out.println("Please Enter Governorate: ");
         governorate = scanner.nextLine();

         System.out.println("Please Enter Floor: ");
         floor = scanner.nextInt();

         customers.add(new Customer(username, password, new Address(street, city, governorate, floor)));

         CustomerFileManager.saveCustomers(CUSTOMERS_FILE_NAME, customers);
    }

    public  void login(){
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;

        int currentUser = -1;
        do{
            System.out.println("Please, Enter Username: ");
            username = scanner.nextLine();
            for(int i=0; i<customers.size(); i++){
                if(customers.get(i).getUsername().equals(username)){
                    currentUser = i;
                    break;
                }
            }
            if(currentUser == -1){
                System.out.println("Username not found");
            }
        }while (currentUser == -1);


        do{
            System.out.println("Please, Enter Password: ");
            password = scanner.nextLine();
            if(!customers.get(currentUser).getPassword().equals(password)){
                System.out.println("Invalid Password");
            }else {
                break;
            }
        }while (true);

        System.out.println("LogIn successfully!!");

        currentCustomer = customers.get(currentUser);

    }

    void printCustomers(){
        for(int i=0; i<customers.size(); i++){
            System.out.println(customers.get(i).getUsername());
            System.out.println(customers.get(i).getPassword());
        }
    }
    void printItems(){
        for(int i=0; i<items.size(); i++){
            System.out.println(items.get(i).getName());
        }
    }






}
