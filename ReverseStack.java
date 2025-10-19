import java.util.*;

public class ReverseStack {

    public void reverseStack(Stack<Integer> stack) {
        /**
         * Time Complexity: O(n^2)
         * Space Complexity: O(n) (recursion stack)
         * 
         * Brute Force (Iterative) Approach:
         * ---------------------------------
         * 1. Use an auxiliary stack.
         * 2. Pop all elements from the original stack into the auxiliary stack.
         * 3. Now the auxiliary stack has elements in reversed order.
         * 4. Transfer them back to the original stack.
         * 
         * Example:
         * Original: [1, 2, 3, 4]
         * Temp:     [4, 3, 2, 1]
         * Final:    [4, 3, 2, 1]
         */

        /*
         * Stack<Integer> temp = new Stack<>();
         * while (!stack.isEmpty()) {
         *     temp.push(stack.pop());
         * }
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
         * 1. Base case: If the stack is empty, return.
         * 2. Pop the top element.
         * 3. Recursively reverse the remaining stack.
         * 4. Insert the popped element at the **bottom** of the stack
         *    using another recursive helper function.
         *
         * Recursion Tree Example (stack = [1, 2, 3, 4] → top = rightmost):
         * ---------------------------------------------------------------
         * reverseStack([1, 2, 3, 4])
         * ├── pop(4)
         * ├── reverseStack([1, 2, 3])
         * │     ├── pop(3)
         * │     ├── reverseStack([1, 2])
         * │     │     ├── pop(2)
         * │     │     ├── reverseStack([1])
         * │     │     │     ├── pop(1)
         * │     │     │     ├── reverseStack([]) → base → return
         * │     │     │     └── insertAtBottom([], 1) → [1]
         * │     │     └── insertAtBottom([1], 2) → [2, 1]
         * │     └── insertAtBottom([2, 1], 3) → [3, 2, 1]
         * └── insertAtBottom([3, 2, 1], 4) → [4, 3, 2, 1]
         *
         * Stack Unwinding (step-by-step):
         * 1️⃣ Pop elements: [1, 2, 3, 4] → []
         * 2️⃣ Rebuild reversed stack:
         *     [] → [1] → [2, 1] → [3, 2, 1] → [4, 3, 2, 1]
         */

        if (stack.isEmpty())
            return;

        int top = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, top);
    }

    private void insertAtBottom(Stack<Integer> stack, int element) {
        if (stack.isEmpty()) {
            stack.push(element);
            return;
        }

        int top = stack.pop();
        insertAtBottom(stack, element);
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

        ReverseStack obj = new ReverseStack();
        obj.reverseStack(stack);

        System.out.println("Reversed Stack: " + stack);

        sc.close();
    }
}
