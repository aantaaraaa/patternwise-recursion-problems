import java.util.*;

public class CountGoodNumbers {

    static final long MOD = 1000000007L;

    public int countGoodNumbers(long n) {
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
         * Example:
         * n = 4 → indices 0,1,2,3
         * even indices = 0,2 → 5 choices each
         * odd indices  = 1,3 → 4 choices each
         * total = (5 * 4 * 5 * 4) % MOD = 400
         */

        /*
         * long ans = 1;
         * for (long i = 0; i < n; i++) {
         *     if (i % 2 == 0)
         *         ans = (ans * 5) % MOD;
         *     else
         *         ans = (ans * 4) % MOD;
         * }
         * return ans;
         */

        /**
         * Time Complexity: O(log n)
         * Space Complexity: O(log n) (due to recursion stack)
         * 
         * Optimal (Recursive - Exponentiation by Squaring) Approach:
         * ----------------------------------------------------------
         * 1. Even indices → 5 choices each.
         * 2. Odd indices → 4 choices each.
         * 3. Let:
         *      evenCount = (n + 1) / 2
         *      oddCount  = n / 2
         * 4. Result = (5^evenCount * 4^oddCount) % MOD
         * 
         * Recursion Tree Example (for power(5, 5)):
         * ------------------------------------------
         * power(5, 5)
         * ├── half = power(5, 2)
         * │     ├── half = power(5, 1)
         * │     │     ├── half = power(5, 0) → base → 1
         * │     │     └── exp = 1 → odd → return (1 * 1 * 5) % MOD = 5
         * │     └── exp = 2 → even → return (5 * 5) % MOD = 25
         * └── exp = 5 → odd → return (25 * 25 * 5) % MOD = 3125 % MOD
         *
         * Stack Unwinding:
         * power(0) = 1
         * power(1) = 5
         * power(2) = 25
         * power(5) = 3125
         *
         * Thus, 5^5 = 3125
         */

        if (n == 0)
            return 1;

        long evenCount = (n + 1) / 2;
        long oddCount = n / 2;

        long part1 = power(5, evenCount);
        long part2 = power(4, oddCount);

        return (int)((part1 * part2) % MOD);
    }

    private long power(long base, long exp) {
        if (exp == 0)
            return 1;

        long half = power(base, exp / 2);

        if (exp % 2 == 0)
            return (half * half) % MOD;
        else
            return (half * half * base) % MOD;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        long n = sc.nextLong();

        CountGoodNumbers obj = new CountGoodNumbers();
        long result = obj.countGoodNumbers(n);

        System.out.println("Count of good numbers: " + result);

        sc.close();
    }
}

