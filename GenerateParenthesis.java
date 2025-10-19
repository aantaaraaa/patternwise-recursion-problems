import java.util.*;

public class GenerateParenthesis {

    public void generateParenthesis(int n) {
        /**
         * Time Complexity: O(2^(2n))
         * Space Complexity: O(2n)
         * 
         * Brute Force (Recursive) Approach:
         * ---------------------------------
         * 1. Generate all possible strings made of '(' and ')' of length 2n.
         * 2. For each generated string, check if it is a valid parentheses sequence.
         * 3. Add valid ones to the result or print them.
         * 
         * Example (n = 2):
         * All generated combinations: ((), ()), )(), ))(, etc.
         * Valid only: (()), ()()
         * 
         * This is inefficient as it explores invalid sequences too.
         */

        /*
        List<String> result = new ArrayList<>();
        char[] curr = new char[2 * n];
        bruteForce(result, curr, 0);
        for (String s : result) {
            System.out.println(s);
        }
        */

        /**
         * Time Complexity: O(4^n / √n)
         * Space Complexity: O(2n) (recursion stack + string builder)
         * 
         * Optimal (Recursive / Backtracking) Approach:
         * --------------------------------------------
         * 1. Start with an empty string "".
         * 2. You can add '(' if open < n.
         * 3. You can add ')' if close < open.
         * 4. When the string length == 2n, print it (base case).
         * 
         * Example (n = 3):
         * backtrack("", open=0, close=0)
         * ├── "(" → open=1, close=0
         * │    ├── "((" → open=2, close=0
         * │    │    ├── "(((" → open=3, close=0
         * │    │    │    ├── "((()" → open=3, close=1
         * │    │    │    │    ├── "((())" → open=3, close=2
         * │    │    │    │    │    ├── "((()))" ✅ print
         * │    │    │    │    └── backtrack done
         * │    │    ├── "(()" → open=2, close=1
         * │    │    │    ├── "(())" → open=2, close=2
         * │    │    │    │    ├── "(())(" → open=3, close=2
         * │    │    │    │    │    ├── "(())()" ✅ print
         * │    │    │    │    └── backtrack done
         * │    ├── "()(" → open=2, close=1
         * │    │    ├── "()((" → open=3, close=1
         * │    │    │    ├── "()(()" → open=3, close=2
         * │    │    │    │    ├── "()(())" ✅ print
         * │    │    └── "()()" → open=2, close=2
         * │    │         ├── "()()(" → open=3, close=2
         * │    │         │    ├── "()()()" ✅ print
         * │    │         └── backtrack done
         */

        backtrack("", 0, 0, n);
    }

    // ------------------- BRUTE FORCE -------------------
    /*
    private void bruteForce(List<String> res, char[] curr, int i) {
        if (i == curr.length) {
            if (isValid(curr)) res.add(new String(curr));
            return;
        }

        curr[i] = '(';
        bruteForce(res, curr, i + 1);
        curr[i] = ')';
        bruteForce(res, curr, i + 1);
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
    */

    // ------------------- OPTIMAL BACKTRACKING -------------------
    private void backtrack(String current, int open, int close, int n) {
        if (current.length() == 2 * n) {
            System.out.println(current);
            return;
        }

        if (open < n)
            backtrack(current + "(", open + 1, close, n);

        if (close < open)
            backtrack(current + ")", open, close + 1, n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of pairs (n): ");
        int n = sc.nextInt();

        GenerateParenthesis obj = new GenerateParenthesis();
        System.out.println("\nAll valid parentheses combinations for n = " + n + ":");
        obj.generateParenthesis(n);

        sc.close();
    }
}
