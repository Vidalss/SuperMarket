public class Cliente {

    private final String name;
    private int checkoutId;
    private int shoppingCartId;
    private int shoppingTime;
    private int shoppingCurrentTime;

    public Cliente(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCheckoutId() {
        return checkoutId;
    }

    public void setCheckoutId(int checkoutId) {
        this.checkoutId = checkoutId;
    }

    public int getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(int shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public int getShoppingTime() {
        return shoppingTime;
    }

    public void setShoppingTime(int shoppingTime) {
        this.shoppingTime = shoppingTime;
    }

    public int getShoppingCurrentTime() {
        return shoppingCurrentTime;
    }

    public void addShopingCurrentTime() {
        this.shoppingCurrentTime++;
    }
}
