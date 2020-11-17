package applications;

import net.datastructures.BinaryTree;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

// solution
public class Lab11Sum {
    private static void check(int expected, int actual) throws Exception {
       if (expected != actual) throw new Exception("expected="+expected+" actual="+actual);
    }

    public static void main(String[] args) throws Exception {
        LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>();

        // create this tree incrementally
        //       10
        //      /   \
        //     20   30
        //    / \   / \
        //  40  50 60  70
        Position<Integer> myRoot = lbt.addRoot(10);
        check(10, sumOfLeaves(lbt));

        Position<Integer> leftOfRoot = lbt.addLeft(myRoot, 20);
        check(20, sumOfLeaves(lbt));

        Position<Integer> rightOfRoot = lbt.addRight(myRoot, 30);
        check(50, sumOfLeaves(lbt));

        lbt.addRight(rightOfRoot, 70);
        check(90, sumOfLeaves(lbt));

        lbt.addLeft(rightOfRoot, 60);
        check(150, sumOfLeaves(lbt));

        lbt.addLeft(leftOfRoot, 40);
        check(170, sumOfLeaves(lbt));

        lbt.addRight(leftOfRoot, 50);
        check(220, sumOfLeaves(lbt));

        System.out.println("Passed all tests");
    }

    public static int sumOfLeaves(BinaryTree<Integer> tree) {
        if (tree.isEmpty()) return 0;
        else return sumOfLeavesHelper(tree, tree.root());
    }

    private static int sumOfLeavesHelper(BinaryTree<Integer> tree, Position<Integer> p) {
        
    }
}
