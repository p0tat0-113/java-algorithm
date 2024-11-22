package leetcode.review.mid;

import java.util.Comparator;

public class Leet_179LargestNumber {
    public static void main(String[] args) {
        Leet_179LargestNumber leet = new Leet_179LargestNumber();
        System.out.println(leet.largestNumber(new int[]{10,2}));
        System.out.println(leet.largestNumber(new int[]{3,30,34,5,9}));
        System.out.println(leet.largestNumber(new int[]{0,0,0,0}));
        System.out.println(leet.largestNumber(new int[]{1,0,0,0}));
    }

    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];

        boolean notZero = false;//[0,0,0,0,0]이 들어오는 경우 "00000"이 출력되는 것을 막기 위한 플래그 변수

        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
            if (nums[i] != 0) {
                notZero = true;
            }
        }

        if (!notZero) {
            return "0";
        }

        mergeSort(strings, 0, strings.length - 1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {//문자열 조합의 크기를 비교하기위해 Comparator를 구현함.
                String str1 = o1+o2;
                String str2 = o2+o1;
                return str1.compareTo(str2);
            }
        });

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
        String[] temp = new String[e - s + 1];
        int tempLength = 0;
        int left = s;
        int right = mid+1;

        while(left <= mid && right <= e) {
            if (comparator.compare(strings[left],strings[right]) > 0) {//왼쪽이 더 작다.
                temp[tempLength++] = strings[left++];
            } else {
                temp[tempLength++] = strings[right++];
            }
        }

        while(left <= mid){
            temp[tempLength++] = strings[left++];
        }

        while(right <= e){
            temp[tempLength++] = strings[right++];
        }

        System.arraycopy(temp,0,strings,s,tempLength);
    }
}
