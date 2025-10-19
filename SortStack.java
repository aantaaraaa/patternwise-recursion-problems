import java.util.*;

public class SortStack {

    public void sortStack(Stack<Integer> stack) {
        /**
         * Time Complexity: O(n^2)
         * Space Complexity: O(n) (recursion stack)
         * 
         * Brute Force (Iterative) Approach:
         * ---------------------------------
         * 1. Use an auxiliary stack.
         * 2. Pop one element from the original stack at a time.
         * 3. While the auxiliary stack is not empty and the top element
         *    of the auxiliary stack is greater than the current element:
         *       - Pop from the auxiliary stack and push it back to the original stack.
         * 4. Push the current element into the auxiliary stack.
         * 5. Finally, transfer all elements back to the original stack.
         * 
         * This approach uses two stacks and an iterative process.
         */

        /*
         * Stack<Integer> temp = new Stack<>();
         * 
         * while (!stack.isEmpty()) {
         *     int current = stack.pop();
         *     while (!temp.isEmpty() && temp.peek() > current) {
         *         stack.push(temp.pop());
         *     }
         *     temp.push(current);
         * }
         * 
         * while (!temp.isEmpty()) {
         *     stack.push(temp.pop());
         * }
         */

        /**
         * Time Complexity: O(n^2)
         * Space Complexity: O(n) (due to recursion)
         * 
         * Optimal (Recursive) Approach:
         * ------------------------------
         * 1. Base case: If the stack is empty or has one element, it’s already sorted.
         * 2. Pop the top element.
         * 3. Recursively sort the remaining stack.
         * 4. Insert the popped element into the sorted stack at the correct position
         *    using another recursive helper function.
         *
         * Recursion Tree Example (stack = [3, 1, 4, 2]  → top = rightmost):
         * --------------------------------------------------------------
         * sortStack([3, 1, 4, 2])
         * ├── pop(2)
         * ├── sortStack([3, 1, 4])
         * │     ├── pop(4)
         * │     ├── sortStack([3, 1])
         * │     │     ├── pop(1)
         * │     │     ├── sortStack([3])
         * │     │     │     └── base case → return
         * │     │     └── insertInSortedOrder([3], 1) → [1, 3]
         * │     └── insertInSortedOrder([1, 3], 4) → [1, 3, 4]
         * └── insertInSortedOrder([1, 3, 4], 2)
         *       ├── pop(4), pop(3)
         *       ├── push(2)
         *       └── reinsert 3, 4 → [1, 2, 3, 4]
         *
         * Stack Unwinding (step-by-step):
         * 1️⃣ Pop elements until base case → [3]
         * 2️⃣ Rebuild sorted stack: [1,3] → [1,3,4] → [1,2,3,4]
         */

        if (stack.isEmpty() || stack.size() == 1)
            return;

        int top = stack.pop();
        sortStack(stack);
        insertInSortedOrder(stack, top);
    }

    private void insertInSortedOrder(Stack<Integer> stack, int element) {
        if (stack.isEmpty() || stack.peek() <= element) {
            stack.push(element);
            return;
        }

        int top = stack.pop();
        insertInSortedOrder(stack, element);
        stack.push(top);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Stack<Integer> stack = new Stack<>();
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        System.out.println("Enter stack elements:");
        for (int i = 0; i < n; i++) {
            stack.push(sc.nextInt());
        }

        System.out.println("Original Stack: " + stack);

        SortStack obj = new SortStack();
        obj.sortStack(stack);

        System.out.println("Sorted Stack: " + stack);

        sc.close();
    }
}
