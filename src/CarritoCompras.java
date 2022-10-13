public class CarritoCompras {

    private final int id;
    private String client;

    public CarritoCompras(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public boolean isAvailable() {
        return client == null;
    }
}
