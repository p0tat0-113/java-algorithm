package leetcode.medium;

/*
정렬된 배열에서 target숫자를 찾는 문제다. 이진탐색으로 찾으면 됨.
근데 배열이 회전했다.... 임의의 pivot 인덱스 k가 맨 앞으로 오도록 회전했음.
k (0 <= k < nums.length) such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].

처음에는 배열을 다시 정상적인 상태로 되돌려야되나 하고 고민했는데 좀 생각해보니까 그럴 필요가 없었다.
회전하기는 했지만 왼쪽과 오른쪽 각각의 부분배열은 여전히 정렬된 상태다. 그냥 둘로 나누어진 지점을 찾아서 각각에서 이진탐색을 돌리면 그만이다.

나누어진 지점을 어떻게 찾을지도 고민했는데, 그냥 단순하게 접근했다.
배열 전체를 순회해서 최대값과 최소값을 찾는다. 그리고 다시 배열을 순회하면서 최대값과 최소값이 바로 붙어있는 부분이 있는지 찾는다.
바로 붙어있는 부분이 있다면 그걸 기준으로 각각을 이진탐색을 돌리면 되고, 붙어있는 부분을 찾지 못했다면 배열이 회전되지 않은 상태(pivot 인덱스가 0)이므로 전체 배열을 이진탐색하면 된다.
이 부분은 leftStart...rightEnd변수를 잘 설정해놓았기 때문에 자연스럽게 돌아간다.
*/

public class Leet81_SearchInRotatedSortedArray2 {
    public static void main(String[] args) {
        //틀렸던 테스트 케이스
        //System.out.println(search(new int[]{2, 2, 2, 3, 2, 2, 2}, 3));
        //System.out.println(search(new int[]{3,1}, 1));
    }

    public static boolean search(int[] nums, int target) {
        /*if (nums.length == 1) {//길이가 1이면 target과 같은지만 확인하고 그대로 반환.
            return nums[0] == target;
        }*///이 부분은 없어도 코드 실행에 전혀 지장이 없지만, 이 부분을 빼니까 메모리 사용량이 늘어남...

        int leftStart = 0;
        int leftEnd = -1;
        int rightStart = 0;
        int rightEnd = nums.length-1;

        //ex) [0,0,0,0,0,4,4] -> [0,0,0,4,4,0,0]  [0,0,4,4,4,4,4] -> [4,4,4,0,0,4,4]
        //우선 최소값과 최대값을 각각 찾는다.
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {//실수로 i = 2로 해서 틀림.
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }

        for (int i = 0; i < nums.length-1; i++) {//실수로 nums.length-2로 해서 틀림.
            if (nums[i] == max && nums[i+1] == min) {//[4,4,4,0,0,4,4] 여기에서 4,0 이렇게 최대값-최소값이 연결되어 있는 부분을 찾는 것. 찾게되면 왼쪽과 오른쪽은 각각 정렬된 상태다.
                leftEnd = i;
                rightStart = i+1;
            }
        }
        //만약 여기서 연결부분을 찾지 못했다면 배열이 rotate되지 않은 상태인 것이다.(좀 더 정확히 말하자면 pivot의 인덱스가 0인 것)
        //그냥 전체배열을 이진탐색 돌리면 된다. 그렇게 되도록 leftStart...rightEnd변수 값을 설정해 놓았음.

        /*boolean left = binarySearch(nums, leftStart, leftEnd, target);
        boolean right = binarySearch(nums, rightStart, rightEnd, target);
        return left || right;*///디버깅용 코드

        return binarySearch(nums, leftStart, leftEnd, target) || binarySearch(nums, rightStart, rightEnd, target);//왼쪽과 오른쪽을 각각 이진탐색 돌려서 둘 중 하나라도 true면 true반환
    }

    //이진탐색 알고리즘.
    private static boolean binarySearch(int[] nums, int start, int end, int target){
        if(start <= end) {
            int mid = (start+end)/2;

            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < target) {
                return binarySearch(nums, mid+1, end, target);//return을 빼먹어서 틀림. return이 붙어있어야 return true를 한게 타고 올라간다.
            } else {
                return binarySearch(nums, start, mid-1, target);
            }
        }

        return false;
    }

}
