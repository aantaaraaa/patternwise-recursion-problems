import java.util.*;

public class CombinationSum {
    public static void backtrack(int[] candidates, int target, int index, List<Integer> combination,
            List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }
        if (target < 0) return;

        for (int i = index; i < candidates.length; i++) {
            combination.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, combination, result);
            combination.remove(combination.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] candidates = new int[n];
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }
        int target = sc.nextInt();
        List<List<Integer>> result = combinationSum(candidates, target);
        System.out.println(result);
    }
}

/*
=============================================================
📘 Problem Explanation & Solution Outline (Combination Sum I)
=============================================================

1️⃣ Problem Statement:
- Given an array of distinct integers "candidates" and a target value.
- Find all unique combinations where the numbers sum to target.
- Each number may be used **unlimited times**.

-------------------------------------------------------------
2️⃣ Approach (Backtracking Pattern):
- Start with an empty combination.
- At each step, either:
  • Include the current number (keep the index same, since we can reuse it)
  • Skip to the next index.
- If target == 0 → valid combination, store a copy.
- If target < 0 → invalid path, backtrack.
- Backtracking ensures that after exploring one number, we remove it and try other options.

-------------------------------------------------------------
3️⃣ Example Dry Run:
Input: candidates = [2,3,5], target = 8

- Pick 2: [2], target=6
   - Pick 2: [2,2], target=4
       - Pick 2: [2,2,2], target=2
           - Pick 2: [2,2,2,2], target=0 ✅ store result
       - Pick 3: [2,2,3], target=1 (fail, backtrack)
       - Pick 5: [2,2,5], target=-1 (fail, backtrack)
   - Pick 3: [2,3], target=3
       - Pick 3: [2,3,3], target=0 ✅ store result
       - Pick 5: [2,3,5], target=-2 (fail, backtrack)
   - Pick 5: [2,5], target=1 (fail, backtrack)

- Pick 3: [3], target=5
   - Pick 3: [3,3], target=2
       - Pick 3: [3,3,3], target=-1 (fail)
       - Pick 5: [3,3,5], target=-3 (fail)
   - Pick 5: [3,5], target=0 ✅ store result

- Pick 5: [5], target=3
   - Pick 5: [5,5], target=-2 (fail)

Final Result: [[2,2,2,2], [2,3,3], [3,5]]

-------------------------------------------------------------
4️⃣ Complexity Analysis:

Time Complexity:
- Exponential in nature, since we explore all combinations.
- Rough bound: O(N^(target/minCandidate)), where N = number of candidates.
- Copying combinations adds O(R*K), where:
  • R = number of valid combinations
  • K = average length of a combination
- Total: O(N^(target/minCandidate) * K)

Space Complexity:
- Recursion depth: O(target/minCandidate)
- Temporary path list: O(target/minCandidate)
- Result storage: O(R*K)
- Total: O(target/minCandidate + R*K)

=============================================================
*/
