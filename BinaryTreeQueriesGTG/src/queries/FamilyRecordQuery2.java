
package queries;

import iterators.FoldFunction;
import net.datastructures.AbstractTree;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.LinkedQueue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class FamilyRecordQuery2 {

    public static void main(String[] args) throws IOException {
        Iterator<FamilyRecord> records = FamilyRecordTools.records("familyrecord.csv");

        LinkedBinaryTree<String> treeOfNames = new LinkedBinaryTree<>();
        while (records.hasNext()) {
            FamilyRecord record = records.next();
            treeOfNames.insertNode(record.name);
        }

        System.out.println("Family Tree:\n" + AbstractTree.toList(treeOfNames.breadthfirst()));


        int generationNumberA = 3;
        String ageGroupB = treeOfNames.foldAtDepthRecursive(generationNumberA, new ConcatentateNames());
        System.out.println("Generation " + generationNumberA + ": " + ageGroupB);
        int generationNumberB = 4;
        String ageGroupA = treeOfNames.foldAtDepthIterative(generationNumberB, new ConcatentateNames());
        System.out.println("Generation " + generationNumberB + ": " + ageGroupA);

        int generationNumberC = 2;
        String ageGroupC = treeOfNames.foldAtDepthRecursive(generationNumberC, new ConcatentateNames());
        System.out.println("Generation " + generationNumberC + ": " + ageGroupC);
    }


    private static class ConcatentateNames implements FoldFunction<String, String> {
        @Override
        public String combine(String soFar, String x) {
            return !soFar.isEmpty()? soFar + " " + x : x;
        }

        @Override
        public String initialValue() {
            return "";
        }
    }
}


