import java.util.*;

public class WordSearch {

    /**
     * Word Search (Backtracking)
     *
     * Time Complexity: O(N * M * 4^L)
     * Space Complexity: O(L)
     *
     * Problem:
     * Given a 2D grid of characters and a word, return true if the word exists
     * in the grid. The word can be constructed from letters of sequentially adjacent cells,
     * where adjacent cells are horizontally or vertically neighboring. The same letter
     * cell may not be used more than once.
     *
     * Core Idea:
     * 1. Start DFS from every cell matching the first character.
     * 2. Explore recursively in all four directions.
     * 3. Mark cells as visited during exploration and backtrack afterward.
     * 4. If all characters are matched, return true.
     *
     * Recursion Tree Example:
     * ------------------------
     * board =
     * A B C E
     * S F C S
     * A D E E
     * word = "ABCCED"
     *
     * Start from A(0,0)
     * helper(0,0,0)
     *  ├── (1,0) → 'S' ❌
     *  ├── (0,1) → 'B' ✅ helper(0,1,1)
     *  │       ├── (0,2) → 'C' ✅ helper(0,2,2)
     *  │       │       ├── (1,2) → 'C' ✅ helper(1,2,3)
     *  │       │       │       ├── (2,2) → 'E' ✅ helper(2,2,4)
     *  │       │       │       │       ├── (2,1) → 'D' ✅ helper(2,1,5)
     *  │       │       │       │       │       ✅ word found
     *
     * ✅ Output: true
     */

    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (backtrack(board, word, 0, i, j))
                    return true;
            }
        }

        return false;
    }

    private boolean backtrack(char[][] board, String word, int index, int i, int j) {
        if (index == word.length())
            return true;

        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(index))
            return false;

        char temp = board[i][j];
        board[i][j] = '#';

        boolean found =
                backtrack(board, word, index + 1, i + 1, j) ||  // Down
                backtrack(board, word, index + 1, i - 1, j) ||  // Up
                backtrack(board, word, index + 1, i, j + 1) ||  // Right
                backtrack(board, word, index + 1, i, j - 1);    // Left

        board[i][j] = temp;
        return found;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();
        System.out.print("Enter number of columns: ");
        int cols = sc.nextInt();

        char[][] board = new char[rows][cols];
        System.out.println("Enter grid elements (each row as a continuous string):");
        for (int i = 0; i < rows; i++) {
            String row = sc.next();
            for (int j = 0; j < cols; j++) {
                board[i][j] = row.charAt(j);
            }
        }

        System.out.print("Enter word to search: ");
        String word = sc.next();

        WordSearch obj = new WordSearch();
        boolean exists = obj.exist(board, word);

        if (exists)
            System.out.println("✅ Word exists in the grid.");
        else
            System.out.println("❌ Word not found.");

        sc.close();
    }
}
