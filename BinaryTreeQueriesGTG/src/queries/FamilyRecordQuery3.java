
package queries;

import iterators.Predicate;
import net.datastructures.LinkedBinaryTree;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class FamilyRecordQuery3 {

    public static void main(String[] args) throws IOException {
        Iterator<FamilyRecord> records = FamilyRecordTools.records("familyrecord.csv");

        LinkedBinaryTree<FamilyRecord> treeOfRecords = new LinkedBinaryTree<>();
        while (records.hasNext()) {
            FamilyRecord record = records.next();
            treeOfRecords.insertNode(record);
        }


 /*       List<FamilyRecord> robertList = treeOfRecords.filterIterative(new SelectName("Roger"));
        System.out.println(robertList);

        List<FamilyRecord> engineerList = treeOfRecords.filterRecursive(new SelectJob("Engineer"));
        System.out.println(engineerList);
*/
    }


    /* UNCOMMENT and COMPLETE
    private static class SelectName implements Predicate<FamilyRecord> {

    }
    
    private static class SelectJob implements Predicate<FamilyRecord>{

    }
    */
}