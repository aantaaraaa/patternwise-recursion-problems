import java.util.*;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        /**
         * Time Complexity: O(N^(T/M))
         * Space Complexity: O(T/M)
         * 
         * Brute Force (Iterative) Approach:
         * ---------------------------------
         * 1. Sort the candidates array.
         * 2. Use a stack to simulate recursion.
         * 3. Each stack element stores (index, remaining target, current list).
         * 4. Push and pop until stack is empty.
         * 5. When remaining target == 0, add combination to result.
         * 6. Avoid revisiting previous elements (maintain start index).
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
                if (candidates[i] > remain) break;

                List<Integer> next = new ArrayList<>(current);
                next.add(candidates[i]);
                stack.push(new int[]{i, remain - candidates[i]});
                combos.push(next);
            }
        }

        return result;
        */

        /**
         * Time Complexity: O(N^(T/M))
         * Space Complexity: O(T/M)
         * 
         * Optimal (Recursive Backtracking) Approach:
         * ------------------------------------------
         * 1. Explore all possible combinations using recursion.
         * 2. Stop recursion when:
         *      - target == 0 → valid combination
         *      - target < 0  → invalid path
         * 3. Reuse elements (call backtrack with same index).
         * 4. Use start index to avoid duplicates.
         * 5. Add a copy of current combination to result when valid.
         */

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