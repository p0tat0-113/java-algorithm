package leetcode.medium;

/*
arr1의 각 단어의 길이를 늘려가면서  arr2의 단어들의 앞 부분과 일치하는지 비교를 해야 함.
일단 max가 나오면 그 다음부터는 max보다 긴 길이로 비교를 해야 함.
*/

public class Leet_3043FindTheLengthOfTheLongestCommonPrefix {
    public static void main(String[] args) {

    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        String[] sArr1 = new String[arr1.length];
        String[] sArr2 = new String[arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            sArr1[i] = String.valueOf(arr1[i]);
        }
        for (int i = 0; i < arr2.length; i++) {
            sArr2[i] = String.valueOf(arr2[i]);
        }

        int max = 0;
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                while(sArr1[i].substring(0, max+1).equals(sArr2[j].substring(0,max+1)) && (max+1 < sArr1[i].length() && max+1 < sArr2[j].length())) {
                    max++;
                }
            }
        }

        return max;
    }
}
