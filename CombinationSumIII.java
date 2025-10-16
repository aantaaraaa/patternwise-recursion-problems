import java.util.*;

public class CombinationSumIII {

    public List<List<Integer>> combinationSum3(int k, int n) {
        /**
         * Time Complexity: O(C(9, k))
         * Space Complexity: O(k)
         * 
         * Brute Force (Iterative) Approach:
         * ---------------------------------
         * 1. Use numbers 1 through 9.
         * 2. Use a stack to simulate recursion.
         * 3. Each stack element stores (next number, remaining sum, current list).
         * 4. When the current list size == k and sum == 0, store the combination.
         * 5. Stop when next number exceeds 9 or sum < 0.
         */

        /*
        List<List<Integer>> result = new ArrayList<>();
        Stack<int[]> stack = new Stack<>();
        Stack<List<Integer>> combos = new Stack<>();

        stack.push(new int[]{1, n});
        combos.push(new ArrayList<>());

        while (!stack.isEmpty()) {
            int[] state = stack.pop();
            int start = state[0];
            int remain = state[1];
            List<Integer> current = combos.pop();

            if (remain == 0 && current.size() == k) {
                result.add(new ArrayList<>(current));
                continue;
            }

            if (remain < 0 || current.size() > k) continue;

            for (int i = 9; i >= start; i--) {
                List<Integer> next = new ArrayList<>(current);
                next.add(i);
                stack.push(new int[]{i + 1, remain - i});
                combos.push(next);
            }
        }

        return result;
        */

        /**
         * Time Complexity: O(C(9, k))
         * Space Complexity: O(k)
         * 
         * Optimal (Recursive Backtracking) Approach:
         * ------------------------------------------
         * 1. Explore all combinations using numbers 1 through 9.
         * 2. Each number can be used only once.
         * 3. Track both the sum (n) and count (k).
         * 4. When sum == 0 and size == k, store the valid combination.
         * 5. Stop when sum < 0 or size exceeds k.
         */

        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, k, n, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int k, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0 && current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        if (target < 0 || current.size() > k) {
            return;
        }

        for (int i = start; i <= 9; i++) {
            current.add(i);
            backtrack(i + 1, k, target - i, current, result);
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of elements (k): ");
        int k = sc.nextInt();

        System.out.print("Enter target sum (n): ");
        int n = sc.nextInt();

        CombinationSumIII obj = new CombinationSumIII();
        List<List<Integer>> result = obj.combinationSum3(k, n);

        System.out.println("Combinations of " + k + " numbers that sum to " + n + ":");
        System.out.println(result);

        sc.close();
    }
}
