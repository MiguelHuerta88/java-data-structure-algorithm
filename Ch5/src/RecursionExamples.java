public class RecursionExamples {
    public static void reverseArray(int[] data, int low, int high) {
        if (low < high) {
            int temp = data[low];
            data[low] = data[high];
            data[high] = temp;
            reverseArray(data, low + 1, high - 1);
        }
    }

    public static double power(double x, int n) {
        if (n == 0){
            return 1;
        } else {
            return power(x, n -1) * x;
        }
    }

    /**
     * Power function using partials
     * @param x
     * @param n
     * @return
     */
    public static double power2(double x, int n) {
        if (n == 0) {
            return 1;
        } else {
            double partial = power2(x, n/2);
            double result = partial * partial;
            if (n % 2 == 1) {
                result *= x;
            }
            return result;
        }
    }

    public static int binarySum(int[] data, int low, int high) {
        if (low > high) {
            return 0;
        } else if (low == high) {
            return data[low];
        } else {
            int mid = (low + high) / 2;
            return binarySum(data, low, mid) + binarySum(data, mid + 1, high);
        }
    }
}
