public class Participant {
    public String name;
    public String email;

    public Participant(String name, String email) {
        if (!email.matches("(.*)@(.*)")) throw new IllegalArgumentException("Invalid email address");
        this.name = name;
        this.email = email;
    }
}
