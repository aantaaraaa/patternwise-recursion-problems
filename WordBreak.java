import java.util.*;

public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        /**
         * Time Complexity: Exponential (O(2^n)) in the worst case.
         * Space Complexity: O(n) due to recursion stack.
         * 
         * Brute Force (Recursive Backtracking) Approach:
         * ---------------------------------------------
         * 1. Start from index 0 of the string.
         * 2. At each step, try to partition the string into a prefix that exists in the dictionary.
         * 3. Recursively check the remaining substring.
         * 4. If any path leads to the end of the string successfully, return true.
         * 5. Otherwise, return false.
         */

        /*
        return backtrack(s, 0, new HashSet<>(wordDict));
        */

        /**
         * Time Complexity: O(n^2)
         * Space Complexity: O(n) (recursion + memoization)
         * 
         * Optimized (Recursion + Memoization) Approach:
         * ---------------------------------------------
         * 1. Use a Set for O(1) dictionary lookups.
         * 2. Use a boolean[] memo to remember failed start indices.
         * 3. Try to match every prefix starting at index `start`.
         * 4. If a prefix matches, recursively check the suffix.
         * 5. If recursion returns true for any path, mark success.
         * 6. Otherwise, memoize and return false.
         */

        Set<String> dict = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return helper(s, 0, dict, memo);
    }

    private boolean helper(String s, int start, Set<String> dict, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }

        if (memo[start] != null) {
            return memo[start];
        }

        for (int end = start + 1; end <= s.length(); end++) {
            String prefix = s.substring(start, end);
            if (dict.contains(prefix) && helper(s, end, dict, memo)) {
                memo[start] = true;
                return true;
            }
        }

        memo[start] = false;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String s = sc.nextLine();

        System.out.print("Enter dictionary words (space-separated): ");
        String[] words = sc.nextLine().split(" ");
        List<String> wordDict = Arrays.asList(words);

        WordBreak obj = new WordBreak();

        boolean result = obj.wordBreak(s, wordDict);

        System.out.println("Can the string be segmented? " + result);

        sc.close();
    }
}
