import java.util.*;

public class CombinationSumII {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        /**
         * Time Complexity: O(2^N)
         * Space Complexity: O(N)
         * 
         * Brute Force (Iterative) Approach:
         * ---------------------------------
         * 1. Sort the candidates array to handle duplicates easily.
         * 2. Use a stack to simulate recursion.
         * 3. Each stack element stores (index, remaining target, current combination).
         * 4. Skip consecutive duplicates at the same recursion depth.
         * 5. Stop when remaining target == 0.
         */

        /*
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        Stack<int[]> stack = new Stack<>();
        Stack<List<Integer>> combos = new Stack<>();

        stack.push(new int[]{0, target});
        combos.push(new ArrayList<>());

        while (!stack.isEmpty()) {
            int[] state = stack.pop();
            int start = state[0];
            int remain = state[1];
            List<Integer> current = combos.pop();

            if (remain == 0) {
                result.add(new ArrayList<>(current));
                continue;
            }

            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i - 1]) continue;
                if (candidates[i] > remain) break;

                List<Integer> next = new ArrayList<>(current);
                next.add(candidates[i]);
                stack.push(new int[]{i + 1, remain - candidates[i]});
                combos.push(next);
            }
        }

        return result;
        */

        /**
         * Time Complexity: O(2^N)
         * Space Complexity: O(N)
         * 
         * Optimal (Recursive Backtracking) Approach:
         * ------------------------------------------
         * 1. Sort candidates to handle duplicates.
         * 2. Explore all combinations recursively.
         * 3. Each element can be used only once per combination.
         * 4. Skip consecutive duplicates at the same recursion depth.
         * 5. Add valid combinations (when target == 0) to the result.
         */

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
