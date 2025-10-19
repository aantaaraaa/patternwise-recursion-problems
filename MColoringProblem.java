import java.util.*;

public class MColoringProblem {

    /**
     * M-Coloring Problem (Backtracking)
     *
     * Time Complexity: O(m^V)
     * Space Complexity: O(V)
     *
     * Problem:
     * Given an undirected graph with V vertices, determine if it can be colored
     * using at most M colors such that no two adjacent vertices share the same color.
     *
     * Core Idea:
     * Try to assign each vertex one of M colors recursively.
     * If at any point no color fits (due to adjacent constraints), backtrack.
     *
     * Recursion Tree Example:
     * ------------------------
     * For V = 3, M = 2
     * Graph edges: (0–1), (1–2), (0–2)
     *
     *                      color[0] = 1
     *                      /          \
     *           color[1] = 1 ❌     color[1] = 2 ✅
     *                               /        \
     *                    color[2] = 1 ✅     color[2] = 2 ❌
     *
     * Valid Coloring: [1, 2, 1]
     *
     * Explanation:
     * Each recursive level represents coloring one vertex.
     * Each branch represents trying a different color.
     * Backtracking occurs when no valid color is found for the current vertex.
     */

    public boolean graphColoring(boolean[][] graph, int m) {
        int V = graph.length;
        int[] color = new int[V];

        if (solve(0, graph, color, m, V)) {
            System.out.println("✅ Graph can be colored successfully.");
            System.out.println("Color assignment: " + Arrays.toString(color));
            return true;
        } else {
            System.out.println("❌ Graph cannot be colored with " + m + " colors.");
            return false;
        }
    }

    private boolean solve(int node, boolean[][] graph, int[] color, int m, int V) {
        if (node == V)
            return true;

        for (int c = 1; c <= m; c++) {
            if (isSafe(node, graph, color, V, c)) {
                color[node] = c;

                if (solve(node + 1, graph, color, m, V))
                    return true;

                color[node] = 0; 
            }
        }

        return false;
    }

    private boolean isSafe(int node, boolean[][] graph, int[] color, int V, int col) {
        for (int k = 0; k < V; k++) {
            if (graph[node][k] && color[k] == col)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of vertices: ");
        int V = sc.nextInt();

        System.out.print("Enter number of colors (m): ");
        int m = sc.nextInt();

        boolean[][] graph = new boolean[V][V];
        System.out.println("Enter adjacency matrix (0 or 1):");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = sc.nextInt() == 1;
            }
        }

        MColoringProblem obj = new MColoringProblem();
        obj.graphColoring(graph, m);

        sc.close();
    }
}
