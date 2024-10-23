import java.util.Arrays;
import java.util.Random;

public class ArrayMergeSort {
    public static void main(String[] args) {
        ArrayMergeSort leet = new ArrayMergeSort();

        int[] arr1 = {2, 1, 5, 3, 4};
        leet.mergeSort(arr1, 0, arr1.length-1);
        System.out.println(Arrays.toString(arr1));

        Random random = new Random();
        int[] arr2 = new int[100];
        for (int i = 0; i < 100; i++) {
            arr2[i] = random.nextInt(1,101);
        }
        System.out.println(Arrays.toString(arr2));
        leet.mergeSort(arr2,0, arr2.length-1);
        System.out.println(Arrays.toString(arr2));
    }

    private void mergeSort(int[] arr, int s, int e){
        if (s < e) {
            int mid = (s+e)/2;

            mergeSort(arr, s, mid);
            mergeSort(arr, mid+1, e);

            merge(arr, s, mid, e);
        }
    }

    private void merge(int[] arr, int s, int mid, int e){
        int[] tempArr = new int[e - s + 1];
        int tempArrLength = 0;

        int left = s;
        int right = mid+1;

        while (left <= mid && right <= e) {
            if (arr[left] < arr[right]) {
                tempArr[tempArrLength++] = arr[left++];
            } else {
                tempArr[tempArrLength++] = arr[right++];
            }
        }

        while (left <= mid) {
            tempArr[tempArrLength++] = arr[left++];
        }
        while (right <= e) {
            tempArr[tempArrLength++] = arr[right++];
        }

        System.arraycopy(tempArr, 0, arr, s, tempArrLength);
    }
}
