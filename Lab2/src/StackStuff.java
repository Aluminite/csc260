import java.util.Stack;

public class StackStuff {
    public static String reverseString(String input) {
        Stack<Character> chars = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            chars.push(input.charAt(i));
        }

        String output = "";
        while (!chars.isEmpty()) {
            output = output + chars.pop();
        }

        return output;
    }

    public static boolean palindromeCheck(String input) {
        return reverseString(input).equals(input);
    }

    public static boolean balanceCheck(String input) {
        Stack<Character> chars = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (currentChar == '{' || currentChar == '[' || currentChar == '(') {
                chars.push(currentChar);
            } else if (currentChar == '}' || currentChar == ']' || currentChar == ')') {
                if (chars.isEmpty()) return false;
                char popChar = chars.pop();
                if (popChar == '{' && currentChar != '}' ||
                    popChar == '[' && currentChar != ']' ||
                    popChar == '(' && currentChar != ')') {
                    return false;
                }
            }
        }
        // if there are still characters left in the stack afterward, it's not balanced
        return chars.isEmpty();
    }
}
