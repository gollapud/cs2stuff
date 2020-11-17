public class Quiz {
    public static void main(String[] args) {

        int b = 0;

        int x = 0;

        while (b < 3) {

            x += foo(2);

            x += bar(x);

            b += 1;

        }

        System.out.println(x);

    }

    public static int bar(int b) {

        return b + b;

    }

    public static int foo(int b) {

        return b * 4;

    }
}
