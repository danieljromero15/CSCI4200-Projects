public class Question1 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(q1(i));
        }
    }

    public static String q1(int k) {
        int j = 0;

        if ((k == 1) || (k == 2)) {
            j = 2 * k - 1;
        } else if ((k == 3) || (k == 5)) {
            j = 3 * k + 1;
        } else if (k == 4) {
            j = 4 * k - 1;
        } else if ((k == 6) || (k == 7) || (k == 8)) {
            j = k - 2;
        }

        return "In: " + k + "; Out: " + j;
    }
}
