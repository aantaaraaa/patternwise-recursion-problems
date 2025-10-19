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
         * Classic (Recursive - Non-Tail) Approach:
         * ----------------------------------------
         * 1. Trim spaces and handle empty input.
         * 2. Detect sign ('+' or '-').
         * 3. Recursively process digits starting from the current index.
         * 4. Stop recursion when a non-digit or end of string is reached.
         * 5. Combine digits on the way back up (classic recursion).
         * 6. Return the final signed integer.
         *
         * Recursion Tree Example (for s = "123"):
         * ---------------------------------------
         * helper("123", 0)
         * ├── digit = 1
         * ├── helper("123", 1)
         * │     ├── digit = 2
         * │     ├── helper("123", 2)
         * │     │     ├── digit = 3
         * │     │     ├── helper("123", 3) → base case → returns 0
         * │     │     └── return = 3 * 10^(3-2-1) + 0 = 3
         * │     └── return = 2 * 10^(3-1-1) + 3 = 20 + 3 = 23
         * └── return = 1 * 10^(3-0-1) + 23 = 100 + 23 = 123
         *
         * Stack unwinding:
         * helper(2) returns 3
         * helper(1) returns 23
         * helper(0) returns 123
         */

        s = s.trim();
        if (s.isEmpty()) return 0;

        int sign = 1;
        int i = 0;

        if (s.charAt(i) == '-' || s.charAt(i) == '+') {
            sign = (s.charAt(i) == '+') ? 1 : -1;
            i++;
        }

        return sign * helper(s, i);
    }

    private int helper(String s, int i) {
        if (i >= s.length() || !Character.isDigit(s.charAt(i))) return 0;

        int digit = s.charAt(i) - '0';
        int value = helper(s, i + 1);

        if (value > (Integer.MAX_VALUE - digit) / 10)
            return Integer.MAX_VALUE;

        return digit * (int) Math.pow(10, s.length() - i - 1) + value;
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
