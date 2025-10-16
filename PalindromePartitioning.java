import java.util.*;

public class PalindromePartitioning {

    public List<List<String>> partition(String s) {
        /**
         * Time Complexity: O(n * 2^n)
         * Space Complexity: O(n) (recursion depth)
         * 
         * Backtracking Approach:
         * -----------------------
         * 1. Explore all possible partitions of the string.
         * 2. At each step, choose a substring starting at 'start' index.
         * 3. If the substring is a palindrome, include it and recurse on the remaining string.
         * 4. When 'start' reaches the end of the string, add the current list to the result.
         * 5. Backtrack by removing the last added substring and continue exploring.
         */

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
            if (str.charAt(left++) != str.charAt(right--)) {
                return false;
            }
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
