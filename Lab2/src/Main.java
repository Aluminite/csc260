import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("String to test?");
        String text = input.nextLine();

        System.out.println("String is " + (StackStuff.isBalanced(text) ? "" : "not ") + "balanced");
        try {
            System.out.println("Postfix evaluation of string is " + StackStuff.evaluatePostfix(text));
        } catch (IllegalArgumentException unused) {
            System.out.println("Unable to evaluate postfix");
        }
        System.out.println("Infix to postfix: " + StackStuff.infixToPostfix(text));
        System.out.println("Reversed string: " + StackStuff.reverseString(text));
        System.out.println("String is " + (StackStuff.isPalindrome(text) ? "" : "not ") + "a palindrome");
    }
}