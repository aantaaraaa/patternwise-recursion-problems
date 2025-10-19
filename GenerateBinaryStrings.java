import java.util.*;

public class GenerateBinaryStrings {

    /**
     * Generate All Binary Strings of Length n
     *
     * Time Complexity: O(2^n)
     * Space Complexity: O(n)  (recursion stack)
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: n = 3
     *
     *                         ""
     *                        /  \
     *                     "0"    "1"
     *                    /  \    /  \
     *                "00"  "01" "10" "11"
     *                / \    / \  / \  / \
     *           "000" "001" ... etc.
     *
     * Output:
     * 000
     * 001
     * 010
     * 011
     * 100
     * 101
     * 110
     * 111
     *
     * Explanation:
     * Each node represents a partial binary string.
     * Each recursive call appends '0' or '1' to the current string.
     * Once the string length reaches n, it is printed as a complete result.
     */

    public void generateBinaryStrings(int n) {
        helper("", n);
    }

    private void helper(String current, int n) {
        if (current.length() == n) {
            System.out.println(current);
            return;
        }

        helper(current + "0", n);
        helper(current + "1", n);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the length of binary strings (n): ");
        int n = sc.nextInt();

        GenerateBinaryStrings obj = new GenerateBinaryStrings();
        System.out.println("\nAll possible binary strings of length " + n + ":");
        obj.generateBinaryStrings(n);

        sc.close();
    }
}
