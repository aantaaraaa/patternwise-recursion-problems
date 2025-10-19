import java.util.*;

public class SudokuSolver {

    /**
     * Sudoku Solver (Backtracking)
     *
     * Time Complexity: O(9^(n*n))
     * Space Complexity: O(n*n)
     *
     * Problem:
     * Given a partially filled 9x9 Sudoku board (0 = empty),
     * fill it such that every row, column, and 3x3 subgrid
     * contains digits 1–9 exactly once.
     *
     * Approach (Recursive Backtracking):
     * ---------------------------------
     * 1. Find the first empty cell.
     * 2. Try placing digits 1–9 in it.
     * 3. If a number is valid:
     *      - Place it temporarily.
     *      - Recurse for the next cell.
     * 4. If no number fits, backtrack (reset cell to 0).
     * 5. Stop once the entire board is filled correctly.
     *
     * Recursion Tree Example (Concept):
     * ---------------------------------
     * solve(cell_0)
     * ├── try 1 ❌ invalid
     * ├── try 2 ✅ → solve(cell_1)
     * │      ├── try 3 ✅ → solve(cell_2)
     * │      │      ├── ...
     * │      │      └── backtrack
     * │      └── try 4 ...
     * └── try 5 ...
     *
     * Backtracking occurs when a later choice makes the board invalid,
     * undoing previous placements to try alternate numbers.
     */

    public boolean solveSudoku(int[][] board) {
        return solve(board);
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) { 
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num;

                            if (solve(board))
                                return true;

                            board[row][col] = 0; 
                        }
                    }
                    return false; 
                }
            }
        }
        return true;
    }

    private boolean isSafe(int[][] board, int row, int col, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num || board[i][col] == num)
                return false;
        }

        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num)
                    return false;
            }
        }

        return true;
    }

    private void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
                if ((j + 1) % 3 == 0 && j < 8)
                    System.out.print("| ");
            }
            System.out.println();
            if ((i + 1) % 3 == 0 && i < 8)
                System.out.println("---------------------");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[][] board = new int[9][9];
        System.out.println("Enter Sudoku grid (9x9, use 0 for empty cells):");

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = sc.nextInt();
            }
        }

        SudokuSolver solver = new SudokuSolver();

        System.out.println("\nOriginal Sudoku:");
        solver.printBoard(board);

        if (solver.solveSudoku(board)) {
            System.out.println("\n✅ Sudoku solved successfully:");
            solver.printBoard(board);
        } else {
            System.out.println("\n❌ No solution exists for the given Sudoku.");
        }

        sc.close();
    }
}
