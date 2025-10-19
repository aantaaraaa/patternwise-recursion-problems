import java.util.*;

public class CombinationSumIII {

    /**
     * Combination Sum III (Backtracking)
     *
     * Time Complexity: O(C(9, k))
     * Space Complexity: O(k)
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: k = 3, n = 7
     * Using numbers 1 to 9
     *
     *                        (start=1, k=3, target=7)
     *                         /    |     |     \
     *                     +1 v   +2 v  +3 v   +4 v  ...
     *                   (2,2,6) (3,2,5) (4,2,4) ...
     *                     |       |       |
     *                 +2  v   +3  v   +4  v
     *              (3,3,4)  (4,3,2) (5,3,0)
     *                                 ✅ [1,2,4]
     *
     * Valid Combinations:
     *  - [1, 2, 4]
     *
     * Key Insights:
     * 1. Each number 1–9 can be used at most once.
     * 2. Recursion stops when:
     *       - target == 0 and size == k → valid combination
     *       - target < 0 or size > k → invalid path
     * 3. Next recursive call always uses i + 1 (no repetition).
     */

    public List<List<Integer>> combinationSum3(int k, int n) {
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
