import java.util.Arrays;

public class ExpressionUtil {

    public void printPretty(int [] arr) {
        printLikeMatrix(arr, 1);
    }

    public void printPretty(String[] arr) {
        printLikeMatrix(arr, 1);
    }

    public void printLikeMatrix(int[] arr, int cols) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("array is null or empty");
        if (cols <= 0) throw new IllegalArgumentException("cols must be greater than 0");

        int maxDigit =  (int) Math.log10(Arrays.stream(arr).max().getAsInt()) + 1;
        char[] line = new char[maxDigit];
        Arrays.fill(line, ' ');
        int col = 1;
        StringBuilder sb = new StringBuilder();

        for (int j : arr) {

            char[] dummy = Arrays.copyOfRange(line, 0, maxDigit);
            int digit = (int) Math.log10(j) + 1;
            if (j == 0) digit = 1;
            String str = String.valueOf(j);
            for (int now = maxDigit - 1, strDigit = 1; now >= 0 && strDigit <= digit; now--, strDigit++) {
                dummy[now] = str.charAt(str.length() - strDigit);
            }

            if (col == cols) {
                sb.append(new String(dummy)).append('\n');
                col = 1;
            } else {
                sb.append(new String(dummy)).append(' ');
                col++;
            }
        }
        System.out.println(sb);
    }

    public void printLikeMatrix(String[] arr, int cols) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("array is null or empty");
        if (cols <= 0) throw new IllegalArgumentException("cols must be greater than 0");

        int max = 0;
        for (String s : arr) {
            if (s.length() > max) max = s.length();
        }

        int col = 1;
        StringBuilder sb = new StringBuilder();

        for (String s : arr) {

            int digit = s.length();
            int diff = max - digit;
            char[] dummy = new char[diff];
            Arrays.fill(dummy, ' ');

            if (col == cols) {
                sb.append(new String(dummy)).append(s).append('\n');
                col = 1;
            } else {
                sb.append(new String(dummy)).append(s).append(' ');
                col++;
            }
        }
        System.out.println(sb);
    }

    public int[] changeBaseUnderTen(int[] arr, int b) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("array is null or empty");
        if (b > 10 || b < 1) throw new IllegalArgumentException("b > 10 || b < 1");
        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            String str = Integer.toString(arr[i], b);
            result[i] = Integer.parseInt(str);
        }
        return result;
    }

    public String[] changeBaseOverTen(int[] arr, int b) {
        if (arr == null || arr.length == 0) throw new IllegalArgumentException("array is null or empty");
        if (b <= 10 || b > 36) throw new IllegalArgumentException("b <= 10 || b > 36");
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = Integer.toString(arr[i], b);
        }
        return result;
    }
}
