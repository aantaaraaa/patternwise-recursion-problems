import java.util.*;

public class ExpressionAddOperators {

    /**
     * Expression Add Operators (Backtracking)
     *
     * Time Complexity: O(4^n)
     * Space Complexity: O(n)
     *
     * Problem:
     * Given a numeric string (e.g., "123"), insert '+', '-', or '*' between digits
     * to form expressions that evaluate to a target value.
     *
     * Core Idea:
     * Explore all possible expressions recursively.
     * Handle multiplication carefully by tracking the last operand used.
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: num = "123", target = 6
     *
     *                          ""
     *                         / | \
     *                       1  12  123
     *                        |
     *                        v
     *                    "1"
     *                /     |      \
     *           +2        -2       *2  ← invalid at root, start fresh
     *           /          \
     *        "1+2"        "1-2"
     *         / | \        / | \
     *      +3 -3 *3     +3 -3 *3
     *
     * Valid Expressions:
     *   - 1+2+3 = 6
     *   - 1*2*3 = 6
     *
     * Key Concepts:
     * 1. Start recursion at index 0 with an empty expression.
     * 2. At each step, extract a substring num[index...i] as the next operand.
     * 3. For every recursive call:
     *    - '+' → add operand normally.
     *    - '-' → subtract operand normally.
     *    - '*' → adjust using previous operand (handle precedence).
     * 4. Base case: if index == num.length() and currentValue == target → valid expression.
     */

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0)
            return result;

        helper(num, target, 0, 0, 0, new StringBuilder(), result);
        return result;
    }

    private void helper(String num, int target, int index,
                        long currValue, long lastOperand,
                        StringBuilder expr, List<String> result) {

        if (index == num.length()) {
            if (currValue == target)
                result.add(expr.toString());
            return;
        }

        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0')
                break;

            String part = num.substring(index, i + 1);
            long currNum = Long.parseLong(part);
            int len = expr.length();

            if (index == 0) {
                expr.append(part);
                helper(num, target, i + 1, currNum, currNum, expr, result);
                expr.setLength(len);
            } else {
                expr.append('+').append(part);
                helper(num, target, i + 1, currValue + currNum, currNum, expr, result);
                expr.setLength(len);

                expr.append('-').append(part);
                helper(num, target, i + 1, currValue - currNum, -currNum, expr, result);
                expr.setLength(len);

                expr.append('*').append(part);
                helper(num, target, i + 1,
                       currValue - lastOperand + lastOperand * currNum,
                       lastOperand * currNum, expr, result);
                expr.setLength(len);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter digit string (num): ");
        String num = sc.nextLine();

        System.out.print("Enter target value: ");
        int target = sc.nextInt();

        ExpressionAddOperators solver = new ExpressionAddOperators();
        List<String> result = solver.addOperators(num, target);

        System.out.println("\nAll possible expressions that evaluate to " + target + ":");
        for (String expr : result) {
            System.out.println(expr);
        }

        sc.close();
    }
}
