import java.util.*;

public class PowerSet {

    public List<List<Integer>> subsets(int[] nums) {
        /**
         * Time Complexity: O(n * 2^n)
         * Space Complexity: O(1) (excluding output storage)
         * 
         * Brute Force (Iterative / Bitmask) Approach:
         * --------------------------------------------
         * 1. The total number of subsets (power set) for n elements is 2^n.
         * 2. Represent each subset as a binary number (mask) of length n.
         * 3. Each bit in mask (0 or 1) represents whether to include nums[j].
         * 4. Loop through all masks from 0 to (2^n - 1).
         * 5. For each mask, check which bits are set using (mask & (1 << j)) != 0.
         * 6. Add the corresponding elements to form each subset.
         * 7. Add all generated subsets to the result list.
         */

        /*
        int n = nums.length;
        int total = 1 << n; // 2^n
        List<List<Integer>> res = new ArrayList<>();

        for (int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            res.add(subset);
        }
        return res;
        */

        /**
         * Time Complexity: O(2^n)
         * Space Complexity: O(n) (recursion depth)
         * 
         * Optimal (Recursive / Backtracking) Approach:
         * ---------------------------------------------
         * 1. Use recursion to generate subsets by making decisions:
         *      - Include the current element.
         *      - Exclude the current element.
         * 2. Base Case: When index == nums.length, add the current subset to result.
         * 3. Add a copy of the subset (to avoid mutation) before backtracking.
         * 4. This directly explores all 2^n possible combinations.
         * 5. The recursion tree effectively represents all subset choices.
         */

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
