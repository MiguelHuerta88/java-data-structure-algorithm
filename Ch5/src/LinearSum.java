public class LinearSum {
    public static int linearSearch(int[] data, int n) {
        if (n == 0) {
            return 0;
        } else {
            return linearSearch(data, n-1) + data[n -1];
        }
    }

    public static void main (String[] args) {
        int[] numbers = {20,40,11,2,5,63,212};
        System.out.print(LinearSum.linearSearch(numbers, 5));
    }
}
