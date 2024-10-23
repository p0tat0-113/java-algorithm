import java.util.Comparator;

public class Leet_179LargestNumber {
    public static void main(String[] args) {
        Leet_179LargestNumber leet = new Leet_179LargestNumber();
        System.out.println(leet.largestNumber(new int[]{10, 2}));
        System.out.println(leet.largestNumber(new int[]{3,30,34,5,9}));
    }

    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < strings.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }

        mergeSort(strings, 0, strings.length - 1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1+o2;
                String s2 = o2+o1;

                if (s1.length() != s2.length()) {
                    return Integer.compare(s1.length(), s2.length());
                }
                return s1.compareTo(s2);
            }
        });

        if (strings[0].equals("0")) {
            return "0";
        }

        return String.join("",strings);
    }

    private void mergeSort(String[] strings, int s, int e, Comparator<String> comparator){
        if (s < e) {
            int mid = (s+e)/2;
            mergeSort(strings, s, mid, comparator);
            mergeSort(strings, mid+1, e, comparator);

            merge(strings, s, mid, e, comparator);
        }
    }

    private void merge(String[] strings, int s, int mid, int e, Comparator<String> comparator){
        String[] tempArr = new String[e - s + 1];
        int tempArrLength = 0;
        int left = s;
        int right = mid+1;

        while (left <= mid && right <= e) {
            if (comparator.compare(strings[left],strings[right]) >= 0) {
                tempArr[tempArrLength++] = strings[left++];
            } else {
                tempArr[tempArrLength++] = strings[right++];
            }
        }

        while (left <= mid) {
            tempArr[tempArrLength++] = strings[left++];
        }
        while (right <= e) {
            tempArr[tempArrLength++] = strings[right++];
        }

        System.arraycopy(tempArr, 0, strings, s, tempArrLength);
    }
}
