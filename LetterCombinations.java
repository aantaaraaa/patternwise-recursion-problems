import java.util.*;

public class LetterCombinations {
    
    private Map<Character, String> map = new HashMap<>();

    public void backtrack(List<String> result, StringBuilder temp, String digits, int index){
        if(index == digits.length()){
            result.add(temp.toString());
            return;
        }

        char digit = digits.charAt(index);
        String letters = map.get(digit);

        for(int i = 0; i < letters.length(); i++){
            temp.append(letters.charAt(i));
            backtrack(result, temp, digits, index + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits){
        List<String> result = new ArrayList<>();
        if(digits == null || digits.length() == 0) return result;

        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String digits = sc.nextLine();
        LetterCombinations obj = new LetterCombinations();
        List<String> ans = obj.letterCombinations(digits);
        System.out.println(ans);
    }
}

/*
 * =============================================================
 * 📘 Problem Explanation (Letter Combinations of a Phone Number)
 * =============================================================
 *
 * 1️⃣ Problem Statement:
 * - Given a string containing digits from 2-9 inclusive.
 * - Return all possible letter combinations that the number could represent.
 * - Mapping is like on a phone keypad:
 *   2 → "abc", 3 → "def", 4 → "ghi", 5 → "jkl",
 *   6 → "mno", 7 → "pqrs", 8 → "tuv", 9 → "wxyz"
 *
 * Example:
 * Input: "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * -------------------------------------------------------------
 * 2️⃣ Why Backtracking?
 * - At each digit, we have multiple letter choices.
 * - We build combinations recursively:
 *   • Append a letter → move to next digit.
 *   • Backtrack (remove last letter) → try next letter.
 *
 * -------------------------------------------------------------
 * 3️⃣ Step-by-Step Approach:
 *
 * Function: backtrack(result, temp, digits, index)
 * - result → stores all valid combinations.
 * - temp → current combination being formed.
 * - digits → input string of digits.
 * - index → current digit index.
 *
 * Base Case:
 * - If index == digits.length() → ✅ combination complete, add to result.
 *
 * Recursive Case:
 * - For each letter mapped to digits[index]:
 *   • Append letter to temp
 *   • Recurse to index + 1
 *   • Remove last letter (backtrack)
 *
 * -------------------------------------------------------------
 * 4️⃣ Example Dry Run:
 *
 * Input: digits = "23"
 *
 * Start ""
 * - Pick 'a' → "a"
 *   - Pick 'd' → "ad" ✅ store
 *   - Pick 'e' → "ae" ✅ store
 *   - Pick 'f' → "af" ✅ store
 * - Pick 'b' → "b"
 *   - Pick 'd' → "bd" ✅ store
 *   ...
 *
 * ✅ Final Result = ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * -------------------------------------------------------------
 * 5️⃣ Complexity Analysis:
 *
 * Time Complexity:
 * - Each digit has up to 4 letters (7 and 9 have 4 letters).
 * - If n = digits.length(), total combinations = O(4^n)
 * - For each combination, building string takes O(n)
 * - Total ≈ O(4^n * n)
 *
 * Space Complexity:
 * - Recursion depth = O(n)
 * - Temporary string builder = O(n)
 * - Result storage = O(4^n * n)
 *
 * -------------------------------------------------------------
 * 6️⃣ Interview Script (How to Explain):
 *
 * "We use recursion + backtracking. At each digit, we iterate over all
 * letters mapped to it. We append the letter to a temporary StringBuilder,
 * recurse for the next digit, then remove the last letter to backtrack.
 * When we reach the end of digits, we have a complete combination to store."
 *
 * -------------------------------------------------------------
 * ✅ Java Implementation
 */
