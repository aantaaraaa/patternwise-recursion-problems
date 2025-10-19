import java.util.*;

public class CountSubsequencesWithSumK {

    public int countSubsequences(int[] nums, int k) {
        /**
         * Time Complexity: O(2^n)
         * Space Complexity: O(n) (recursion depth)
         * 
         * Brute Force (Recursive) Approach:
         * ---------------------------------
         * 1. Generate all possible subsequences.
         * 2. For each subsequence, calculate the sum.
         * 3. Count those whose sum equals K.
         * 
         * Example:
         * nums = [1, 2, 1], k = 2
         * Subsequences: [], [1], [2], [1,1], [1,2], [2,1], [1,2,1]
         * Sums:         0,   1,   2,   2,    3,    3,     4
         * Count = 2  →  [2], [1,1]
         */

        /*
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        generate(nums, 0, new ArrayList<>(), res);

        int count = 0;
        for (List<Integer> subseq : res) {
            int sum = 0;
            for (int num : subseq) sum += num;
            if (sum == k) count++;
        }

        return count;
        */

        /**
         * Time Complexity: O(2^n)
         * Space Complexity: O(n) (recursion depth)
         * 
         * Optimal (Recursive Counting) Approach:
         * --------------------------------------
         * 1. Use recursion to explore all subsequences:
         *      - Include current element → add it to sum.
         *      - Exclude current element → skip it.
         * 2. Maintain running sum as we recurse.
         * 3. Base Case:
         *      - When index == nums.length, 
         *        check if current sum == K → count = 1.
         * 4. Return total count = include + exclude.
         * 
         * Example:
         * nums = [1, 2, 1], K = 2
         * helper(0, 0)
         * ├── include 1 → helper(1, 1)
         * │     ├── include 2 → helper(2, 3)
         * │     └── exclude 2 → helper(2, 1)
         * │           ├── include 1 → helper(3, 2) ✅ count++
         * │           └── exclude 1 → helper(3, 1)
         * └── exclude 1 → helper(1, 0)
         *       ├── include 2 → helper(2, 2) ✅ count++
         *       └── exclude 2 → helper(2, 0)
         */

        return helper(nums, 0, 0, k);
    }

    private int helper(int[] nums, int index, int currentSum, int k) {
        if (index == nums.length) {
            return (currentSum == k) ? 1 : 0;
        }
        int include = helper(nums, index + 1, currentSum + nums[index], k);

        int exclude = helper(nums, index + 1, currentSum, k);

        return include + exclude;
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

        System.out.print("Enter target sum (K): ");
        int k = sc.nextInt();

        CountSubsequencesWithSumK obj = new CountSubsequencesWithSumK();
        int count = obj.countSubsequences(nums, k);

        System.out.println("\nCount of subsequences with sum = " + k + ": " + count);

        sc.close();
    }
}
