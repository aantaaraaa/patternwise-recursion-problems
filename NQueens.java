import java.util.*;

public class NQueens {

    public List<List<String>> solveNQueens(int n) {
        /**
         * Time Complexity: O(N!)
         * Space Complexity: O(N^2)
         * 
         * Backtracking Approach:
         * -----------------------
         * 1. Place one queen per row.
         * 2. For each row, try placing the queen in each column.
         * 3. Before placing, check if the position is safe:
         *      - No other queen in the same column.
         *      - No other queen on the upper-left or upper-right diagonal.
         * 4. If safe, place the queen and recurse to the next row.
         * 5. If not safe, try next column.
         * 6. Once all queens are placed, add the board configuration to the result.
         * 7. Backtrack by removing the last placed queen and continue exploring.
         */

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
