import java.util.*;

public class SubsequenceSumK {

    public boolean checkSubsequenceSum(int[] nums, int k) {
        /**
         * Time Complexity: O(2^n)
         * Space Complexity: O(n) (recursion stack)
         * 
         * Brute Force (Iterative) Approach:
         * ---------------------------------
         * 1. Generate all possible subsequences (2^n total).
         * 2. For each subset, calculate its sum.
         * 3. If any subset’s sum equals K → return true.
         * 
         * Example:
         * nums = [1, 2, 1], k = 3
         * Subsequences: [], [1], [2], [1,2], [1,1], [2,1], [1,2,1]
         * Only [1,2] or [2,1] give sum = 3 → return true
         */

        /*
        int n = nums.length;
        int total = 1 << n;
        for (int mask = 0; mask < total; mask++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0)
                    sum += nums[i];
            }
            if (sum == k) return true;
        }
        return false;
        */

        /**
         * Time Complexity: O(2^n)
         * Space Complexity: O(n) (recursion stack)
         * 
         * Optimal (Recursive / Backtracking) Approach:
         * --------------------------------------------
         * 1. At each index, we have two choices:
         *      → Include nums[i] in sum
         *      → Exclude nums[i] from sum
         * 2. Base case:
         *      - If current sum == K → return true.
         *      - If we reach the end (index == n) → return false.
         * 3. Recur both choices.
         * 4. Return true if any recursive path gives sum == K.
         * 
         * Example (nums = [1, 2, 1], k = 3):
         * -----------------------------------
         * helper(0, 0)
         * ├── include 1 → helper(1, 1)
         * │    ├── include 2 → helper(2, 3) ✅ sum == K → return true
         * │    └── exclude 2 → helper(2, 1)
         * └── exclude 1 → helper(1, 0)
         *      ├── include 2 → helper(2, 2)
         *      └── exclude 2 → helper(2, 0)
         *
         * Since we found a valid path early, recursion stops early.
         */

        return helper(nums, 0, 0, k);
    }

    private boolean helper(int[] nums, int index, int currentSum, int k) {
        if (currentSum == k) return true;

        if (index == nums.length) return false;

        if (helper(nums, index + 1, currentSum + nums[index], k))
            return true;

        if (helper(nums, index + 1, currentSum, k))
            return true;

        return false;
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

        System.out.print("Enter target sum K: ");
        int k = sc.nextInt();

        SubsequenceSumK obj = new SubsequenceSumK();
        boolean exists = obj.checkSubsequenceSum(nums, k);

        if (exists)
            System.out.println("✅ A subsequence with sum " + k + " exists.");
        else
            System.out.println("❌ No subsequence with sum " + k + " found.");

        sc.close();
    }
}
