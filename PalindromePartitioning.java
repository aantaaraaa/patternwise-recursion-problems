import java.util.*;

public class PalindromePartitioning {

    /**
     * Palindrome Partitioning (Backtracking)
     *
     * Time Complexity: O(n * 2^n)
     * Space Complexity: O(n)
     *
     * Problem:
     * Given a string s, partition it such that every substring in each partition
     * is a palindrome. Return all possible palindrome partitioning of s.
     *
     * Core Idea:
     * Explore all possible substrings starting at each index.
     * Whenever a palindrome substring is found, include it and recurse for the remaining part.
     * Backtrack to explore other partitions.
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: s = "aab"
     *
     * Start from index 0:
     *                     ""
     *                     |
     *                   "a"  → palindrome
     *                     |
     *                   backtrack("ab")
     *                    /     \
     *                 "a"      "ab"
     *                 |          ❌ not palindrome
     *               backtrack("b")
     *                   |
     *                 "b" ✅
     *
     * Valid Partitions:
     *  - ["a", "a", "b"]
     *  - ["aa", "b"]
     *
     * Tree Representation:
     * --------------------
     *                 ""
     *               /     \
     *             "a"     "aa"
     *              |        |
     *             "a"      "b"
     *              |        |
     *             "b"      ✅
     *              |
     *              ✅
     */

    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(String s, int start, List<String> curr, List<List<String>> res) {
        if (start == s.length()) {
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            String part = s.substring(start, i + 1);

            if (isPalindrome(part)) {
                curr.add(part);
                backtrack(s, i + 1, curr, res);
                curr.remove(curr.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left++) != str.charAt(right--))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String s = sc.nextLine();

        PalindromePartitioning obj = new PalindromePartitioning();
        List<List<String>> partitions = obj.partition(s);

        System.out.println("\nAll possible palindrome partitions:");
        for (List<String> partition : partitions) {
            System.out.println(partition);
        }

        sc.close();
    }
}
