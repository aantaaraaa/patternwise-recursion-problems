import java.util.*;

public class Atoi {

    public int myAtoi(String s) {
        /**
         * Time Complexity: O(n)
         * Space Complexity: O(1)
         * 
         * Brute Force (Iterative) Approach:
         * ---------------------------------
         * 1. Trim leading/trailing whitespaces.
         * 2. Detect the optional sign ('+' or '-').
         * 3. Loop through the digits one by one.
         * 4. Convert each digit into integer form and build the result.
         * 5. Handle overflow/underflow before multiplying.
         * 6. Stop at the first non-digit character.
         */

        /*
        s = s.trim();
        int n = s.length();
        if (n == 0) return 0;

        int i = 0;
        int result = 0;
        int sign = 1;

        if (i < n && (s.charAt(i) == '-' || s.charAt(i) == '+')) {
            sign = (s.charAt(i) == '+') ? 1 : -1;
            i++;
        }

        while (i < n && Character.isDigit(s.charAt(i))) {
            int digit = s.charAt(i) - '0';

            if (result > (Integer.MAX_VALUE - digit) / 10) {
                return (sign == -1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return sign * result;
        */

        /**
         * Time Complexity: O(n)
         * Space Complexity: O(n) (due to recursion stack)
         * 
         * Optimal (Recursive) Approach:
         * ------------------------------
         * 1. Trim spaces and handle empty input.
         * 2. Detect sign ('+' or '-').
         * 3. Use recursion to process digits one by one.
         * 4. Stop recursion when non-digit or end of string is reached.
         * 5. Check for overflow before each recursion step.
         * 6. Return final signed integer.
         */

        s = s.trim();
        if (s.isEmpty()) return 0;

        int sign = 1;
        int i = 0;

        if (s.charAt(i) == '-' || s.charAt(i) == '+') {
            sign = (s.charAt(i) == '+') ? 1 : -1;
            i++;
        }

        return helper(s, i, 0, sign);
    }

    private int helper(String s, int i, int result, int sign) {
        if (i >= s.length() || !Character.isDigit(s.charAt(i))) {
            return result * sign;
        }

        int digit = s.charAt(i) - '0';

        if (result > (Integer.MAX_VALUE - digit) / 10) {
            return (sign == -1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }

        return helper(s, i + 1, result * 10 + digit, sign);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to convert: ");
        String s = sc.nextLine();

        Atoi obj = new Atoi();

        int converted = obj.myAtoi(s);

        System.out.println("Converted integer: " + converted);

        sc.close();
    }
}
