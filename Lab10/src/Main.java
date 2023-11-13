import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String, UserSession> sessions = new HashMap<>();
    static Scanner input = new Scanner(System.in);

    private static boolean getYesNo() {
        String line = input.nextLine().toLowerCase();
        return line.equals("y") || line.equals("yes") || line.equals("true"); // returns true for these three inputs
    }

    public static void main(String[] args) {
        while (true) {
            UserSession currentSession = null;
            System.out.println("Current user sessions: " + sessions.keySet());
            System.out.print("Create new session? ");
            if (getYesNo()) {
                System.out.print("Username? ");
                String username = input.nextLine();
                currentSession = new UserSession(username);
                sessions.put(username, currentSession);
            } else {
                System.out.print("Log in to an existing session? ");
                if (getYesNo()) {
                    System.out.print("Username of the session? ");
                    currentSession = sessions.get(input.nextLine());
                } else {
                    System.exit(0);
                }
            }

            currentSession.logIn();
            System.out.println(currentSession);

            System.out.print("Add an item to the shopping cart? ");
            if (getYesNo()) {
                System.out.print("Name of the item? ");
                currentSession.addToCart(input.nextLine());
            }

            System.out.print("Add to the user's balance? ");
            if (getYesNo()) {
                System.out.print("Amount to add? ");
                // Balance methods are in cents, so it must be multiplied by 100
                currentSession.addToBalance((int) (Double.parseDouble(input.nextLine()) * 100));
            }

            System.out.print("Remove an item from the shopping cart? ");
            if (getYesNo()) {
                System.out.print("Name of the item? ");
                currentSession.removeFromCart(input.nextLine());
            }

            System.out.print("Remove from the user's balance? ");
            if (getYesNo()) {
                System.out.print("Amount to remove? ");
                currentSession.removeFromBalance((int) (Double.parseDouble(input.nextLine()) * 100));
            }

            System.out.println("Current session state state:");
            System.out.println(currentSession);
            System.out.println("Logging out");
            currentSession.logOut();
            System.out.println();
        }
    }
}