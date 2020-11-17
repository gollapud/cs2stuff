public class Problem2 {

    // print the numbers 1 thru 16,
    // except print the word BUZZ when the number is
    // a multiple of 3 or 5
    public static void main(String[] args) {
        int i;
        for(i=0; i<=16; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                System.out.println("BUZZ");
            }
            else {
                System.out.println(i);
            }
        }
    }
}

