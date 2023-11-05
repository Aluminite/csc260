import java.util.Scanner;

public class Main {
    // The reverse method is implemented in SingleLinkedList.java.
    
    public static void main(String[] args) {
        System.out.println("""
                Type one integer per line to make a single linked list.
                When done, input an empty line.""");

        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            try {
                list.add(Integer.parseInt(input.nextLine()));
            } catch (NumberFormatException unused) {
                break;
            }
        }
        System.out.printf("List before reverse: %s%n", list);
        list.reverse();
        System.out.printf("Reversed list: %s%n", list);
    }
}