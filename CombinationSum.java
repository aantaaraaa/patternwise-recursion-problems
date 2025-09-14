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
We are given:
- An array of distinct integers "candidates".
- A target integer.

We need to return all unique combinations of numbers from "candidates" such that:
- The numbers sum to the target.
- Each number can be used **unlimited times**.

⚡ Key Point:
- "Unlimited times" means if I pick a number, I can reuse it again in the same path.

-------------------------------------------------------------
2️⃣ Approach (How I would explain in interview):
I’ll use **backtracking** because:
- I need to explore all possible subsets of numbers.
- For each candidate, I have two choices:
   • Pick it (and stay at same index, since it can be reused).
   • Skip it (move to the next index).

Steps:
1. Start with an empty combination.
2. If target == 0 → we found a valid combination → add to result.
3. If target < 0 → invalid → stop recursion.
4. Otherwise:
   - Add the current number.
   - Recurse with reduced target.
   - Backtrack (remove last number).
5. Explore next candidate.

This ensures we explore all possible ways.

-------------------------------------------------------------
3️⃣ Example Dry Run:
Input: candidates = [2,3,5], target = 8

- Start []
   - Pick 2 → [2], target=6
       - Pick 2 → [2,2], target=4
           - Pick 2 → [2,2,2], target=2
               - Pick 2 → [2,2,2,2], target=0 ✅ store
               - Pick 3 → [2,2,2,3], target=-1 ❌ stop
               - Pick 5 → [2,2,2,5], target=-3 ❌ stop
           - Pick 3 → [2,2,3], target=1 ❌ stop
           - Pick 5 → [2,2,5], target=-1 ❌ stop
       - Pick 3 → [2,3], target=3
           - Pick 3 → [2,3,3], target=0 ✅ store
           - Pick 5 → [2,3,5], target=-2 ❌ stop
       - Pick 5 → [2,5], target=1 ❌ stop
   - Pick 3 → [3], target=5
       - Pick 3 → [3,3], target=2
           - Pick 3 → [3,3,3], target=-1 ❌ stop
           - Pick 5 → [3,3,5], target=-3 ❌ stop
       - Pick 5 → [3,5], target=0 ✅ store
   - Pick 5 → [5], target=3
       - Pick 5 → [5,5], target=-2 ❌ stop

✅ Final Result: [[2,2,2,2], [2,3,3], [3,5]]

-------------------------------------------------------------
4️⃣ Complexity Analysis:

Time Complexity:
- Worst case: we explore many recursive paths.
- Approx upper bound: O(N^(target/minCandidate)), where:
   • N = number of candidates
   • target/minCandidate = max recursion depth
- Copying combinations into result costs O(R*K),
   • R = number of valid results
   • K = average length of each result

So total: O(N^(target/minCandidate) + R*K)

Space Complexity:
- Recursion depth: O(target/minCandidate)
- Temporary path list: O(target/minCandidate)
- Result storage: O(R*K)

-------------------------------------------------------------
5️⃣ How I would summarize to interviewer:
"This problem is a classic backtracking problem.
We recursively try candidates, allow reuse by not incrementing index,
and backtrack after each exploration.
The complexity is exponential but acceptable because the input size is small."
=============================================================
*/
