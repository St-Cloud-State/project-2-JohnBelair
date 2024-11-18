import java.util.Scanner;

public class WarehouseContext {
    public static final int LOGIN_STATE = 0;
    public static final int CLIENT_MENU_STATE = 1;
    public static final int CLERK_MENU_STATE = 2;
    public static final int MANAGER_MENU_STATE = 3;

    private WarehouseState[] states;
    private int[][] transitionMatrix;
    private int currentState;

    private String clientId;
    private Scanner scanner;
    private Warehouse warehouse = new Warehouse();

    public WarehouseContext() {
        // Initialize scanner for input
        scanner = new Scanner(System.in);

        // Initialize states
        states = new WarehouseState[4];
        states[LOGIN_STATE] = new LoginState(this);
        states[CLIENT_MENU_STATE] = new ClientMenuState(this);
        states[CLERK_MENU_STATE] = new ClerkMenuState(this);
        states[MANAGER_MENU_STATE] = new ManagerMenuState(this);

        // Initialize transition matrix (rows are current states, columns are inputs)
        transitionMatrix = new int[6][8];
        transitionMatrix[LOGIN_STATE][0] = CLIENT_MENU_STATE;  // Option 1: Go to ClientMenuState
        transitionMatrix[LOGIN_STATE][1] = CLERK_MENU_STATE;   // Option 2: Go to ClerkMenuState
        transitionMatrix[LOGIN_STATE][2] = MANAGER_MENU_STATE; // Option 3: Go to ManagerMenuState
        transitionMatrix[CLERK_MENU_STATE][3] = CLIENT_MENU_STATE; // Option 4: ClerkMenuState to ClientMenuState
        transitionMatrix[MANAGER_MENU_STATE][4] = CLERK_MENU_STATE; // Option 5: ManagerMenuState to ClerkMenuState

        // Set initial state
        currentState = LOGIN_STATE;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setClientID(String clientId) {
        this.clientId = clientId;
    }

    public String getClientID() {
        return clientId;
    }

    public void changeState(int input) {
        // Check if the input leads to a valid transition
        int nextState = transitionMatrix[currentState][input];
        if (nextState != -1) {
            currentState = nextState;
            states[currentState].run();
        } else {
            System.out.println("Invalid option. Please try again.");
            states[currentState].run();  // Retry the current state
        }
    }

    public int getUserInput() {
        int input = -1;
        try {
            input = scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine();  // Clear the invalid input
            System.out.println("Invalid input. Please enter a number.");
        }
        return input;
    }

    public static void main(String[] args) {
        WarehouseContext context = new WarehouseContext();
        context.states[context.currentState].run();  // Start in the initial state
    }
}
