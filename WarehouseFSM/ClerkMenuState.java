import java.util.Scanner;

public class ClerkMenuState implements WarehouseState {
    private Scanner scanner = new Scanner(System.in);
    private WarehouseContext context;
    private Warehouse warehouse;

    public ClerkMenuState(WarehouseContext context) {
        this.context = context;
        this.warehouse = context.getWarehouse();
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\nClerk Menu:");
            System.out.println("1. Add a Client");
            System.out.println("2. Show List of Products");
            System.out.println("3. Show List of Clients");
            System.out.println("4. Show List of Clients with Outstanding Balance");
            System.out.println("5. Record payment from a Client");
            System.out.println("6. Become a Client");
            System.out.println("7. Logout");
            System.out.print("Select an option: ");

            int choice = context.getUserInput();
            switch (choice) {
                case 1:
                    addClient();
                    break;
                case 2:
                    showListOfProducts();
                    break;
                case 3:
                    showListOfClients();
                    break;
                case 4:
                    showClientsWithOutstandingBalance();
                    break;
                case 5:
                    recordPaymentFromClient();
                    break;
                case 6:
                    becomeClient();
                    break;
                case 7:
                    logout();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void addClient(){
        System.out.print("Enter client name: ");
        String name = scanner.nextLine();
        System.out.print("Enter client address: ");
        String address = scanner.nextLine();
        boolean success = warehouse.addClient(name, address);
        System.out.println(success ? "Client added successfully." : "Failed to add client.");
    }

    private void showListOfProducts(){
        warehouse.displayProducts();
    }

    private void showListOfClients(){
        warehouse.displayClients();
    }

    private void showClientsWithOutstandingBalance(){
        warehouse.displayClientsWithOutstandingBalance();
    }

    private void recordPaymentFromClient(){
        System.out.print("Enter client ID: ");
        String clientId = scanner.nextLine(); // Get client ID input
        System.out.print("Enter payment amount: ");
        double amount = Double.parseDouble(scanner.nextLine()); // Get payment amount
        warehouse.recordPayment(clientId, amount); // Call warehouse to handle payment
    }

    private void becomeClient(){
        context.changeState(3);
    }

    private void logout(){
        System.out.println("Logging out...");
        context.changeState(WarehouseContext.LOGIN_STATE); 
    }
}
