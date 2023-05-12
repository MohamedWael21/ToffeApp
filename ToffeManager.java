import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * This class used to start application and manage it
 */
public class ToffeManager {
    private Customer currentCustomer;
    private ArrayList<Customer>customers;
    private ArrayList<Item> items;

    private final String ITEMS_FILE_NAME = "items";
    private final String CUSTOMERS_FILE_NAME = "customers";

    /**
     * initialize class by load customers and items from the file
     */
    public  ToffeManager(){
        currentCustomer = null;
        customers = loadCustomers();
        items = loadItems();
    }


    /**
     * This use CustomerFileManager to load customers and initialize shoppingCart of each customer
     * if it null
     * @return list of customers loaded form file
     */
    private ArrayList<Customer> loadCustomers(){
        ArrayList<Customer> customersData = CustomerFileManager.loadCustomers(CUSTOMERS_FILE_NAME);
        for(int i=0; i<customersData.size(); i++){
            if(customersData.get(i).getShoppingCart() == null){
                customersData.get(i).setShoppingCart(new ShoppingCart());
            }
        }
        return customersData;
    }

    /**
     * use ItemFileManager and load items from file
     * @return list of items in file
     */
    private ArrayList<Item> loadItems(){
        return ItemFileManager.loadItems(ITEMS_FILE_NAME);
    }

    /**
     * used for signup new Customer
     */
    public void signUp(){
        Scanner scanner = new Scanner(System.in);
        Boolean isValid;
        String username;
        do {
            isValid = false;
            System.out.println("Please Enter username: ");
            username = scanner.nextLine();
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getUsername().equals(username)) {
                    System.out.println("Username Duplicate");
                    isValid = true;
                    break;
                }
            }

        }while(isValid);

        String email;
        do {
            isValid = false;
            System.out.println("Please Enter email: ");
            email = scanner.nextLine();
            if(!isValidEmail(email)){
                System.out.println("Email is not valid\n");
                isValid = true;
                continue;
            }
            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getEmail().equals(email)) {
                    System.out.println("Email Duplicate");
                    isValid = true;
                    break;
                }
            }

        }while(isValid);

         System.out.println("Please Enter Password: ");
         String password = scanner.nextLine();

         Address address;
         String city;
         String street;
         String governorate;
         int floor;

        String otp = sendOTP(email);
        isValid = true;
        do{
            System.out.println("Please Enter OTP: ");
            String userInput = scanner.nextLine();
            if(otp.equals(userInput)) {
                System.out.println("Email verified!!\n");
                isValid = false;
            }
            else{
                System.out.println("Invalid OTP\n");
            }
        }while(isValid);


         System.out.println("Please Enter City: ");
         city = scanner.nextLine();

         System.out.println("Please Enter Street: ");
         street = scanner.nextLine();

         System.out.println("Please Enter Governorate: ");
         governorate = scanner.nextLine();

         System.out.println("Please Enter Floor: ");
         floor = scanner.nextInt();


         customers.add(new Customer(email, username, password, new Address(street, city, governorate, floor)));

         CustomerFileManager.saveCustomers(CUSTOMERS_FILE_NAME, customers);
    }

    /**
     * Used for login to customer
     */
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

    /**
     * print customers
     */
    void printCustomers(){
        for(int i=0; i<customers.size(); i++){
            System.out.println(customers.get(i).getUsername());
            System.out.println(customers.get(i).getPassword());
        }
    }

    /**
     * print Items
     */
    void printItems(){
        for(int i=0; i<items.size(); i++){
            System.out.println(items.get(i).getName());
        }
    }

    /**
     * Start application and it contains menus to print to user
     */
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
                    removeFromCart();
                    break;
                case 5:
                    checkOut();
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

    /**
     * Display items in catalog
     */
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

    /**
     * add item from customer to his shopping cart
     */
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
        CustomerFileManager.saveCustomers(CUSTOMERS_FILE_NAME, customers);
        System.out.println("Added To Cart");

    }

    /**
     * ask customer to remove item form his shopping cart
     */
    public void removeFromCart(){
        currentCustomer.getShoppingCart().displayCart();
        System.out.println("Please Enter Item Number You want to remove");
        Scanner scanner = new Scanner(System.in);
        int itemNumber = scanner.nextInt();
        if(itemNumber > currentCustomer.getShoppingCart().cartSize() || itemNumber <= 0){
            System.out.println("Invalid Item Number");
            return;
        }
        LinkedHashMap<Item, Integer> itemsInCart = (LinkedHashMap<Item, Integer>) currentCustomer.getShoppingCart().getItemsInCart();
        List<Map.Entry<Item, Integer>> entries = new ArrayList<>(itemsInCart.entrySet());
        currentCustomer.getShoppingCart().removeItem(entries.get(itemNumber-1).getKey());

        CustomerFileManager.saveCustomers(CUSTOMERS_FILE_NAME, customers);

        System.out.println("item "+entries.get(itemNumber-1).getKey().getName()+" Removed Successfully");

    }

    /**
     * checkout user
     */
    public void checkOut(){
        double totalPrice = 0.0;
        LinkedHashMap<Item, Integer> itemsInCart = (LinkedHashMap<Item, Integer>) currentCustomer.getShoppingCart().getItemsInCart();
        List<Map.Entry<Item, Integer>>  itemsToBeCheckedOut= new ArrayList<>(itemsInCart.entrySet());

        for (int i=0; i<itemsToBeCheckedOut.size(); i++){
            totalPrice += itemsToBeCheckedOut.get(i).getKey().getPrice()*itemsToBeCheckedOut.get(i).getValue();
        }

        currentCustomer.getShoppingCart().displayCart();
        System.out.println("Total Price: "+ totalPrice);
        System.out.println("Do you Want to Proceed?(y/n) ");

        char option = new Scanner(System.in).next().toLowerCase().charAt(0);

        if(option == 'y'){
               System.out.println("The order will be shipping to This Address: ");
               Address address = currentCustomer.getAddress();
               System.out.println("City: " + address.getCity());
               System.out.println("Governorate: " + address.getGovernorate());
               System.out.println("Street: " + address.getStreet());
               System.out.println("Floor: " + address.getFloor());

               System.out.println("Order has been made successfully");
               currentCustomer.getShoppingCart().clearCart();
               try{
                   Thread.sleep(5000);
               }catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
               sendOrder(Double.toString(totalPrice));
               System.out.println("Order is Delivered successfully");
               System.out.println("Order has been Closed\n\n");
               CustomerFileManager.saveCustomers(CUSTOMERS_FILE_NAME, customers);
        }



    }

    public String sendOTP(String email){
        String otp = generateOTP();
        String from = "mazenbakry62@gmail.com";
        String password = "eiqyfuapbncpsamm";
        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Your OTP for SignUp");
            message.setText("Your OTP is " + otp);
            Transport.send(message);
            System.out.println("\t\t**** Email sent successfully! ****\n");
        }
        catch(MessagingException e){
            throw new RuntimeException(e);
        }
        return otp;

    }
    public void sendOrder(String totalPrice){
        String from = "mazenbakry62@gmail.com";
        String password = "eiqyfuapbncpsamm";
        String host = "smtp.gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });
        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(currentCustomer.getEmail()));
            message.setSubject("Your Order");
            message.setText("Order has been placed\n Total price: " + totalPrice +"\n Being delivered to: " + "\nCity: " + currentCustomer.getAddress().getCity() + "\nGovernorate: " + currentCustomer.getAddress().getGovernorate()+ "\nStreet: " + currentCustomer.getAddress().getStreet() + "\nFloor: " + currentCustomer.getAddress().getFloor());
            Transport.send(message);
            System.out.println("\t\t**** Email sent successfully! ****\n");
        }
        catch(MessagingException e){
            throw new RuntimeException(e);
        }
    }
    private static String generateOTP(){
        int otpLength = 6;
        String numbers = "0123456789";
        Random random = new Random();
        char [] otp = new char[otpLength];
        for(int i=0 ; i<otpLength ; i++){
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));
        }
        return new String(otp);
    }
    public boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^\\w[\\w.-]*@([\\w-]+\\.)+[a-zA-Z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
