public class Contact {
    public final String name;
    public final String number;

    public Contact(String name, String number) {
        if (!number.matches("^[0-9]{10}$")) throw new IllegalArgumentException("Invalid phone number");
        this.name = name;
        this.number = number;
    }
}
