import java.util.*;

public class PowXN {

    /**
     * Power Function (x^n) — Exponentiation by Squaring
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(log n)  (recursion depth)
     *
     * Problem:
     * Compute x raised to the power n (xⁿ), where n can be negative.
     *
     * Core Idea:
     * Use divide-and-conquer:
     *  - xⁿ = (xⁿ/²)²       if n is even
     *  - xⁿ = (xⁿ/²)² * x   if n is odd
     *
     * Handle negatives by converting:
     *  x⁻ⁿ = (1/x)ⁿ
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: x = 2, n = 5
     *
     *                 helper(2, 5)
     *                   /     \
     *        helper(2, 2)       odd → *2
     *          /     \
     * helper(2, 1)    even → square
     *     |
     * helper(2, 0) → 1
     *
     * Computation Flow:
     * helper(0) = 1
     * helper(1) = 2
     * helper(2) = 2² = 4
     * helper(5) = 4² * 2 = 32
     *
     * Result = 32
     */

    public double myPow(double x, int n) {
        if (n == 0)
            return 1.0;
        if (x == 0)
            return 0.0;

        long exp = n;
        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }

        return helper(x, exp);
    }

    private double helper(double x, long exp) {
        if (exp == 0)
            return 1.0;

        double half = helper(x, exp / 2);

        if (exp % 2 == 0)
            return half * half;
        else
            return half * half * x;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter base (x): ");
        double x = sc.nextDouble();

        System.out.print("Enter exponent (n): ");
        int n = sc.nextInt();

        PowXN obj = new PowXN();
        double result = obj.myPow(x, n);

        System.out.println("Result: " + result);

        sc.close();
    }
}
