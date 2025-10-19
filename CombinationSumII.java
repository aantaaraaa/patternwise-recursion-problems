import java.util.*;

public class CombinationSumII {

    /**
     * Combination Sum II (Backtracking with Duplicate Handling)
     *
     * Time Complexity: O(2^N)
     * Space Complexity: O(N)
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
     * After Sorting: [1, 1, 2, 5, 6, 7, 10]
     *
     *                                   (start=0, target=8)
     *                                 /       |        |        \
     *                            +1  v    +1  v     +2  v     +5  v  ...
     *                          (1,7)    (2,7)    (3,6)    (4,3)
     *                           /          |         \      
     *                      +1  v        +2  v       +6  v
     *                     (2,6)       (3,5)       (5,0) ✅ [1,2,5]
     *                      /              \
     *                 +2  v               +5  v
     *               (3,4)               (4,0) ✅ [1,7]
     *
     * Valid Paths:
     *  - [1, 1, 6]
     *  - [1, 2, 5]
     *  - [1, 7]
     *  - [2, 6]
     *
     * Key Idea:
     * Each recursive layer represents selecting the next unique element (no repeats at the same depth).
     * Duplicates are skipped using: if (i > start && nums[i] == nums[i - 1]) continue;
     */

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            if (nums[i] > target) break;

            current.add(nums[i]);
            backtrack(nums, i + 1, target - nums[i], current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of candidates: ");
        int n = sc.nextInt();

        int[] candidates = new int[n];
        System.out.println("Enter candidate numbers:");
        for (int i = 0; i < n; i++) {
            candidates[i] = sc.nextInt();
        }

        System.out.print("Enter target value: ");
        int target = sc.nextInt();

        CombinationSumII obj = new CombinationSumII();
        List<List<Integer>> result = obj.combinationSum2(candidates, target);

        System.out.println("Unique combinations that sum to target:");
        System.out.println(result);

        sc.close();
    }
}
