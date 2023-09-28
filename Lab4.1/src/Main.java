import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Contact> contacts = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Contact Management System");
        while (true) {
            System.out.println("""
                    Choose an option:
                    a to add a contact
                    r to remove a contact
                    s to search for a contact by name
                    l to list all contacts
                    e to exit""");

            switch (input.nextLine().toLowerCase()) {
                case "a" -> addContact();
                case "r" -> removeContact();
                case "s" -> searchContact();
                case "l" -> listContacts();
                case "e" -> System.exit(0);
            }
        }
    }

    private static void addContact() {
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        for (Contact contact : contacts) {
            if (contact.name.equals(name)) {
                System.out.println("Name is already used in the system.");
                return;
            }
        }

        System.out.print("Enter Phone: ");
        String number = input.nextLine();
        if (!number.matches("^[0-9]{10}$")) {
            System.out.println("Phone number is invalid.");
            return;
        }

        contacts.add(new Contact(name, number));
        System.out.println("Contact added successfully!");
    }

    private static void removeContact() {
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).name.equals(name)) {
                contacts.remove(i);
                System.out.println("Contact removed successfully!");
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    private static void searchContact() {
        System.out.print("Enter Name: ");
        String name = input.nextLine();
        for (Contact contact : contacts) {
            if (contact.name.equals(name)) {
                System.out.println("Phone: " + contact.number);
                return;
            }
        }
        System.out.println("Contact not found.");
    }

    private static void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("No contacts in the system.");
            return;
        }
        for (Contact contact : contacts) {
            System.out.println("Name: " + contact.name);
            System.out.println("Number: " + contact.number);
            System.out.println();
        }
    }
}