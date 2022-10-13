import java.util.LinkedList;
import java.util.List;

public class Caja {

    private final int id;
    private final List<Cliente> clientQueue;
    private Cliente client;

    public Caja(int id) {
        this.id = id;
        clientQueue = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public List<Cliente> getClientQueue() {
        return clientQueue;
    }
}
