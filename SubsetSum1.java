import java.util.*;

public class SubsetSum1 {
    
    public void backtrack(List<List<Integer>> result, List<Integer> temp, int[] arr, int target, int index){

        if(target == 0){
            result.add(new ArrayList<>(temp));
            return;
        }

        if(target < 0 || index == arr.length){
            return;
        }

        temp.add(arr[index]);
        backtrack(result, temp, arr, target-arr[index], index+1);
        temp.remove(temp.size()-1);

        backtrack(result, temp, arr, target, index+1);
    }

    public List<List<Integer>> subsetSum(int[] arr, int target){
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), arr, target, 0);
        return result;
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); 
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt(); 
        int target = sc.nextInt(); 
        SubsetSum1 obj = new SubsetSum1();
        List<List<Integer>> ans = obj.subsetSum(arr, target);
        System.out.println(ans); 
    }
}

/*
 * =============================================================
 * 📘 Problem Explanation (Subset Sum I)
 * =============================================================
 *
 * 1️⃣ Problem Statement:
 * - Given an array of integers (arr) and a target sum (S).
 * - Find all subsets of arr whose sum equals S.
 * - Each element may be used at most once.
 * - Subsets must not contain duplicates (if input has duplicates,
 *   we handle them carefully).
 *
 * Example:
 * Input: arr = [1,2,3], S = 3
 * Output: [[1,2], [3]]
 *
 * -------------------------------------------------------------
 * 2️⃣ Why Backtracking?
 * - Subset problems naturally map to recursion:
 *   • At each index, we decide → Include or Exclude element.
 * - This explores the entire **power set (2^n subsets)**.
 * - While exploring, we track running sum and prune when it exceeds target.
 *
 * -------------------------------------------------------------
 * 3️⃣ Step-by-Step Approach:
 *
 * Function: backtrack(result, temp, arr, target, index)
 * - result → stores all valid subsets.
 * - temp → current subset being formed.
 * - arr → input array.
 * - target → remaining sum.
 * - index → current index in arr.
 *
 * Base Cases:
 * - If target == 0 → ✅ valid subset, add to result.
 * - If target < 0 OR index == arr.length → ❌ invalid, stop.
 *
 * Recursive Case:
 * - Option 1: Include arr[index] → recurse with target - arr[index].
 * - Option 2: Exclude arr[index] → recurse with same target.
 *
 * -------------------------------------------------------------
 * 4️⃣ Example Dry Run:
 *
 * Input: arr = [1,2,3], target = 3
 *
 * Start []
 * - Pick 1 → [1], target=2
 *   - Pick 2 → [1,2], target=0 ✅ store
 *   - Skip 2 → [1], target=2 → Pick 3 → [1,3], target=-1 ❌
 * - Skip 1 → []
 *   - Pick 2 → [2], target=1
 *     - Pick 3 → [2,3], target=-2 ❌
 *   - Skip 2 → []
 *     - Pick 3 → [3], target=0 ✅ store
 *
 * ✅ Final Result = [[1,2], [3]]
 *
 * -------------------------------------------------------------
 * 5️⃣ Complexity Analysis:
 *
 * Time Complexity:
 * - Each element can be either included or excluded → O(2^n).
 * - For each valid subset, copying into result takes O(k) where k = subset size.
 * - Total = O(2^n * k).
 *
 * Space Complexity:
 * - Recursion depth = O(n).
 * - Temporary subset list = O(n).
 * - Result storage = O(R * K), where R = number of valid subsets, K = average subset size.
 *
 * -------------------------------------------------------------
 * 6️⃣ Difference From Combination Sum:
 * - Combination Sum:
 *   • We must form subsets that sum to a target with optional reuse (variant dependent).
 * - Subset Sum:
 *   • Only checks if subset sum matches target.
 *   • Core = exploring the power set.
 *
 * -------------------------------------------------------------
 * 7️⃣ Interview Script (How to Explain):
 *
 * "This problem is solved using recursion + backtracking.
 * At each index, we have two choices: include the current element
 * or skip it. We keep track of the current sum. 
 * If at any point our sum exceeds the target, we prune.
 * If we exactly reach sum == target, we record that subset.
 * This way, we explore all possible subsets in O(2^n)."
 *
 * -------------------------------------------------------------
 * ✅ Java Implementation
 */
