import java.util.*;

public class CombinationSum {

    /**
     * Combination Sum Problem (Backtracking)
     *
     * Time Complexity: O(N^(T/M))
     * Space Complexity: O(T/M)
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: candidates = [2, 3, 6, 7], target = 7
     *
     *                           (start=0, target=7)
     *                          /        |        \
     *                    +2   /     +3  |    +6  |   +7
     *                        v          v         v
     *                   (0,5)        (1,4)     (2,1)   (3,0)
     *                    /  \          |         
     *               +2  /    +3        +3       
     *                 v       v         v
     *             (0,3)     (1,2)     (1,1)
     *              /           \
     *          +2 /             +3
     *            v               v
     *         (0,1)           (1,-1)
     *          |
     *       +2 v
     *        (0,-1)
     *
     * Valid Paths:
     *  - [2,2,3]
     *  - [7]
     *
     * Explanation:
     * Each node represents a recursive state (index, remaining target).
     * Paths that reach target == 0 are valid combinations.
     */

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
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
            current.add(nums[i]);
            backtrack(nums, i, target - nums[i], current, result);
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

        CombinationSum obj = new CombinationSum();
        List<List<Integer>> result = obj.combinationSum(candidates, target);

        System.out.println("Possible combinations that sum to target:");
        System.out.println(result);

        sc.close();
    }
}
