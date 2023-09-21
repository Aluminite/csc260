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

    public static int evaluatePostfix(String input) {
        char[] chars = input.toCharArray();
        Stack<Integer> numbers = new Stack<>();
        for (char c : chars) {
            if (c >= '0' && c <= '9') {
                numbers.push(c - 0x30); // convert the 0-9 char to an int by subtracting 0x30
            } else {
                if (numbers.isEmpty()) throw new IllegalArgumentException("Stack is empty, invalid expression");
                int operand1 = numbers.pop();
                int operand2 = numbers.pop();
                switch (c) {
                    case '+' -> numbers.push(operand2 + operand1);
                    case '-' -> numbers.push(operand2 - operand1);
                    case '*' -> numbers.push(operand2 * operand1);
                    case '/' -> numbers.push(operand2 / operand1);
                    default -> throw new IllegalArgumentException("Invalid character detected");
                }
            }
        }
        return numbers.pop();
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

    public static String infixToPostfix(String input) {
        char[] inputChars = input.toCharArray();
        Stack<Character> numbers = new Stack<>();
        Stack<Character> others = new Stack<>();
        for (char c : inputChars) {
            if (c >= '0' && c <= '9') {
                numbers.push(c);
            } else {
                others.push(c);
            }
        }

        char[] output = new char[numbers.size() + others.size()];
        int index = output.length - 1;
        while (!others.isEmpty()) {
            output[index] = others.pop();
            index--;
        }
        while (!numbers.isEmpty()) {
            output[index] = numbers.pop();
            index--;
        }

        return String.valueOf(output);
    }
}
