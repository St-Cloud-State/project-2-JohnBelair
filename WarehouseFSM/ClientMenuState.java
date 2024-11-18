import java.util.Scanner;

public class ClientMenuState implements WarehouseState {
    private Scanner scanner = new Scanner(System.in);
    private WarehouseContext context;
    private Warehouse warehouse;

    public ClientMenuState(WarehouseContext context) {
        this.context = context;
        this.warehouse = context.getWarehouse();
    }

    @Override
    public void run() {
        System.out.print("Enter client ID (or press Enter to exit): ");
        String clientId = scanner.nextLine();

        if(warehouse.getClientById(clientId) != null){
            context.setClientID(clientId);
            System.out.println("Welcome, Client " + context.getClientID() + ".");
        }
        else{
            // Validate client ID
            while (warehouse.getClientById(clientId) == null) {
                // If enter is pressed to escape while loop
                if (clientId.trim().isEmpty()){
                    System.out.println("Returning to previous menu.");
                    context.changeState(WarehouseContext.LOGIN_STATE);
                }
                else{
                    System.out.println("Invalid Client ID. Please try again.(or press Enter to exit):");
                    System.out.print("Enter client ID: ");
                    clientId = scanner.nextLine();
                }
            }
            context.setClientID(clientId);
            System.out.println("Welcome, Client " + context.getClientID() + ".");
        }
        
        boolean running = true;
        while (running) {
            System.out.println("\nClient Menu:");
            System.out.println("1. Show client details");
            System.out.println("2. Show list of products (with price)");
            System.out.println("3. Show client transactions");
            System.out.println("4. Add item to clients wishlist");
            System.out.println("5. Display clients wishlist");
            System.out.println("6. Place an order");
            System.out.println("7. Logout");
            System.out.print("Select an option: ");
        
            int choice = context.getUserInput();
            switch (choice) {
                case 1:
                    showClientDetails();
                    break;
                case 2:
                    showListOfProducts();
                    break;
                case 3:
                    showClientTransactions();
                    break;
                case 4:
                    addItemToWishlist();
                    break;
                case 5:
                    displayWishlist();
                    break;
                case 6:
                    placeOrder();
                    break;
                case 7:
                    logout();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    run();  // Retry menu
                    break;
            }
        }
    }

    private void showClientDetails() {
        System.out.println("Displaying client details for ID: " + context.getClientID());
        warehouse.displayClientByID(context.getClientID());

    }

    private void showListOfProducts() {
        this.warehouse.displayProducts();
     }

    private void showClientTransactions() {
        System.out.println("Displaying client transactions...");
        warehouse.printInvoicesByClientId(context.getClientID());
    }


    private void addItemToWishlist() {
        System.out.print("Enter product ID: ");
        String productId = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        boolean success = warehouse.addItemToWishlist(context.getClientID(), productId, quantity);
        System.out.println(success ? "Product added to wishlist." : "Failed to add product to wishlist.");
    }

    private void displayWishlist() {
        warehouse.displayWishlist(context.getClientID());
    }

    
    private void placeOrder() {
        System.out.println("Placing order for product " + context.getClientID() + "...");
        warehouse.createOrderFromWishlist(context.getClientID());
    }

    private void logout() {
        System.out.println("Logging out...");
        context.changeState(WarehouseContext.LOGIN_STATE); 
    }
}