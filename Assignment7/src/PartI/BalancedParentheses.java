package PartI;

import java.util.Stack;

public class BalancedParentheses {

    public static boolean isBalanced(String inString) {
        Stack<Character> stack = new Stack<>();
        for (Character c : inString.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        boolean result = isBalanced("(()()()())");
        System.out.println(result);
        result = isBalanced("(((())))");
        System.out.println(result);
        result = isBalanced("((((((())");
        System.out.println(result);
    }
}
