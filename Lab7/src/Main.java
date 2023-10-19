import java.util.Scanner;

public class Main {
    private static final SingleLinkedList<Appointment> cardiologyAppointments = new SingleLinkedList<>();
    private static final SingleLinkedList<Appointment> neurologyAppointments = new SingleLinkedList<>();
    private static final SingleLinkedList<Appointment> orthopedicAppointments = new SingleLinkedList<>();

    private static final Scanner input = new Scanner(System.in);

    private static SingleLinkedList<Appointment> chooseDepartment() {
        while (true) {
            System.out.print("Type c for cardiology, n for neurology, o for orthopedics: ");
            SingleLinkedList<Appointment> department = null;
            switch (input.nextLine().toLowerCase()) {
                case "c" -> department = cardiologyAppointments;
                case "n" -> department = neurologyAppointments;
                case "o" -> department = orthopedicAppointments;
            }
            if (department != null) return department;
        }

    }

    private static int findAppointmentByPatientName(SingleLinkedList<Appointment> department, String name) {
        int iteration = 0;
        while (iteration < department.size()) {
            if (department.get(iteration).getPatientName().equals(name)) {
                return iteration;
            }
            iteration++;
        }
        return -1;
    }

    private static Appointment lookupAppointment(SingleLinkedList<Appointment> department, String name) {
        int index = findAppointmentByPatientName(department, name);
        if (index == -1) return null;
        else return department.get(index);
    }

    private static Appointment createAppointment() {
        System.out.print("Patient name? ");
        String patientName = input.nextLine();
        System.out.print("Appointment time? ");
        String appointmentTime = input.nextLine();
        System.out.print("Reason for visit? ");
        String visitReason = input.nextLine();
        System.out.print("Extra notes? (Leave blank if none) ");
        String notes = input.nextLine();

        if (notes.isEmpty()) {
            notes = null;
        }

        return new Appointment(patientName, appointmentTime, visitReason, notes);
    }

    private static void insertAppointment() {
        SingleLinkedList<Appointment> department = chooseDepartment();
        Appointment usrApp = createAppointment();
        System.out.println("""
                Where do you want to insert it?
                b to insert the appointment at the beginning
                m to insert the appointment at a specific point in the middle
                e to insert the appointment at the end of the list""");
        switch (input.nextLine()) {
            case "b" -> department.add(0, usrApp);
            case "m" -> {
                System.out.println("Please insert where you want your appointment to be (size:" + department.size() + ")");
                department.add(Integer.parseInt(input.nextLine()), usrApp);
            }
            case "e" -> department.add(usrApp);
        }
    }

    private static void removeAppointment() {
        SingleLinkedList<Appointment> department = chooseDepartment();
        System.out.print("Patient name? ");
        String name = input.nextLine();
        int appointmentIndex = findAppointmentByPatientName(department, name);
        if (appointmentIndex == -1) {
            System.out.println("Could not find an appointment for that patient.");
        }
        department.remove(appointmentIndex);
    }


    public static void main(String[] args) {
        System.out.println("Patient Management System");
        while (true) {
            System.out.println("""
                    Hospital Appointment Management
                    i to insert an appointment
                    c to cancel an appointment
                    s to search an appointment
                    l to list all appointments
                    n to get the next appointment
                    exit to quit the script
                    """);
            switch (input.nextLine().toLowerCase()) {
                case "i" -> insertAppointment();
                case "c" -> removeAppointment();
                case "s" -> {
                    System.out.print("Name of the patient? ");
                    String patientName = input.nextLine();
                    Appointment appointment = lookupAppointment(chooseDepartment(), patientName);
                    if (appointment == null) {
                        System.out.println("Could not find an appointment for that patient.");
                    } else {
                        System.out.println(appointment);
                    }
                }
                case "l" -> {
                    System.out.println("List of all appointments:");
                    System.out.println("Cardiology:");
                    for (int i = 0; i < cardiologyAppointments.size(); i++) {
                        System.out.printf("%d. %s%n", i, cardiologyAppointments.get(i));
                    }
                    System.out.println("Neurology:");
                    for (int i = 0; i < neurologyAppointments.size(); i++) {
                        System.out.printf("%d. %s%n", i, neurologyAppointments.get(i));
                    }
                    System.out.println("Orthopedics:");
                    for (int i = 0; i < orthopedicAppointments.size(); i++) {
                        System.out.printf("%d. %s%n", i, orthopedicAppointments.get(i));
                    }
                }
                case "n" -> {
                    SingleLinkedList<Appointment> department = chooseDepartment();
                    if (department.size() == 0) {
                        System.out.println("There are no appointments for that department.");
                        break;
                    }
                    System.out.printf("Next appointment is %s%n", department.get(0));
                    department.remove(0);
                }
                case "exit" -> System.exit(0);
            }
        }
    }
}
