package easyAlgorithm.sorting.advanced;

/*힙 정렬 구현 최악의 경우 세타 nlogn, 모든 원소가 동일한 값이라던가 해서 운이 좋으면 n의 시간복잡도를 보임. 이론적 수행시간으로는 완벽한 알고리즘인데
* 실제로 돌려보면 퀵소트한테 따잇 당함.
*
* 힙 정렬 과정을 간단하게 요약하자면
* 완전 이진 트리(모든 배열은 완전 이진 트리로 해석할 수 있음)에서 힙 구조로 수선한다. 수선과정에서 스며내리기 알고리즘을 사용한다.
* 그 후 힙의 루트, 즉 최대값을 맨 뒤로 보내고, 다시 스며내리기를 하는 과정을 반복해서 그 다음으로 큰 값이 루트 자리에 올라오게 한다.
* 이 과정을 계속 반복해서 배열을 정렬함.*/


import java.util.Arrays;

public class HeapSort implements AdvancedSort {
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();

        int[] arr = {4,1,8,7,3,3,5,2,9};

        /*heapSort.buildHeap(arr);
        System.out.println(Arrays.toString(arr));*/

        heapSort.sort(arr,0,0);
        System.out.println(Arrays.toString(arr));
    }

    @Override
    public void sort(int[] arr, int s, int e) {//s,e는 사용하지 않음.
        //우선 입력 받은 배열, 완전 이진 트리(모든 배열은 완전이진트리로 치환될 수 있음)를 힙 구조로 수선한다.
        buildHeap(arr);

        //deleteMax()를 호출하는 쪽에서 length를 하나씩 줄여주면서 각 반복시의 리프노드 자리를 관심에서 제외시킨다.
        for (int length = arr.length-1; length > 0; length--) {
            deleteMax(arr,length);
        }
    }

    //힙에서 최대값을 힙의 맨 뒤로 보내는 메서드,
    //현재 힙은 최대값이 루트인 상태이다.
    //루트를 리프노드(가장 말단 노드)와 맞바꾸고, 리프노드 자리를 관심에서 제외,스며내리기를 한다(rootIdx는 0으로 함). 이러면 그 다음으로 큰 값이 루트로 올라오게 된다. 이것을 계속 반복함.
    private void deleteMax(int[] arr, int length){
        int temp = arr[0];
        arr[0] = arr[length];
        arr[length] = temp;

        percolateDown(arr,0,length);
    }

    //완전 이진 트리 구조를 힙 구조로 수선함.
    private void buildHeap(int[] arr){
        //가장 말단 서브트리의 root부터 반복문을 돌린다.
        for (int rootIdx = (arr.length-2)/2; rootIdx >= 0; rootIdx--) {//(arr.length-2)/2를 한 이유는 노드A[k]의 부모는 노드A[(k-1)/2]이기 때문이다.
            percolateDown(arr,rootIdx,arr.length);
        }
    }

    //서브 트리를 힙 구조로 수선함. 스며내리기 알고리즘.
    private void percolateDown(int[] arr, int rootIdx, int length) {//root는 root의 인덱스, length는 percolateDown이 인식하고 있는 힙의 크기
        int child = (2 * rootIdx) + 1; //왼쪽 자식의 인덱스, 힙 구조는 각 레벨을 꽉 채우고, 마지막 레벨의 경우 왼쪽부터 채우기 때문에 이런 공식을 사용할 수 있다.
        int right = (2 * rootIdx) + 2; //오른쪽 자식의 인덱스

        if (child <= (length - 1)) {//왼쪽 자식을 가리키는 인덱스가 유효하다면(왼쪽 자식이 실제로 존재한다면), 왼쪽이 유효하지 않으면 오른쪽도 당연히 유효하지 않음.
            if (right <= (length - 1)) {//오른쪽 자식을 가리키는 인덱스가 유효하다면
                if (arr[child] < arr[right]){
                    child = right;//왼쪽과 오른쪽 자식 중 더 큰 것을 골라냄.
                }
            }

            if (arr[rootIdx] < arr[child]){//루트가 자식 둘 중 큰 값보다 작다면 자식과 위치를 맞바꿈.
                int temp = arr[rootIdx];
                arr[rootIdx] = arr[child];
                arr[child] = temp;

                //이제 그 밑의 서브트리들도 다시 재귀적으로 힙 구조로 수선함.
                percolateDown(arr,child,length);
            }
        }
    }
}
