import java.util.Arrays;
import java.util.Random;

public class ArrayQuickSort {
    public static void main(String[] args) {
        ArrayQuickSort leet = new ArrayQuickSort();

        int[] arr1 = {2, 4, 5, 3, 1};
        leet.quickSort(arr1, 0, arr1.length-1);
        System.out.println(Arrays.toString(arr1));

        Random random = new Random();
        int[] arr2 = new int[10];
        for (int i = 0; i < 10; i++) {
            arr2[i] = random.nextInt(1,101);
        }
        leet.quickSort(arr2, 0, arr2.length-1);
        System.out.println(Arrays.toString(arr2));
    }

    private void quickSort(int[] arr, int s, int e){
        if (s < e) {
            Random random = new Random();
            int randomIdx = random.nextInt(s, e+1);
            int temp = arr[s];
            arr[s] = arr[randomIdx];
            arr[randomIdx] = temp;

            int pivot = partition(arr, s, e);

            quickSort(arr, s, pivot-1);
            quickSort(arr, pivot+1, e);
        }
    }

    private int partition(int[] arr, int s, int e){
        int pivot = arr[s];//처음에 pivot = s;로 해서 안돌아감.

        int low = s+1;
        int high = e;

        while (low <= high) {
            if (arr[low] < pivot) {
                low++;
            } else if (arr[high] > pivot) {
                high--;
            } else {
                int temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
                low++;
                high--;
            }
        }

        arr[s] = arr[high];
        arr[high] = pivot;

        return high;
    }
}
