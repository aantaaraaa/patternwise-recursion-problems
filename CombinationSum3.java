import java.util.*;

public class CombinationSum3 {
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> temp, int k, int remain, int start) {
        if (remain == 0 && temp.size() == k) {
            result.add(new ArrayList<>(temp));
            return;
        }
        if (remain < 0 || temp.size() > k) return;

        for (int i = start; i <= 9; i++) {
            temp.add(i);
            backtrack(result, temp, k, remain - i, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt(); 
        int n = sc.nextInt();  

        CombinationSum3 solver = new CombinationSum3();  
        List<List<Integer>> ans = solver.combinationSum3(k, n);
        System.out.println(ans); 
    }
}

/*
 * =============================================================
 * 📘 Problem Explanation (Combination Sum III - Leetcode 216)
 * =============================================================
 *
 * 1️⃣ Problem Statement:
 * - We need to find all **unique combinations** of k numbers
 *   that add up to n.
 * - Only numbers 1 through 9 can be used.
 * - Each number may be used **at most once**.
 * - Output should not contain duplicate combinations.
 *
 * Example:
 * Input: k = 3, n = 7
 * Output: [[1,2,4]]
 *
 * -------------------------------------------------------------
 * 2️⃣ Why Backtracking?
 * - We are generating all possible subsets of {1..9}.
 * - At each step:
 *   • Choose a number → recurse.
 *   • Skip a number → move ahead.
 * - Backtracking ensures:
 *   • We explore all possibilities.
 *   • We discard invalid paths early (pruning).
 *
 * -------------------------------------------------------------
 * 3️⃣ Step-by-Step Approach:
 *
 * Function: backtrack(result, temp, k, remain, start)
 * - result → stores all valid combinations.
 * - temp → current partial combination.
 * - k → required length of combination.
 * - remain → remaining target sum.
 * - start → next number to consider (ensures no duplicates).
 *
 * Base Cases:
 * - If remain == 0 AND temp.size() == k → ✅ valid combination, add to result.
 * - If remain < 0 OR temp.size() > k → ❌ invalid path, stop.
 *
 * Recursive Case:
 * - Loop i from start to 9:
 *   • Add i to current list.
 *   • Recurse with remain - i, start = i+1.
 *   • Remove i (backtrack).
 *
 * -------------------------------------------------------------
 * 4️⃣ Example Dry Run:
 *
 * Input: k = 3, n = 7
 *
 * Start: []
 * - Pick 1 → [1], remain=6
 *   - Pick 2 → [1,2], remain=4
 *     - Pick 3 → [1,2,3], remain=1 ❌ continue
 *     - Pick 4 → [1,2,4], remain=0 ✅ store [1,2,4]
 *   - Pick 3 → [1,3], remain=3
 *     - Pick 4 → [1,3,4], remain=-1 ❌ stop
 * - Pick 2 → [2], remain=5
 *   - Pick 3 → [2,3], remain=2
 *     - Pick 4 → [2,3,4], remain=-2 ❌
 * - Pick 3 → [3], remain=4
 *   - Pick 4 → [3,4], remain=0 but size=2 ❌ need 3 numbers
 *
 * ✅ Final Result = [[1,2,4]]
 *
 * -------------------------------------------------------------
 * 5️⃣ Complexity Analysis:
 *
 * Time Complexity:
 * - At most 9 numbers to choose from.
 * - We are generating combinations of length k.
 * - Upper Bound: O(C(9, k)) = 9! / (k! * (9-k)!)
 *   (In worst case behaves like O(9^k)).
 *
 * Space Complexity:
 * - Recursion stack depth = O(k).
 * - Temporary list = O(k).
 * - Final answer = O(R * k), where R is number of valid results.
 *
 * -------------------------------------------------------------
 * 6️⃣ Difference From Other Variants:
 * - Combination Sum I:
 *   • Numbers can be reused multiple times.
 *   • No restriction on length of combination.
 * - Combination Sum II:
 *   • Input array can contain duplicates, must avoid duplicate results.
 *   • Each number can be used once.
 * - Combination Sum III:
 *   • Numbers fixed from 1..9.
 *   • Must use exactly k numbers.
 *   • No reuse.
 *
 * -------------------------------------------------------------
 * 7️⃣ Interview Script (How to Explain):
 *
 * "We are solving this using backtracking.
 * At each step, we decide whether to include a number from 1–9.
 * We maintain the current combination, remaining target, and 
 * remaining count of numbers.
 * If at any point our sum goes below zero or we exceed k numbers,
 * we prune the recursion.
 * If we exactly hit sum = 0 and used exactly k numbers,
 * we add that combination to our result.
 * This ensures all possible unique combinations are explored
 * without duplicates."
 *
 * -------------------------------------------------------------
 * ✅ Java Implementation
 */