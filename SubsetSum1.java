import java.util.*;

public class SubsetSum1 {

    public List<Integer> subsetSums(int[] nums) {
        /**
         * Time Complexity: O(2^n)
         * Space Complexity: O(2^n) (for storing all subset sums)
         * 
         * Brute Force (Iterative / Bitmask) Approach:
         * -------------------------------------------
         * 1. Each subset can be represented as a binary mask of length n.
         * 2. Loop through all masks from 0 to (2^n - 1).
         * 3. For each mask, sum all nums[i] where the i-th bit is set.
         * 4. Store each subset sum in a list.
         * 
         * Example:
         * nums = [1, 2, 3]
         * Masks (0 to 7):
         * 000 → sum = 0
         * 001 → sum = 3
         * 010 → sum = 2
         * 011 → sum = 5
         * 100 → sum = 1
         * 101 → sum = 4
         * 110 → sum = 3
         * 111 → sum = 6
         * Output = [0, 3, 2, 5, 1, 4, 3, 6]
         */

        /*
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        int total = 1 << n; // 2^n

        for (int mask = 0; mask < total; mask++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((mask & (1 << j)) != 0)
                    sum += nums[j];
            }
            result.add(sum);
        }

        return result;
        */

        /**
         * Time Complexity: O(2^n)
         * Space Complexity: O(2^n)
         * 
         * Optimal (Recursive / Backtracking) Approach:
         * ---------------------------------------------
         * 1. At each index, we can either include or exclude nums[i].
         * 2. Base case: when index == n, store the current sum.
         * 3. This explores all 2^n subset combinations.
         * 
         * Example (nums = [1, 2]):
         * helper(0, 0)
         * ├── include 1 → helper(1, 1)
         * │    ├── include 2 → helper(2, 3)
         * │    └── exclude 2 → helper(2, 1)
         * └── exclude 1 → helper(1, 0)
         *      ├── include 2 → helper(2, 2)
         *      └── exclude 2 → helper(2, 0)
         * Output = [3, 1, 2, 0]
         */

        List<Integer> result = new ArrayList<>();
        helper(nums, 0, 0, result);
        Collections.sort(result); 
        return result;
    }

    private void helper(int[] nums, int index, int currentSum, List<Integer> result) {
        if (index == nums.length) {
            result.add(currentSum);
            return;
        }

        helper(nums, index + 1, currentSum + nums[index], result);
        helper(nums, index + 1, currentSum, result);
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

        SubsetSum1 obj = new SubsetSum1();
        List<Integer> sums = obj.subsetSums(nums);

        System.out.println("\nAll possible subset sums:");
        System.out.println(sums);

        sc.close();
    }
}
