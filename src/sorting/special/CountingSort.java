package sorting.special;

/*계수 정렬, 원소들이 특정 범위를 넘지 않는 특수한 경우에 사용할 수 있음. 얘도 안정정렬이고(구현하기에 따라서 다름, 아주 약간의 차이), Θ(n)의 수행시간을 가진다.
* 별도의 카운팅 배열을 운용해서 각 원소가 각각 몇번 나왔는지 세고, 각 인덱스별로 구간합을 구함. 각 누적합은 그 숫자보다 작거나 같은 모든 숫자들의 갯수의 합을 가리킨다.
* 이 구간합을 이용해서 원소들을 알맞은 자리에 집어넣음.
*
* 나는 음수가 포함되어도 정렬할 수 있게 구현함.
* 값의 범위가 작을수록 정렬속도가 빠르다. 숫자는 몇개 없는데, 값의 범위만 크면 카운터 배열만 겁나게 커져야 하기 때문에 비효율적이다.
*/


import java.util.Arrays;

public class CountingSort {
    public static void main(String[] args) {
        CountingSort countingSort = new CountingSort();

        int[] arr = {-1,1,0,2,5,2,3,5};
        int[] ints = countingSort.countingSort(arr);
        System.out.println(Arrays.toString(ints));
    }

    public int[] countingSort(int[] arr){
        int[] result = getCountArrLengthAndCali(arr);
        int[] countArr = new int[result[0]];
        int cali = result[1];

        //각 숫자의 출현횟수를 셈.
        for (int i : arr) {
            countArr[i+cali] = countArr[i+cali]+1;
        }

        //누적합을 구함. 각 인덱스에는 나보다 작거나 같은 숫자들이 몇개가 있는지가 들어감.
        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i] + countArr[i-1];
        }

        //구간합에 맞춰서 각 숫자들을 집어넣음. 이때 안정적으로 정렬되게끔 해야 함.
        int[] sortedArr = new int[arr.length];
        for (int i = arr.length-1; i >= 0; i--) {
            sortedArr[ countArr[arr[i]+cali] - 1 ] = arr[i]; //countArr[arr[i]+cali]해당 숫자의 countArr 누적합을 구한다. cali는 음수 때문에 더해주는 보정값이다.
            countArr[arr[i]+cali] = countArr[arr[i]+cali]-1; //다음에 똑같은 숫자가 올 것에 대비해서 누적합을 하나 줄임.
        }
        return sortedArr;
    }

    private int[] getCountArrLengthAndCali(int[] arr){
        int max = arr[0];
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            } else if (min > arr[i]) {
                min = arr[i];
            }
        }

        int length = max - min + 2;
        int cali = 0;
        if (min < 0){//최소값이 음수라면 calibration값을 구해줘야 함.
            cali = Math.abs(min);
        }

        return new int[]{length, cali};
    }
}
