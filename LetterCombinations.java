import java.util.*;

public class LetterCombinations {

    /**
     * Letter Combinations of a Phone Number (Backtracking)
     *
     * Time Complexity: O(4^N)
     * Space Complexity: O(N)
     *
     * Problem:
     * Given a string of digits (2–9), return all possible letter combinations
     * that the number could represent on a traditional phone keypad.
     *
     * Mapping:
     * 2 → "abc", 3 → "def", 4 → "ghi", 5 → "jkl", 6 → "mno",
     * 7 → "pqrs", 8 → "tuv", 9 → "wxyz"
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: digits = "23"
     *
     *                     ""
     *                    / | \
     *                  a   b   c        ← letters for '2'
     *                 /|\ /|\ /|\
     *                d e f d e f d e f  ← letters for '3'
     *
     * Output:
     * ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
     *
     * Key Idea:
     * 1. Each recursion level corresponds to one digit.
     * 2. At each level, explore all letters mapped to that digit.
     * 3. When the index reaches digits.length(), add the combination to the result.
     */

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;

        String[] map = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        backtrack(digits, 0, new StringBuilder(), result, map);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result, String[] map) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        String letters = map[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(digits, index + 1, current, result, map);
            current.deleteCharAt(current.length() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter digits (2-9): ");
        String digits = sc.nextLine();

        LetterCombinations obj = new LetterCombinations();
        List<String> result = obj.letterCombinations(digits);

        System.out.println("Possible letter combinations:");
        System.out.println(result);

        sc.close();
    }
}
