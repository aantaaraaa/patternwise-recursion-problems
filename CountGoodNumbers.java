import java.util.*;

public class CountGoodNumbers {

    static final long MOD = 1000000007L;

    public long countGoodNumbers(long n) {
        /**
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         * 
         * Brute Force (Iterative) Approach:
         * ---------------------------------
         * 1. Initialize ans = 1.
         * 2. For each index i from 0 to n - 1:
         *    - If i is even, multiply ans by 5.
         *    - If i is odd, multiply ans by 4.
         * 3. Take modulo after every multiplication to prevent overflow.
         * 4. Return ans % MOD.
         * 
         * This approach works correctly but is inefficient for large n
         * (up to 10^15) since it performs O(n) multiplications.
         * 
         * Example:
         * n = 4 → indices 0,1,2,3
         * even indices = 0,2 → 5 choices each
         * odd indices  = 1,3 → 4 choices each
         * total = (5 * 4 * 5 * 4) % MOD = 400
         */

        /*
        long ans = 1;
        for (long i = 0; i < n; i++) {
            if (i % 2 == 0)
                ans = (ans * 5) % MOD;
            else
                ans = (ans * 4) % MOD;
        }
        return ans;
        */

        /**
         * Time Complexity: O(log n)
         * Space Complexity: O(1)
         * 
         * Optimal (Iterative - Exponentiation by Squaring) Approach:
         * ----------------------------------------------------------
         * Observation:
         * - Even indices → 5 choices each
         * - Odd indices  → 4 choices each
         * 
         * Let:
         * evenCount = (n + 1) / 2
         * oddCount  = n / 2
         * 
         * Therefore:
         * total = (5^evenCount * 4^oddCount) % MOD
         * 
         * Steps:
         * 1. Calculate evenCount and oddCount.
         * 2. Compute power(5, evenCount) % MOD.
         * 3. Compute power(4, oddCount) % MOD.
         * 4. Multiply both parts under modulo and return.
         */

        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;

        /*
        long part1 = powerIterative(5, evenCount);
        long part2 = powerIterative(4, oddCount);
        return (part1 * part2) % MOD;
        */

        /**
         * Time Complexity: O(log n)
         * Space Complexity: O(log n) (due to recursion stack)
         * 
         * Optimal (Recursive - Exponentiation by Squaring) Approach:
         * ----------------------------------------------------------
         * Instead of iterative squaring, recursion can be used to
         * divide the exponent by 2 in each step.
         * 
         * Steps:
         * 1. Base case: if exp == 0 → return 1
         * 2. Recursively compute half = powerRecursive(base, exp / 2)
         * 3. Square the result and take modulo.
         * 4. If exp is odd → multiply once more by base and take modulo.
         */

        long part1 = powerRecursive(5, evenCount);
        long part2 = powerRecursive(4, oddCount);

        return (part1 * part2) % MOD;
    }

    private long powerRecursive(long base, long exp) {
        if (exp == 0)
            return 1;

        long half = powerRecursive(base, exp / 2);
        long result = (half * half) % MOD;

        if (exp % 2 != 0)
            result = (result * base) % MOD;

        return result;
    }

    /**
     * Iterative version of power function (for reference)
     * ---------------------------------------------------
     * private long powerIterative(long base, long exp) {
     *     long result = 1;
     *     base = base % MOD;
     * 
     *     while (exp > 0) {
     *         if ((exp & 1) == 1)
     *             result = (result * base) % MOD;
     * 
     *         base = (base * base) % MOD;
     *         exp >>= 1;
     *     }
     * 
     *     return result;
     * }
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        long n = sc.nextLong();

        CountGoodNumbers obj = new CountGoodNumbers();
        long count = obj.countGoodNumbers(n);

        System.out.println("Count of good numbers: " + count);
        sc.close();
    }
}
