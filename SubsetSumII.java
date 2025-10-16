import java.util.*;

public class SubsetSumII {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        /**
         * Time Complexity: O(2^n)
         * Space Complexity: O(n) (recursion depth)
         * 
         * Backtracking Approach:
         * -----------------------
         * 1. Sort the array to handle duplicates easily.
         * 2. Use recursion to explore all subsets.
         * 3. At each index, decide to include or skip the element.
         * 4. Skip duplicates: when nums[i] == nums[i-1] and i > start, continue.
         * 5. Add each subset to the result list (including empty subset).
         * 6. Backtrack to remove the last added element and explore others.
         */

        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] nums, int start, List<Integer> curr, List<List<Integer>> res) {
        res.add(new ArrayList<>(curr));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates

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
