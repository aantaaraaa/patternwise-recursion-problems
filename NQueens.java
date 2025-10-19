import java.util.*;

public class NQueens {

    /**
     * N-Queens Problem (Backtracking)
     *
     * Time Complexity: O(N!)
     * Space Complexity: O(N^2)
     *
     * Problem:
     * Place N queens on an N×N chessboard such that no two queens attack each other.
     *
     * Key Constraints:
     * - Only one queen per row.
     * - No two queens share the same column.
     * - No two queens share the same diagonal.
     *
     * Recursion Tree Example:
     * ------------------------
     * Input: N = 4
     *
     * Step 1: Try placing queen row by row.
     *
     *                          Row 0
     *                       /    |    |    \
     *                    C0     C1   C2    C3
     *                    ❌     ✅    ❌    ✅
     *                     |            |
     *                  Row 1        Row 1
     *                / | \          / | \
     *              ...             ...
     *
     * For N = 4, valid configurations:
     *  - [".Q..", "...Q", "Q...", "..Q."]
     *  - ["..Q.", "Q...", "...Q", ".Q.."]
     *
     * Each recursive level corresponds to one row.
     * Each branch explores a different column choice for that row.
     * Backtracking occurs whenever a placement violates safety rules.
     */

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        char[][] board = new char[n][n];
        for (char[] row : board) Arrays.fill(row, '.');

        backtrack(board, 0, n, res);
        return res;
    }

    private void backtrack(char[][] board, int row, int n, List<List<String>> res) {
        if (row == n) {
            res.add(construct(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col, n)) {
                board[row][col] = 'Q';
                backtrack(board, row + 1, n, res);
                board[row][col] = '.';
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col, int n) {
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') return false;
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') return false;
        }

        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }

        return true;
    }

    private List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) result.add(new String(row));
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of queens (N): ");
        int n = sc.nextInt();

        NQueens obj = new NQueens();
        List<List<String>> solutions = obj.solveNQueens(n);

        System.out.println("\nAll possible solutions:");
        for (List<String> board : solutions) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println();
        }

        sc.close();
    }
}
