import java.util.*;

public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        /**
         * Time Complexity: O(4^N)
         * Space Complexity: O(N)
         * 
         * Brute Force (Iterative) Approach:
         * ---------------------------------
         * 1. Use a queue to store partial combinations.
         * 2. For each digit, expand all existing combinations by adding its corresponding letters.
         * 3. Continue until all digits are processed.
         * 4. Return the list of completed combinations.
         */

        /*
        if (digits == null || digits.length() == 0) return new ArrayList<>();
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        Queue<String> queue = new LinkedList<>();
        queue.add("");
        for (char d : digits.toCharArray()) {
            int size = queue.size();
            String letters = map[d - '0'];
            for (int i = 0; i < size; i++) {
                String prefix = queue.poll();
                for (char c : letters.toCharArray()) {
                    queue.add(prefix + c);
                }
            }
        }
        return new ArrayList<>(queue);
        */

        /**
         * Time Complexity: O(4^N)
         * Space Complexity: O(N)
         * 
         * Optimal (Recursive Backtracking) Approach:
         * ------------------------------------------
         * 1. If the input is empty, return an empty list.
         * 2. For each digit, retrieve its corresponding letters from a predefined map.
         * 3. Build combinations by appending one letter at a time recursively.
         * 4. Stop when all digits are processed and store the complete combination.
         * 5. Return all generated combinations.
         */

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
