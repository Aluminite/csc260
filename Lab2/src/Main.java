import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("String to test?");
        String text = input.nextLine();
        System.out.println(StackStuff.reverseString(text));
        System.out.println(StackStuff.reverseStringNoStack(text));
        System.out.println("String is " + (StackStuff.isPalindrome(text) ? "" : "not ") + "a palindrome");
        System.out.println("String is " + (StackStuff.isBalanced(text) ? "" : "not ") + "balanced");
    }
}