public class Duplicates {

    public static void main(String[] args) {
        int[] arr1 = {2,4,6,8,10,12,14,16,18};
        if (!noDuplicates(arr1)) {
            System.out.println("FAILED TEST 1");
            System.exit(1);
        }
        int[] arr2 = {2,4,6,6,10,12,14,16,18};
        if (noDuplicates(arr2)) {
            System.out.println("FAILED TEST 2");
            System.exit(1);
        }
        int[] arr3 = {1,3,5,7,9,11,13,15,17,191};
        if (noDuplicates(arr3)) {
            System.out.println("FAILED TEST 3");
            System.exit(1);
        }
        int[] arr4 = {1,5,8,13,15,8,14,16,71};
        if (!noDuplicates(arr4)) {
            System.out.println("FAILED TEST 4");
            System.exit(1);
        }
        System.out.println("Tests passed");
    }


    public static boolean noDuplicates(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    return false;
                    }
                }
            }

        return true;
    }

}
