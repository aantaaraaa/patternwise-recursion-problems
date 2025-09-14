import java.util.*;

public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int index, List<Integer> combination,
            List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1])  // skip duplicates
                continue;
            if (candidates[i] > target)  // pruning
                break;
            combination.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, combination, result);
            combination.remove(combination.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        int target = sc.nextInt();

        CombinationSum2 solver = new CombinationSum2();  
        List<List<Integer>> result = solver.combinationSum2(candidates, target);
        System.out.println(result);

        sc.close();
    }
}

/*
 * =============================================================
 * 📘 Problem Explanation & Solution Outline (Combination Sum II)
 * =============================================================
 * 
 * 1️⃣ Problem Statement:
 * - Given an array of integers "candidates" (may contain duplicates) and a
 *   target value.
 * - Find all unique combinations where the numbers sum to target.
 * - Each number may be used **at most once**.
 * 
 * -------------------------------------------------------------
 * 2️⃣ Approach (Backtracking with Duplicate Skipping):
 * - First, sort the array → ensures duplicates are adjacent.
 * - Use recursion (backtracking) to explore all possible combinations.
 *   At each index:
 *   • Pick the current number → reduce target and move to index + 1 (since each number can be used only once).
 *   • Skip the current number → continue loop.
 * - To avoid duplicates:
 *   • If (i > index && candidates[i] == candidates[i - 1]) → skip this number.
 * - Pruning:
 *   • If candidates[i] > target, we can break early since further elements will also be too large.
 * - If target == 0 → we found a valid combination → add a copy to result.
 * 
 * -------------------------------------------------------------
 * 3️⃣ Example Dry Run:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Sorted: [1,1,2,5,6,7,10]
 * 
 * Start []
 * - Pick 1 → [1], target=7
 *   - Pick 1 → [1,1], target=6
 *       - Pick 2 → [1,1,2], target=4
 *       - Pick 6 → [1,1,6], target=0 ✅ store
 *   - Pick 2 → [1,2], target=5
 *       - Pick 5 → [1,2,5], target=0 ✅ store
 *   - Pick 5 → [1,5], target=2 ❌ stop
 *   - Pick 7 → [1,7], target=0 ✅ store
 * - Skip the second 1 (duplicate skip)
 * - Pick 2 → [2], target=6
 *   - Pick 6 → [2,6], target=0 ✅ store
 * - Pick 5 → [5], target=3 ❌ stop
 * - Pick 6 → [6], target=2 ❌ stop
 * - Pick 7 → [7], target=1 ❌ stop
 * - Pick 10 → [10], target=-2 ❌ stop
 * 
 * ✅ Final Result: [[1,1,6], [1,2,5], [1,7], [2,6]]
 * 
 * -------------------------------------------------------------
 * 4️⃣ Complexity Analysis:
 * Time Complexity:
 * - Sorting: O(n log n)
 * - Backtracking exploration: O(2^n * k) in worst case
 *   • n = number of candidates
 *   • k = average size of combination
 * - Total: O(2^n * k)
 * 
 * Space Complexity:
 * - Recursion depth: O(n)
 * - Temporary combination storage: O(n)
 * - Result storage: O(R * K)
 *   • R = number of valid combinations
 *   • K = average size of combination
 * 
 * -------------------------------------------------------------
 * 5️⃣ Difference from Combination Sum I:
 * - Combination Sum I:
 *   • Can reuse the same element multiple times → recursive call keeps the same index.
 * - Combination Sum II:
 *   • Each element can be used only once → recursive call goes to i+1.
 *   • Must skip duplicates at the same recursion depth.
 * 
 * -------------------------------------------------------------
 * 6️⃣ How I would explain to interviewer:
 * - "This is a variation of backtracking where duplicates exist. 
 *   To avoid duplicate results, I first sort the array and skip identical elements 
 *   at the same recursion depth. Unlike Combination Sum I, where we can reuse 
 *   numbers, here each number can only be used once, so I move the index forward. 
 *   Complexity is exponential, but pruning and duplicate skipping keep it efficient."
 * =============================================================
 */

