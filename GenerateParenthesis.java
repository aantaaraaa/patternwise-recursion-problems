import java.util.*;

public class GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        /**
         * Time Complexity: O(2^(2n))
         * Space Complexity: O(2n) (due to recursion stack)
         * 
         * Brute Force (Recursive) Approach:
         * ---------------------------------
         * 1. Generate all possible strings of '(' and ')' of length 2n.
         * 2. For each generated string, check if it is valid.
         * 3. Add valid strings to the result list.
         * 4. This approach generates invalid combinations and filters them later.
         * 5. Very inefficient for large n (exponential growth).
         */

        /*
        List<String> res = new ArrayList<>();
        char[] curr = new char[2 * n];
        bruteForce(res, curr, 0, n);
        return res;
        */

        /**
         * Time Complexity: O(4^n / √n)
         * Space Complexity: O(2n) (recursion + string building)
         * 
         * Optimal (Recursive Backtracking) Approach:
         * ------------------------------------------
         * 1. Use recursion to build strings only when valid.
         * 2. Add '(' if open < n.
         * 3. Add ')' if close < open.
         * 4. Stop when string length == 2 * n (means valid sequence complete).
         * 5. Backtrack after each recursive call to explore other combinations.
         * 6. Produces only valid combinations efficiently.
         */

        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    // ------------------- BRUTE FORCE -------------------
    private void bruteForce(List<String> res, char[] curr, int i, int n) {
        if (i == curr.length) {
            if (isValid(curr)) res.add(new String(curr));
            return;
        }

        curr[i] = '(';
        bruteForce(res, curr, i + 1, n);
        curr[i] = ')';
        bruteForce(res, curr, i + 1, n);
    }

    private boolean isValid(char[] s) {
        int bal = 0;
        for (char c : s) {
            if (c == '(') bal++;
            else bal--;
            if (bal < 0) return false;
        }
        return bal == 0;
    }

    // ------------------- OPTIMAL BACKTRACKING -------------------
    private void backtrack(List<String> res, StringBuilder curr, int open, int close, int n) {
        if (curr.length() == 2 * n) {
            res.add(curr.toString());
            return;
        }

        if (open < n) {
            curr.append('(');
            backtrack(res, curr, open + 1, close, n);
            curr.deleteCharAt(curr.length() - 1); // backtrack
        }

        if (close < open) {
            curr.append(')');
            backtrack(res, curr, open, close + 1, n);
            curr.deleteCharAt(curr.length() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n (number of pairs): ");
        int n = sc.nextInt();

        GenerateParenthesis obj = new GenerateParenthesis();

        List<String> combinations = obj.generateParenthesis(n);

        System.out.println("All valid parentheses combinations:");
        for (String s : combinations) {
            System.out.println(s);
        }

        sc.close();
    }
}
