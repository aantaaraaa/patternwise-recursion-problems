import java.util.*;

public class SubsetSum2 {
    
    public void backtrack(List<List<Integer>> result, List<Integer> temp, int[] arr, int target, int index){
        if(target == 0){
            result.add(new ArrayList<>(temp));
            return;
        }

        if(target < 0 || index == arr.length){
            return;
        }

        for(int i = index; i < arr.length; i++){
            if(i > index && arr[i] == arr[i-1]) continue;
            if(arr[i] > target) break;

            temp.add(arr[i]);
            backtrack(result, temp, arr, target-arr[i], i+1);
            temp.remove(temp.size()-1);
        }
    }

    public List<List<Integer>> subsetSum2(int[] arr, int target){
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        backtrack(result, new ArrayList<>(), arr, target, 0);
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); 
        int[] arr = new int[n];
        for(int i=0; i<n; i++) arr[i] = sc.nextInt();
        int target = sc.nextInt();
        SubsetSum2 obj = new SubsetSum2();
        List<List<Integer>> ans = obj.subsetSum2(arr, target);
        System.out.println(ans);
    }
}

/*
 * =============================================================
 * 📘 Problem Explanation (Subset Sum II)
 * =============================================================
 *
 * 1️⃣ Problem Statement:
 * - Given an array of integers (arr) and a target sum (S).
 * - Find all **unique subsets** of arr whose sum equals S.
 * - Each element may be used **at most once**.
 * - Array may contain duplicates; result must not contain duplicate subsets.
 *
 * Example:
 * Input: arr = [1,2,2,3], S = 4
 * Output: [[1,3], [2,2]]
 *
 * -------------------------------------------------------------
 * 2️⃣ Why Backtracking?
 * - Subset problems naturally map to recursion:
 *   • At each index, we decide → Include or Exclude element.
 * - We **skip duplicates** to avoid repeated subsets.
 * - Early pruning reduces unnecessary recursion (if arr[i] > target).
 *
 * -------------------------------------------------------------
 * 3️⃣ Step-by-Step Approach:
 *
 * Function: backtrack(result, temp, arr, target, index)
 * - result → stores all valid subsets.
 * - temp → current subset being formed.
 * - arr → input array (sorted for duplicate handling).
 * - target → remaining sum.
 * - index → current index in arr.
 *
 * Base Cases:
 * - If target == 0 → ✅ valid subset, add to result.
 * - If target < 0 OR index == arr.length → ❌ invalid, stop.
 *
 * Recursive Case:
 * - Loop from index → arr.length:
 *   • Skip duplicate numbers.
 *   • If arr[i] > target → break (prune).
 *   • Include arr[i] → recurse with target-arr[i], i+1.
 *
 * -------------------------------------------------------------
 * 4️⃣ Example Dry Run:
 *
 * Input: arr = [1,2,2,3], target = 4
 *
 * Start []
 * - Pick 1 → [1], target=3
 *   - Pick 2 → [1,2], target=1 ❌
 *   - Pick 2 (duplicate) skipped
 *   - Pick 3 → [1,3], target=0 ✅ store
 * - Skip 1 → []
 *   - Pick 2 → [2], target=2
 *     - Pick 2 → [2,2], target=0 ✅ store
 *     - Pick 3 → [2,3], target=-1 ❌
 * - Skip 2 → []
 *   - Pick 2 (duplicate) skipped
 * - Pick 3 → [3], target=1 ❌
 *
 * ✅ Final Result = [[1,3], [2,2]]
 *
 * -------------------------------------------------------------
 * 5️⃣ Complexity Analysis:
 *
 * Time Complexity:
 * - Worst case: explore all subsets → O(2^n)
 * - Sorting = O(n log n)
 * - Skipping duplicates avoids repeated work
 * - Total ≈ O(2^n * k), k = subset size
 *
 * Space Complexity:
 * - Recursion depth = O(n)
 * - Temporary list = O(n)
 * - Result storage = O(R*K), R = number of subsets, K = avg size
 *
 * -------------------------------------------------------------
 * 6️⃣ Difference From Subset Sum I:
 * - Subset Sum I: array has distinct elements → no duplicate handling needed.
 * - Subset Sum II: array may have duplicates → must skip duplicates to ensure unique subsets.
 *
 * -------------------------------------------------------------
 * 7️⃣ Interview Script (How to Explain):
 *
 * "This is Subset Sum II: find all unique subsets that sum to target.
 * We sort the array first to handle duplicates. Then we use recursion + backtracking.
 * At each step, we loop over remaining elements, skipping duplicates.
 * We prune recursion if the current element exceeds the remaining target.
 * When the target hits zero, we store the subset.
 * This ensures we explore all unique valid subsets efficiently."
 *
 * -------------------------------------------------------------
 * ✅ Java Implementation
 */
