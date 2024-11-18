public class LoginState implements WarehouseState {
    private WarehouseContext context;

    public LoginState(WarehouseContext context) {
        this.context = context;
    }

    @Override
    public void run() {
        System.out.println("\nSelect your role:");
        System.out.println("0. Client");
        System.out.println("1. Clerk");
        System.out.println("2. Manager");
        System.out.print("Enter choice: ");

        int choice = context.getUserInput();
        context.changeState(choice);  // Pass choice to context for transition
    }
}

