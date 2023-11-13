import java.util.ArrayList;

public class UserSession {
    private final String userName;

    private boolean isLoggedIn;
    private final ArrayList<String> shoppingCart;

    private int balance; // in cents

    public UserSession(String userName) {
        this.userName = userName;
        this.isLoggedIn = false;
        this.shoppingCart = new ArrayList<>();
        this.balance = 0;
    }

    public String getUserName() {
        return userName;
    }

    public void logIn() {
        isLoggedIn = true;
    }

    public void logOut() {
        isLoggedIn = false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void addToCart(String item) {
        shoppingCart.add(item);
    }

    public void removeFromCart(String item) {
        for (int i = 0; i < shoppingCart.size(); i++) {
            if (shoppingCart.get(i).equals(item)) {
                shoppingCart.remove(i);
                i--; // must be done otherwise items will be skipped
            }
        }
    }

    public String getShoppingCart() {
        return shoppingCart.toString();
    }

    public void addToBalance(int amount) {
        balance = balance + amount;
    }

    public void removeFromBalance(int amount) {
        balance = balance - amount;
    }

    public int getBalance() {
        return balance;
    }

    public String toString() {
        return String.format("""
                User %s's session
                %sogged in
                Cart items: %s
                Balance of $%.2f""",
                getUserName(), isLoggedIn() ? "L" : "Not l", getShoppingCart(), getBalance() / 100.0);
    }
}
