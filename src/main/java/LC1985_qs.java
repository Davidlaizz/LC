import java.util.Arrays;

public class LC1985_qs {
    public static void main(String[] args) {
        // Scanner input=new Scanner(System.in);
        // String str=input.next();
        // String trim = str.substring(1, str.lenght - 1);
        int k = 2;
        int[] arr = new int[]{3, 2, 1, 5, 6, 4};
        int res = quickSelect(arr, 0, arr.length - 1, k);
        System.out.println(res);
    }

    public static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right) {
            return arr[left];
        }
        int p = partition(arr, left, right);
        if (p == arr.length - k ) {
            return arr[p];
        } else if (p < arr.length - k) {
            return quickSelect(arr, p + 1, right, k);
        } else {
            return quickSelect(arr, left, p - 1, k);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        // 选择右边作为基准
        int p = arr[right];
        System.out.println("基准：" + p);
        int i = left - 1;
        for (int j = left; j < right; j++) {
            //小于p放左边
            if (arr[j] <= p) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, right);
        System.out.println(Arrays.toString(arr));
        System.out.println("index " + (i + 1));
        return i + 1;
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
