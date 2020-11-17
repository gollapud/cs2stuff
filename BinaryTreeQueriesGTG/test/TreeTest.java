
import iterators.Predicate;
import iterators.FoldFunction;
import java.util.List;

import net.datastructures.AbstractTree;
import net.datastructures.LinkedBinaryTree;
import org.junit.Test;
import static org.junit.Assert.*;

public class TreeTest {

		public TreeTest() {
		}

		@Test
		public void testInsertionAndtoArray() {
				LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				Object[] actual = AbstractTree.toList(bt.breadthfirst()).toArray();
				Object[] expected = {50, 2, 34, 19, 6, 22};
				assertArrayEquals(expected, actual);
		}

		@Test
		public void reduceAtDepthTest() {
				LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				int sum = bt.foldAtDepthIterative(2, new IntegerSum());
				assertEquals(47, sum);
		}

		@Test
		public void reduceAtDepthTest2() {
				LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				int sum = bt.foldAtDepthRecursive(2, new IntegerSum());
				assertEquals(47, sum);
		}

		@Test
		public void filterIterativeTest() {
				LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
				bt.insertNode(50);
				bt.insertNode(2);
				bt.insertNode(34);
				bt.insertNode(19);
				bt.insertNode(6);
				bt.insertNode(22);
				Integer[] expected = {50,2,6,34,22};
				List<Integer> answers = bt.filterIterative(new IsEven());
				assertArrayEquals(expected, answers.toArray());
		}

//		 Example implementation of FoldFunction used by the given test
		private static class IntegerSum implements FoldFunction<Integer, Integer> {
				@Override
				public Integer combine(Integer soFar, Integer x) {
						return soFar+x;
				}

				@Override
				public Integer initialValue() {
						return 0;
				}
		}

		// Example implementation of IsEven used by the given test
		private static class IsEven implements Predicate<Integer> {
				@Override
				public boolean check(Integer data) {
						return data % 2 == 0;
				}
		}

		/* The staff will run your code on several additional JUnit tests of our own.
		   You must write additional tests below to provide more evidence that your
		   method implementations are correct.

		   This test code is part of the assignment, just like the other code.

		   If you write a new test and it fails, GREAT! That means you are making
		   progress. Either the test is wrong and you just need to fix it, or it means you found
		   a bug in your LinkedBinaryTree code and now you can go fix it. Don't remove good tests just
		   because they fail.
		 */

        // write your new tests below here, using the @Test methods above as an example.
		// PART 2: testing

		// PART 4: testing
}
