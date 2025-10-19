import java.util.*;

public class PowerSet {

    /**
     * Power Set / Subsets (Backtracking)
     *
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)
     *
     * Problem:
     * Generate all possible subsets (the power set) of a given integer array.
     * Each element can either be included or excluded in a subset.
     *
     * Core Idea:
     * Use recursion to explore both choices at every index:
     * 1. Include nums[index]
     * 2. Exclude nums[index]
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: nums = [1, 2]
     *
     *                        []
     *                       /  \
     *                    [1]    []         ← include 1 / exclude 1
     *                   /  \    /  \
     *              [1,2]  [1] [2]  []      ← include 2 / exclude 2
     *
     * Output:
     * [[], [2], [1], [1,2]]
     *
     * Explanation:
     * Each recursion branch represents a decision for one element.
     * Once all elements are processed, add the current subset to the result list.
     */

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int index, List<Integer> curr, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(curr));
            return;
        }

        curr.add(nums[index]);
        backtrack(nums, index + 1, curr, res);
        curr.remove(curr.size() - 1);
        backtrack(nums, index + 1, curr, res);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] nums = new int[n];
        System.out.print("Enter elements: ");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        PowerSet obj = new PowerSet();
        List<List<Integer>> subsets = obj.subsets(nums);

        System.out.println("\nAll possible subsets (Power Set):");
        for (List<Integer> subset : subsets) {
            System.out.println(subset);
        }

        sc.close();
    }
}
