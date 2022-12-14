import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    private final Map<Integer, Caja> checkouts = new HashMap<>();
    private final Map<Integer, CarritoCompras> shoppingCarts = new HashMap<>();

    private final List<Cliente> waitingClients = new LinkedList<>();
    private final List<Cliente> shoppingClients = new LinkedList<>();

    public Main() {
        for(int i = 1; i <= 3; i++) {
            checkouts.put(i, new Caja(i));
        }

        //Definir los carritos de compra
        for(int i = 1; i <= 25; i++) {
            shoppingCarts.put(i, new CarritoCompras(i));
        }
    }
    public void start() {

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            Cliente client = new Cliente(nombresAleatorios.string(5));
            System.out.println("-------------------------------------");
            System.out.println("Ha llegado un nuevo cliente llamado " + client.getName());


            if(assignCart(client)) {
                shoppingClients.add(client);
                client.setShoppingTime(nombresAleatorios.number(1));
                System.out.println("Se le ha asignado el carrito " + client.getShoppingCartId());
                System.out.println("-------------------------------------");
            } else {
                waitingClients.add(client);
                System.out.println("No se han encontrado carritos disponibles!, se le agregara a la cola de espera");
            }

            //Definir el tiempo para la llegada de los clientes
        }, 5, 5, TimeUnit.SECONDS);

        //Asignacion de carritos y/o cajas
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::updateWaitingClients, 10, 10, TimeUnit.SECONDS);
        //Asignacion de carritos y/o cajas
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::updateShoppingClients, 8, 8, TimeUnit.SECONDS);

        //Asignacion de carritos y/o cajas
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(this::updateCheckouts, 40, 40 , TimeUnit.SECONDS);
    }


    private void updateCheckouts() {
        for (Caja checkout : checkouts.values()) {
            Cliente currentClient = checkout.getClient();
            if(currentClient != null) {
                checkout.setClient(null);
                System.out.println("El cliente " + currentClient.getName() + " ha finalizado su compra!");
            }

            try {
                Cliente newClient = checkout.getClientQueue().get(0);
                if(newClient != null) {
                    checkout.setClient(newClient);
                    checkout.getClientQueue().remove(newClient);
                    System.out.println("El cliente " + newClient.getName() + " est?? pagando sus productos");
                }
            } catch (IndexOutOfBoundsException e) {
            }

        }
    }

    private void updateShoppingClients() {
        for (Cliente shoppingClient : shoppingClients) {
            shoppingCarts.get(shoppingClient.getShoppingCartId()).setClient(null);
            shoppingClient.setShoppingCartId(0);
            shoppingClients.remove(shoppingClient);
            assignCheckout(shoppingClient);
            System.out.println("El cliente " + shoppingClient.getName() + " ha terminado de elegir sus productos y se le asigno la caja " +  shoppingClient.getCheckoutId());
        }
    }


    private void updateWaitingClients() {
        for (Cliente waitingClient : waitingClients) {
            if(assignCart(waitingClient)) {
                shoppingClients.add(waitingClient);
                waitingClient.setShoppingTime(nombresAleatorios.number(3));
                waitingClients.remove(waitingClient);
                System.out.println("Se le ha asignado el carrito " + waitingClient.getShoppingCartId() + "al cliente " + waitingClient.getName());
            }
        }
    }

    private void assignCheckout(Cliente client) {
        List<Caja> checkoutList = new ArrayList<>(checkouts.values());

        checkoutList.sort((checkout1, checkout2) -> {

            int size1 = checkout1.getClientQueue().size();
            int size2 = checkout2.getClientQueue().size();

            return Integer.compare(size1, size2);
        });

        Caja checkout = checkoutList.get(0);
        checkout.getClientQueue().add(client);
        client.setCheckoutId(checkout.getId());
    }

    private boolean assignCart(Cliente client) {
        List<CarritoCompras> shoppingCartList = new ArrayList<>(shoppingCarts.values());

        shoppingCartList.sort((cart1, cart2) -> {
            int value1 = cart1.isAvailable() ? 0 : 1;
            int value2 = cart2.isAvailable() ? 0 : 1;
            return Integer.compare(value1, value2);
        });

        CarritoCompras shoppingCart = shoppingCartList.get(0);

        if(!shoppingCart.isAvailable()) {
            return false;
        }

        shoppingCart.setClient(client.getName());
        client.setShoppingCartId(shoppingCart.getId());
        return true;
    }
}

