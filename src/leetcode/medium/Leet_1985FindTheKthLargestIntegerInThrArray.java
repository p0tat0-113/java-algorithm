package leetcode.medium;

import java.util.Arrays;
import java.util.Random;

/*
215번 문제랑 거의 비슷한 문제임. quick select알고리즘 문제다.
근데 차이점이라면 숫자들이 문자열로 저장되어있음. 그냥 다 Integer로 변환해서 선택알고리즘을 돌리는게 나을 듯 하다.

런타임 에러가 발생함. int형 범위보다 큰 숫자가 들어온게 문제임. 문제 조건을 제대로 안읽었다.... 숫자들이 문자열로 저장되어 있다는 점에 유의를 했어야했다. 조건이 아주 괴랄함.
1 <= k <= nums.length <= 10^4
1 <= nums[i].length <= 100
nums[i] consists of only digits.
nums[i] will not have any leading zeros.
각 문자의 길이가 최대 100이다. 이건 숫자로 바꿔서 하는 식으로는 감당이 안됨. 잠깐만 아니면 BigInteger라는 것도 있다는데 이걸 쓸까...?
원래는 길이가 다른 문자열은 길이가 긴 것이 무조건 크고, 길이가 같은 문자열은 compareTo()로 사전순으로 비교하려고 했음. <- 쓰다보니까 이 방식이 더 좋아보이는데, 이 방식은 179번 largestNumber문제를 풀면서 체득함.

이렇게 하니까 완벽하게 해결됐음!!! 수행시간에서 6ms로 99.31%를 땄고, 메모리에서 96%를 땄다!!!!
*/

public class Leet_1985FindTheKthLargestIntegerInThrArray {
    public static void main(String[] args) {
        //System.out.println("24".compareTo("25"));//문자열로 저장된 숫자의 크기를 이런식으로 비교하는 것은 불가능. Integer로 변환해서 비교해야 한다.
        //String[] arr = {"3", "6", "7", "10"};
        String[] arr = {"623986800","3","887298","695","794","6888794705","269409","59930972","723091307","726368","8028385786","378585"};
        System.out.println(kthLargestNumber(arr, 4));
    }

    public static String kthLargestNumber(String[] nums, int k) {
        Random random = new Random();
        return quickSelect(nums,0,nums.length-1,nums.length-k+1, random);
    }

    private static String quickSelect(String[] arr, int p, int r, int i, Random random){
        if (p == r) {//p와 r이 같아지면 그대로 이 인덱스의 숫자를 반환함. 배열에 i번째 숫자가 없는 경우는 고려하지 않음.
            return arr[p];
        }

        int randomIdx = random.nextInt(p,r+1);//pivot을 랜덤으로 뽑아서 정렬된 배열이 들어와서 0:n-1로 분할되는 최악의 경우를 피한다.
        String temp = arr[randomIdx];
        arr[randomIdx] = arr[p];
        arr[p] = temp;

        int q = partition(arr, p, r);
        int k = q - p + 1;//pivot은 배열내에서 k번째로 작은 수다.

        if (i < k) {
            return quickSelect(arr,p,q-1,i,random);//왼쪽 그룹 탐색, 찾고자 하는 수는 왼쪽 그룹에서 i번째로 작은 수다.
        } else if (i == k) {
            return arr[q];
        } else {
            return quickSelect(arr,q+1,r,i-k,random);//오른쪽 그룹 탐색, 찾고자 하는 수는 오른쪽 그룹에서 i-k번째로 작은 수다.
        }
    }

    private static int partition(String[] arr, int p, int r){
        //내가 원래 하던 방식으로 분할하자.
        String pivot = arr[p];
        int low = p+1;
        int high = r;

        while (low <= high) {
            if (leftIsBigger(arr[low], pivot) < 0) {//pivot보다 arr[low]가 더 작다면 low전진
                low++;
            } else if (leftIsBigger(arr[high], pivot) > 0) {//pivot보다 arr[high]가 더 크다면 high전진
                high--;
            } else {//둘 다 움직이지 못하는 상황이면, 서로 교환하고 둘 다 한 칸씩 전진. pivot보다 작은 수는 왼쪽으로, 큰 수는 오른쪽으로 보내는 것이다.
                String temp = arr[low];
                arr[low] = arr[high];
                arr[high] = temp;
                low++;
                high--;
            }
        }

        arr[p] = arr[high];
        arr[high] = pivot;//pivot을 알맞은 위치로 이동

        return high;//pivot의 위치 인덱스를 반환한다.
    }

    private static int leftIsBigger(String s1, String s2){//왼쪽이 더 크면 양수, 둘이 같으면 0, 오른쪽이 더 크면 음수를 반환함.
        /*if (s1.length() != s2.length()) {//둘의 길이가 다르다면, 길이가 더 긴 쪽이 무조건 더 크다.
            return (s1.length() > s2.length()) ? 1 : -1;
        }

        return s1.compareTo(s2);//여기까지 오면 s1과 s2는 길이가 같은 것이기 때문에 그냥 사전식으로 비교하면 됨. 왼쪽이 더 크면 양수가 반환된다.*/

        //좀 더 축약하자면 이렇게 됨.
        return (s1.length() != s2.length()) ? s1.length() - s2.length() : s1.compareTo(s2);
    }
}
