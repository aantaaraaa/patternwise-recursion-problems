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
📘 Problem Explanation & Solution Outline
=============================================================

1️⃣ Problem Statement:
We are given an array of distinct integers "candidates" and a target.
We need to find all unique combinations of numbers where the chosen numbers sum to target.
Each number may be used unlimited times.

-------------------------------------------------------------

2️⃣ Approach (Backtracking Pattern):
- Start with an empty combination.
- At each step, either include the current number (and keep the index same,
  since we can reuse it) OR skip to the next index.
- If target == 0 → valid combination, store it.
- If target < 0 → invalid path, backtrack.
- Backtracking means: after exploring with one number, remove it and try others.

-------------------------------------------------------------

3️⃣ Example Dry Run:
Input: candidates = [2,3,6,7], target = 7

- Pick 2: [2], target=5
   - Pick 2: [2,2], target=3
       - Pick 2: [2,2,2], target=1 (fail, backtrack)
       - Pick 3: [2,2,3], target=0 ✅ store result
   - Pick 3: [2,3], target=2 (fail, backtrack)
- Pick 3: [3], target=4
   - Pick 3: [3,3], target=1 (fail, backtrack)
- Pick 6: [6], target=1 (fail)
- Pick 7: [7], target=0 ✅ store result

Final Result: [[2,2,3], [7]]

-------------------------------------------------------------

4️⃣ Complexity Analysis:
Time Complexity:
- Exponential in nature, since we explore many combinations.
- Rough bound: O(N^(target/minCandidate))
- Copying combinations also adds O(R*K),
  where R = number of results, K = average length.

Space Complexity:
- Recursion depth: O(target/minCandidate)
- Path list storage: O(target/minCandidate)
- Result storage: O(R*K)
So, total space = O(target/minCandidate + R*K)

=============================================================
*/
