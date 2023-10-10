import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final ArrayList<Participant> participants = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Participant Management System");
        while (true) {
            System.out.println("""
                    Choose an option:
                    r to register a participant
                    d to deregister a participant
                    c to check if a participant is registered
                    l to list all participants
                    e to exit""");

            switch (input.nextLine().toLowerCase()) {
                case "r" -> registerParticipant();
                case "d" -> deregisterParticipant();
                case "c" -> checkParticipant();
                case "l" -> listParticipants();
                case "e" -> System.exit(0);
            }
        }
    }

    private static void registerParticipant() {
        System.out.print("Enter Name: ");
        String name = input.nextLine();

        System.out.print("Enter Email: ");
        String email = input.nextLine();
        if (!email.matches("(.*)@(.*)")) {
            System.out.println("Email is invalid.");
            return;
        }
        for (Participant participant : participants) {
            if (participant.email.equals(email)) {
                System.out.println("Email is already used in the system.");
                return;
            }
        }

        participants.add(new Participant(name, email));
        System.out.println("Participant added successfully!");
    }

    private static void deregisterParticipant() {
        System.out.print("Enter email: ");
        String email = input.nextLine();
        for (int i = 0; i < participants.size(); i++) {
            if (participants.get(i).email.equals(email)) {
                participants.remove(i);
                System.out.println("Participant removed successfully!");
                return;
            }
        }
        System.out.println("Participant not found.");
    }

    private static void checkParticipant() {
        System.out.print("Enter email: ");
        String email = input.nextLine();
        for (Participant participant : participants) {
            if (participant.email.equals(email)) {
                System.out.println("Participant name: " + participant.name);
                return;
            }
        }
        System.out.println("No participant found with the provided email.");
    }

    private static void listParticipants() {
        if (participants.isEmpty()) {
            System.out.println("No registered participants.");
            return;
        }
        for (Participant Participant : participants) {
            System.out.println("Name: " + Participant.name);
            System.out.println("Email: " + Participant.email);
            System.out.println();
        }
    }
}