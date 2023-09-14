import java.util.Stack;

public class StackStuff {
    public static String reverseString(String input) {
        Stack<Character> chars = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            chars.push(input.charAt(i));
        }

        char[] output = new char[chars.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = chars.pop();
        }

        return String.valueOf(output);
    }

    public static String reverseStringNoStack(String input) {
        char[] chars = input.toCharArray();
        char[] output = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            output[i] = chars[chars.length - 1 - i];
        }

        return String.valueOf(output);
    }

    public static boolean isPalindrome(String input) {
        String lowercase = input.toLowerCase();
        return reverseString(lowercase).equals(lowercase);
    }

    public static boolean isBalanced(String input) {
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
