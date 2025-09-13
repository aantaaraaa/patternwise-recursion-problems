import java.util.*;

public class CombinationSum2 {

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int target, int index, List<Integer> combination,
            List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i] == candidates[i - 1])
                continue;
            if (candidates[i] > target)
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
        List<List<Integer>> result = combinationSum2(candidates, target);
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
 * target value.
 * - Find all unique combinations where the numbers sum to target.
 * - Each number may be used **at most once**.
 * 
 * -------------------------------------------------------------
 * 2️⃣ Approach (Backtracking with Duplicate Skipping):
 * - Sort the array to ensure duplicates are adjacent.
 * - Use recursion (backtracking) to explore combinations:
 * • Include the current number → recurse with remaining target and index + 1.
 * • Skip duplicates at the same recursion depth: if (i > index && candidates[i]
 * == candidates[i-1]) continue.
 * • Stop recursion if candidates[i] > target (pruning).
 * - When target == 0 → store a copy of the current combination as a valid
 * result.
 * 
 * -------------------------------------------------------------
 * 3️⃣ Example Dry Run:
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Sorted: [1,1,2,5,6,7,10]
 * 
 * - Start []
 * - Pick 1 → [1], target=7
 * - Pick 1 → [1,1], target=6
 * - Pick 2 → [1,1,2], target=4
 * - Pick 5 → [1,1,5], target=1
 * - Pick 6 → [1,1,6], target=0 ✅ store
 * - Pick 2 → [1,2], target=5
 * - Pick 5 → [1,2,5], target=0 ✅ store
 * - Pick 5 → [1,5], target=2
 * - Pick 6 → [1,6], target=1
 * - Pick 7 → [1,7], target=0 ✅ store
 * - Skip first 1 (avoid duplicate)
 * - Pick 2 → [2], target=6
 * - Pick 6 → [2,6], target=0 ✅ store
 * - Pick 5 → [5], target=3
 * - Pick 6 → [6], target=2
 * - Pick 7 → [7], target=1
 * - Pick 10 → [10], target=-2
 * 
 * Final Result: [[1,1,6], [1,2,5], [1,7], [2,6]]
 * 
 * -------------------------------------------------------------
 * 4️⃣ Complexity Analysis:
 * 
 * Time Complexity:
 * - Sorting: O(n log n)
 * - Backtracking: O(2^n * k) in worst case
 * • n = number of candidates
 * • k = average length of combination
 * - Total: O(2^n * k)
 * 
 * Space Complexity:
 * - Recursion stack: O(n)
 * - Temporary combination list: O(n)
 * - Result storage: O(R * K)
 * - Total: O(n + R*K)
 * • R = number of valid combinations
 * • K = average size of a combination
 * 
 * -------------------------------------------------------------
 * 5️⃣ Difference from Combination Sum I:
 * - Combination Sum I allows unlimited reuse of the same element → recursion
 * with same index.
 * - Combination Sum II allows each element **once** → recursion with index + 1.
 * - Handles duplicates: skip same elements at same recursion depth.
 * 
 * =============================================================
 */
