import java.util.Scanner;


public class ManagerMenuState implements WarehouseState {
    private Scanner scanner = new Scanner(System.in);
    private WarehouseContext context;
    private Warehouse warehouse;

    public ManagerMenuState(WarehouseContext context) {
        this.context = context;
        this.warehouse = context.getWarehouse();
    }

    @Override
    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\nManager Menu:");
            System.out.println("1. Add a product");
            System.out.println("2. Display the waitlist for a product");
            System.out.println("3. Receive a shipment");
            System.out.println("4. Become a Clerk");
            System.out.println("5. Logout");
            System.out.print("Select an option: ");

            int choice = context.getUserInput();
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displaywaitlistproduct();
                    break;
                case 3:
                    recieveshipment();
                    break;
                case 4:
                    becomeClerk();
                    break;
                case 5:
                    logout();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void addProduct(){
        System.out.print("Enter Product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter sale price: ");
        float sales = Float.parseFloat(scanner.nextLine());
        boolean success = warehouse.addProduct(name, quantity, sales);
        System.out.println(success ? "Product added successfully." : "Failed to add Product.");
    }

    private void displaywaitlistproduct(){
        System.out.println("Enter product ID: ");
        String prductID = scanner.nextLine();
        warehouse.searchProductWaitlist(prductID);
    }

    private void recieveshipment(){
        System.out.print("Enter Product ID: ");
        String ID = scanner.nextLine();
        System.out.print("Enter quanity : ");
        int quantity = Integer.parseInt(scanner.nextLine());
        warehouse.receiveShipmentFromUser(ID, quantity);
    }

    private void becomeClerk(){
        context.changeState(4);
    }


    private void logout(){
        System.out.println("Logging out...");
        context.changeState(WarehouseContext.LOGIN_STATE); 
    }
}
