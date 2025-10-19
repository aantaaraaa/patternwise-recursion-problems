import java.util.*;

public class RatInMaze {

    /**
     * Rat in a Maze (Backtracking)
     *
     * Time Complexity: O(4^(n*n))
     * Space Complexity: O(n*n)
     *
     * Problem:
     * Given an n×n maze filled with 0s (blocked) and 1s (open),
     * find all possible paths from the top-left corner (0,0)
     * to the bottom-right corner (n-1, n-1).
     *
     * Moves allowed: Up (U), Down (D), Left (L), Right (R)
     *
     * Core Idea:
     * Explore all four directions recursively.
     * Mark a cell as visited before exploring neighbors.
     * Backtrack (unmark) after returning from recursion to allow other paths.
     *
     * Recursion Tree Example:
     * ------------------------
     * Input Maze (1 = open, 0 = blocked)
     * 1 0 0 0
     * 1 1 0 1
     * 1 1 0 0
     * 0 1 1 1
     *
     * Path Exploration:
     * helper(0,0,"")
     * ├── Down → helper(1,0,"D")
     * │     ├── Right → helper(1,1,"DR")
     * │     │      ├── Down → helper(2,1,"DRD")
     * │     │      │      ├── Down → helper(3,1,"DRDD")
     * │     │      │      │      ├── Right → helper(3,2,"DRDDR")
     * │     │      │      │      │      ├── Right → helper(3,3,"DRDDRR") ✅ Found path
     * │     │      │      │      └── Backtrack
     * │     │      │      └── Backtrack
     * │     │      └── Backtrack
     * │     └── Backtrack
     * └── Backtrack
     *
     * Output:
     * [DRDDRR, DDRDRR]
     */

    public List<String> findPath(int[][] maze) {
        int n = maze.length;
        List<String> result = new ArrayList<>();

        if (maze[0][0] == 0 || maze[n - 1][n - 1] == 0)
            return result;

        boolean[][] visited = new boolean[n][n];
        helper(maze, 0, 0, "", visited, result);
        return result;
    }

    private void helper(int[][] maze, int i, int j, String path, boolean[][] visited, List<String> result) {
        int n = maze.length;

        if (i == n - 1 && j == n - 1) {
            result.add(path);
            return;
        }

        visited[i][j] = true;

        if (isSafe(maze, i + 1, j, visited))  
            helper(maze, i + 1, j, path + "D", visited, result);

        if (isSafe(maze, i, j - 1, visited))  
            helper(maze, i, j - 1, path + "L", visited, result);

        if (isSafe(maze, i, j + 1, visited))  
            helper(maze, i, j + 1, path + "R", visited, result);

        if (isSafe(maze, i - 1, j, visited))  
            helper(maze, i - 1, j, path + "U", visited, result);

        visited[i][j] = false; 
    }

    private boolean isSafe(int[][] maze, int i, int j, boolean[][] visited) {
        int n = maze.length;
        return i >= 0 && i < n && j >= 0 && j < n && maze[i][j] == 1 && !visited[i][j];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of maze (n): ");
        int n = sc.nextInt();

        int[][] maze = new int[n][n];
        System.out.println("Enter maze (1 for open, 0 for blocked):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maze[i][j] = sc.nextInt();
            }
        }

        RatInMaze obj = new RatInMaze();
        List<String> paths = obj.findPath(maze);

        if (paths.isEmpty()) {
            System.out.println("❌ No path found.");
        } else {
            System.out.println("✅ All possible paths:");
            for (String path : paths)
                System.out.println(path);
        }

        sc.close();
    }
}
