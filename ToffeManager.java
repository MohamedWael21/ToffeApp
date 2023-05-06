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
        ArrayList<Customer> customersData = CustomerFileManager.loadCustomers(CUSTOMERS_FILE_NAME);
        for(int i=0; i<customersData.size(); i++){
            if(customersData.get(i).getShoppingCart() == null){
                customersData.get(i).setShoppingCart(new ShoppingCart());
            }
        }
        return customersData;
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

    void run(){
        boolean isRunning = true;
        // No Login User
        System.out.println("Welcome To ToffeApplication!");

        if(currentCustomer == null){
            while (isRunning){
                System.out.println("1.Display Items");
                System.out.println("2.LogIn");
                System.out.println("3.SignUp");
                System.out.println("4.Exit");
                Scanner scanner = new Scanner(System.in);
                System.out.println("Please, Enter Your Option");

                int option = scanner.nextInt();

                switch (option){
                    case 1:
                        displayItems();
                        break;
                    case 2:
                        login();
                        isRunning = false;
                        break;
                    case 3:
                        signUp();
                        break;
                    case 4:
                        return;
                    default:
                        System.out.println("Invalid Option");
                }
            }
        }

        // LogIn
        isRunning = true;
        while (isRunning){
            System.out.println("1.Display Items");
            System.out.println("2.Display Shopping Cart");
            System.out.println("3.Add Item to Shopping Cart");
            System.out.println("4.Remove Item from Shopping Cart");
            System.out.println("5.checkout");
            System.out.println("6.LogOut");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Please, Enter Your Option");

            int option = scanner.nextInt();

            switch (option){
                case 1:
                    displayItems();
                    break;
                case 2: ;
                    currentCustomer.getShoppingCart().displayCart();
                    break;
                case 3:
                    addToCart();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    currentCustomer = null;
                    run();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid Option");
            }
        }


    }

    public void displayItems(){
        if(items.size() == 0){
            System.out.println("No Items");
            return;
        }
        // Define the column headers
        String[] headers = {"Item", "Description", "Price/Unit", "Available Quantity"};

        // Define the table data
        Object[][] data = new Object[items.size()][4];

        for(int i=0;  i<items.size(); i++){
            data[i][0] = items.get(i).getName();
            data[i][1] = items.get(i).getDescription();
            data[i][2] = items.get(i).getPrice();
            data[i][3] = items.get(i).getAvaliableQuantity();
        }

        // Define the column widths
        int[] widths = {20, 20, 20, 20};

        // Print the column headers
        for (int i = 0; i < headers.length; i++) {
            System.out.printf("%-" + widths[i] + "s", headers[i]);
        }
        System.out.println();

        // Print the table data
        for (int i = 0; i < data.length; i++) {
            System.out.print((i+1)+".");
            for (int j = 0; j < data[i].length; j++) {
                System.out.printf("%-" + widths[j] + "s", data[i][j]);
            }
            System.out.println();
        }
        System.out.println();

    }

    public void addToCart(){
        displayItems();
        System.out.println("Please Enter Item Number: ");
        int itemNumber;
        Scanner scanner = new Scanner(System.in);
        itemNumber = scanner.nextInt();
        if(itemNumber > items.size() || itemNumber <= 0){
            System.out.println("Invalid Item Number");
            return;
        }
        int qunatity;

        System.out.println("Please Enter The Quantity You Want");
        qunatity = scanner.nextInt();
        if(qunatity > items.get(itemNumber-1).getAvaliableQuantity()){
            System.out.println("Not Available Quantity");
            return;
        }

        currentCustomer.getShoppingCart().addItem(items.get(itemNumber-1), qunatity);
        System.out.println("Added To Cart");

    }



}
