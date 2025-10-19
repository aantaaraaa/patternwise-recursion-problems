import java.util.*;

public class SubsetSumII {

    /**
     * Subset Sum II — Unique Subsets (Backtracking)
     *
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     *
     * Problem:
     * Given an integer array that may contain duplicates, return all possible unique subsets.
     *
     * Core Idea:
     * 1. Sort the array to group duplicates together.
     * 2. Use recursion to explore inclusion/exclusion of elements.
     * 3. Skip duplicates at the same recursive depth:
     *      if (i > start && nums[i] == nums[i - 1]) → continue
     * 4. Add a copy of the current subset at every recursive step.
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: nums = [1, 2, 2]
     *
     *                        []
     *                      /     \
     *                   [1]       []
     *                  /   \        \
     *              [1,2]   [1]       [2]
     *               /        \         \
     *           [1,2,2]      [1,2]      [2,2]
     *
     * Output:
     * [[], [1], [1,2], [1,2,2], [2], [2,2]]
     *
     * Key Concept:
     * Sorting ensures that duplicates (like the second 2)
     * appear next to each other, allowing easy skipping.
     */

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int start, List<Integer> curr, List<List<Integer>> res) {
        res.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            curr.add(nums[i]);
            backtrack(nums, i + 1, curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.print("Enter elements (may contain duplicates): ");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        SubsetSumII obj = new SubsetSumII();
        List<List<Integer>> subsets = obj.subsetsWithDup(nums);

        System.out.println("\nAll unique subsets:");
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }

        sc.close();
    }
}
