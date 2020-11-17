package applications;

import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

import java.util.Iterator;

public class Lab11Traversal {
    public static void main(String[] args) {
        LinkedBinaryTree<Integer> lbt = new LinkedBinaryTree<>();

        // create this tree
        //       10
        //      /   \
        //     20   30
        //    /     / \
        //  40    60  70
        //             \
        //             80
        Position<Integer> myRoot = lbt.addRoot(10);
        Position<Integer> leftOfRoot = lbt.addLeft(myRoot, 20);
        Position<Integer> rightOfRoot = lbt.addRight(myRoot, 30);
        Position<Integer> rightOfRightOfRoot = lbt.addRight(rightOfRoot, 70);
        lbt.addLeft(rightOfRoot, 60);
        lbt.addLeft(leftOfRoot, 40);
        lbt.addRight(rightOfRightOfRoot, 80);


        // Print elements of tree in breadth first
        System.out.println("breadth first");
        Iterator<Position<Integer>> breadthfirstIterator = lbt.breadthfirst().iterator();
        while(breadthfirstIterator.hasNext()) {
            System.out.println(breadthfirstIterator.next().getElement());
        }

        /*
        Understanding how breadthfirst works.

        a) Complete this table for the entire breadthfirst for the above tree.
        Each row starting with the 2nd represents an iteration of breadthfirst's while loop.
        For each subsequent iteration, give the node visited and the contents of fringe and snapshot.

        Visit        fringe     snapshot
                     [10]         []
        10           ?            [10]
        ?            ?            ?
        ?            ?            ?
        ?            ?            ?


        b) Generalize. Describe what happens (what node is visited, what happens to fringe and snapshot)
            in one iteration of breadthfirst.

        c) Define "fringe" (as it pertains to tree traversals) in your own words.

         */
    }
}
