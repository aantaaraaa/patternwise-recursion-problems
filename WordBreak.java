import java.util.*;

public class WordBreak {

    /**
     * Word Break Problem (Recursion + Memoization)
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(n)
     *
     * Problem:
     * Given a string s and a dictionary of words, determine if s can be segmented
     * into a space-separated sequence of one or more dictionary words.
     *
     * Approach:
     * Use recursion with memoization.
     * Try to partition the string into valid prefixes (present in the dictionary),
     * and recursively check the remaining suffix.
     * Use memo[start] to cache results for subproblems.
     *
     * Recursion Tree Example:
     * ------------------------
     * Input:
     * s = "leetcode"
     * wordDict = ["leet", "code"]
     *
     * helper("leetcode", 0)
     * ├── prefix = "l" (not in dict)
     * ├── prefix = "le" (not in dict)
     * ├── prefix = "lee" (not in dict)
     * ├── prefix = "leet" ✅
     * │      └── helper("code", 4)
     * │              ├── prefix = "c" (not in dict)
     * │              ├── prefix = "co" (not in dict)
     * │              ├── prefix = "cod" (not in dict)
     * │              ├── prefix = "code" ✅
     * │                     └── helper("", 8) ✅ base case
     * │
     * ✅ Return true (successful segmentation)
     *
     * Result: true
     */

    public boolean wordBreak(String s, List<String> wordDict) {
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

        System.out.println("\nCan the string be segmented? " + result);

        sc.close();
    }
}
