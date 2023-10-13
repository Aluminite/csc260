import java.util.Scanner;

public class TrainSystem {
    private static final Scanner input = new Scanner(System.in);
    private static final SingleLinkedList<RailCar> train = new SingleLinkedList<>();

    private static Engine createEngine() {
        System.out.print("Enter a description for this engine car: ");
        return new Engine(input.nextLine());
    }

    private static Coach createCoach() {
        System.out.print("Enter a description for this coach car: ");
        return new Coach(input.nextLine());
    }

    private static void addCoachAtPos() {
        System.out.print("Position to add the car at? ");
        train.add(Integer.parseInt(input.nextLine()) - 1, createCoach());
    }

    private static void removeCar() {
        System.out.print("Number of car to remove? ");
        train.remove(Integer.parseInt(input.nextLine()) - 1);
    }

    private static void showCars() {
        System.out.println("Current train cars:");
        for (int i = 0; i < train.size(); i++) {
            System.out.printf("%d: %s%n", i + 1, train.get(i));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Train Car Management System");
        while (true) {
            System.out.println("""
                    Please choose an option:
                    1 to add an engine to the front of the train
                    2 to add a coach to the end of the train
                    3 to add a coach at a specific position
                    4 to remove a car
                    5 to show the train's cars
                    e to exit
                    """);

            switch (input.nextLine().toLowerCase()) {
                case "1" -> train.add(0, createEngine());
                case "2" -> train.add(createCoach());
                case "3" -> addCoachAtPos();
                case "4" -> removeCar();
                case "5" -> showCars();
                case "e" -> System.exit(0);
            }
        }
    }
}