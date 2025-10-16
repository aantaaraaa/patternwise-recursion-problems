import java.util.*;

public class GenerateBinaryStrings {

    /**
     * Generates all binary strings of length n.
     * 
     * Approach 1: Brute Force (Iterative)
     * ------------------------------------
     * Idea:
     * - There are exactly 2^n binary strings of length n.
     * - Loop from 0 to (2^n - 1).
     * - Convert each number to a binary string.
     * - Pad with leading zeros until its length equals n.
     * 
     * Time Complexity:  O(n * 2^n)
     * Space Complexity: O(2^n * n)
     */
    private List<String> generateIterative(int n) {
        List<String> result = new ArrayList<>();
        int total = 1 << n;

        for (int i = 0; i < total; i++) {
            String binary = String.format("%" + n + "s", Integer.toBinaryString(i))
                               .replace(' ', '0');
            result.add(binary);
        }

        return result;
    }

    /**
     * Approach 2: Optimal Recursive (Backtracking) using StringBuilder
     * -----------------------------------------------------------------
     * Idea:
     * - Treat each position as a binary choice ('0' or '1').
     * - Use backtracking to explore all 2^n possibilities.
     * - Append a bit, recurse, and then remove it (backtrack).
     * 
     * Time Complexity:  O(n * 2^n)
     * Space Complexity: O(n)   // recursion depth
     */
    private void generateRecursive(StringBuilder current, int n, List<String> result) {
        if (current.length() == n) {
            result.add(current.toString());
            return;
        }

        current.append('0');
        generateRecursive(current, n, result);
        current.deleteCharAt(current.length() - 1);

        current.append('1');
        generateRecursive(current, n, result);
        current.deleteCharAt(current.length() - 1);
    }

    public List<String> generateBinaryStrings(int n) {
        List<String> result = new ArrayList<>();
        generateRecursive(new StringBuilder(), n, result);
        return result;
    }

     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = sc.nextInt();

        if (n < 1 || n > 20) {
            System.out.println("Invalid input. Please enter a value of n between 1 and 20.");
            sc.close();
            return;
        }

        GenerateBinaryStrings obj = new GenerateBinaryStrings();
        List<String> binaries = obj.generateBinaryStrings(n);

        System.out.println("\nAll binary strings of length " + n + ":");
        for (String s : binaries) {
            System.out.println(s);
        }

        sc.close();
    }
}
