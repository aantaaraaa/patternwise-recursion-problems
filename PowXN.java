import java.util.*;

public class PowXN {

    public double myPow(double x, int n) {
        /**
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         * 
         * Brute Force (Iterative) Approach:
         * ---------------------------------
         * 1. Initialize result = 1.
         * 2. If n is negative, take reciprocal of x (x = 1/x) and make n positive.
         * 3. Multiply result by x exactly n times.
         * 4. Return the result.
         * 
         * This approach is simple and correct, but inefficient for large n,
         * because it requires O(n) multiplications.
         * 
         * Example:
         * x = 2, n = 5
         * result = 1
         * result = result * 2 → 2
         * result = result * 2 → 4
         * result = result * 2 → 8
         * result = result * 2 → 16
         * result = result * 2 → 32
         * return 32
         */

        /*
         * double result = 1.0;
         * long exp = n;
         * 
         * if (exp < 0) {
         *     x = 1 / x;
         *     exp = -exp;
         * }
         * 
         * for (long i = 0; i < exp; i++) {
         *     result *= x;
         * }
         * 
         * return result;
         */

        /**
         * Time Complexity: O(log n)
         * Space Complexity: O(1)
         * 
         * Optimal (Iterative - Exponentiation by Squaring) Approach:
         * ----------------------------------------------------------
         * 1. Handle edge cases: n == 0 or x == 0.
         * 2. Convert n to long to prevent overflow when n == Integer.MIN_VALUE.
         * 3. If n is negative, take reciprocal of x and make n positive.
         * 4. Initialize result = 1.
         * 5. While n > 0:
         *    - If n is odd, multiply result by x.
         *    - Square x.
         *    - Divide n by 2.
         * 6. Return result.
         */

        if (n == 0)
            return 1.0;
        if (x == 0)
            return 0.0;

        long exp = n;
        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }

        /*
         * double result = 1.0;
         * 
         * while (exp > 0) {
         *     if ((exp & 1) == 1)
         *         result *= x;
         * 
         *     x *= x;
         *     exp >>= 1;
         * }
         * 
         * return result;
         */

        /**
         * Time Complexity: O(log n)
         * Space Complexity: O(log n) (due to recursion stack)
         * 
         * Optimal (Recursive - Exponentiation by Squaring) Approach:
         * ----------------------------------------------------------
         * 1. Handle edge cases: n == 0 or x == 0.
         * 2. Convert n to long to prevent overflow when n == Integer.MIN_VALUE.
         * 3. If n is negative, take reciprocal of x and make n positive.
         * 4. Recursively compute:
         *    - Base case: n == 0 → return 1
         *    - Recursive case: compute half = helper(x, n / 2)
         *      * if n even → return half * half
         *      * if n odd  → return half * half * x
         */

        return helper(x, exp, 1.0);
    }

    private double helper(double x, long exp, double result) {
        if (exp == 0)
            return result;

        if ((exp & 1) == 1)
            result *= x;

        x *= x;
        exp /= 2;

        return helper(x, exp, result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter base (x): ");
        double x = sc.nextDouble();

        System.out.print("Enter exponent (n): ");
        int n = sc.nextInt();

        PowXN obj = new PowXN();
        double power = obj.myPow(x, n);

        System.out.println("Result: " + power);

        sc.close();
    }
}
