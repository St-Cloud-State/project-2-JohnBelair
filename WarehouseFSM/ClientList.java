import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ClientList implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Client> clients;

    public ClientList() {
        clients = new LinkedList<>();
    }

    public boolean addClient(Client client) {
        return clients.add(client);
    }

    public Client getClient(String clientId) {
        for (Client client : clients) {
            if (client.getId().equals(clientId)) {
                return client;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return clients.isEmpty();
    }

    public void displayAllClients() {
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        } else {
            for (Client client : clients) {
                System.out.println(client);
            }
        }
    }

    public void displayClientByID(String clientID) {
        if (clients.isEmpty()) {
            System.out.println("No clients found.");
        }
        else {
            for (Client client : clients) {
                if(client.getId().equals(clientID)){
                    System.out.println(client);
                }
            }
        }
    }

    public void displayClientsWithOutstandingBalance() {
        boolean foundNegativeBalance = false;

        for (Client client : clients) {
            if(client.getBalance() < 0){
                System.out.println(client);
                foundNegativeBalance = true;
            }
        }

        if (!foundNegativeBalance) {
            System.out.println("No clients with outstanding balance.");
        }
    }

    public String[] getClientIds() {
        return clients.stream().map(Client::getId).toArray(String[]::new);
    }
}
