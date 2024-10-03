package easyAlgorithm.selection;

//최악의 경우에도 선형시간을 보장하는 선택 알고리즘.

import java.util.Arrays;

public class LinearSelect {
    // 테스트 예제
    public static void main(String[] args) {
        //int[] arr = {12, 3, 5, 7, 4, 19, 26, 1, 2, 8, 15, 6, 9, 10, 11, 14, 13, 16, 17, 18, 20};
        int[] arr = {1,2,3,4,5};
        int i = 4; // 10번째 작은 요소
        int result = linearSelect(arr, 0, arr.length - 1, i);
        System.out.println(i + "번째 작은 요소는: " + result);
    }

    // LinearSelect 함수: A 배열에서 p부터 r까지의 범위에서 i번째 작은 원소를 찾음
    public static int linearSelect(int[] A, int p, int r, int i) {
        // 1. 기본 사례: 원소의 수가 5개 이하인 경우에는 그냥 정렬해서 i번째 원소를 반환한다.
        if (r - p + 1 <= 5) {
            Arrays.sort(A, p, r + 1);
            return A[p + i - 1];
        }

        // 2. 그룹화: 전체 원소를 5개씩의 그룹으로 나눔
        int numGroups = (int) Math.ceil((double) (r - p + 1) / 5);
        int[] medians = new int[numGroups];

        for (int g = 0; g < numGroups; g++) {
            int groupStart = p + g * 5;
            int groupEnd = Math.min(groupStart + 4, r);

            // 3. 각 그룹에서 중앙값을 찾음
            int median = findMedian(A, groupStart, groupEnd);
            medians[g] = median;
        }

        // 4. 중앙값들의 중앙값을 피벗으로 선택 <- 최대한 올바른(중앙에 가까운) 피벗을 뽑기위한 사전작업을 거쳐서 뽑은 것이다.
        int pivot;
        if (numGroups == 1) {
            pivot = medians[0];
        } else {
            pivot = linearSelect(medians, 0, numGroups - 1, (numGroups + 1) / 2);//중앙값들 중의 중앙값을 찾기위해 linearSelect를 재귀호출한다.
            //이때 인수로 medians를 넘기고, 찾고자 하는 k번째 작은 수는 정중앙인 (numGroups + 1) / 2 번째로 작은 수다.
        }

        // 5. 피벗을 기준으로 배열을 분할
        int pivotIndex = partition(A, p, r, pivot);

        // 피벗의 순위를 계산 <- 피벗이 부분배열 내에서 몇 번째로 작은 숫자인지를 계산
        int rank = pivotIndex - p + 1;

        // 6. 원하는 순위에 따라 재귀 호출
        if (i == rank) {
            return A[pivotIndex];
        } else if (i < rank) {
            return linearSelect(A, p, pivotIndex - 1, i);
        } else {
            return linearSelect(A, pivotIndex + 1, r, i - rank);
        }
    }

    // 그룹 내 중앙값 찾기
    private static int findMedian(int[] A, int left, int right) {
        Arrays.sort(A, left, right + 1);
        int mid = left + (right - left) / 2;
        return A[mid];
    }

    // 분할 함수: 피벗 값을 기준으로 배열을 분할하고 피벗의 최종 위치를 반환
    private static int partition(int[] A, int left, int right, int pivot) {
        // 피벗을 오른쪽 끝으로 이동, 지금 이렇게 하는 이유는 중앙값 중의 중앙값만 알 뿐이지 걔의 인덱스는 모르기 때문이다. 그리고 걔가 맨 앞이나 맨 뒤 고정된 위치에 있는 것도 아님ㅋㅋ
        int pivotIndex = -1;
        for (int i = left; i <= right; i++) {
            if (A[i] == pivot) {
                pivotIndex = i;
                break;
            }
        }
        if (pivotIndex == -1) {
            throw new IllegalArgumentException("피벗이 배열에 존재하지 않습니다.");
        }
        swap(A, pivotIndex, right);
        pivotIndex = right;//중앙값 중의 중앙값을 맨 뒤로 옮겼음. pivotIndex는 부분배열의 맨 끝 인덱스

        // 피벗을 기준으로 분할, 이 부분부터는 내가 아는 일반적인 partition함수랑 똑같다.
        int storeIndex = left;
        for (int i = left; i < right; i++) {//오 left를 이렇게 for문에 넣어버리는 것도 좋은 방법인 듯.
            if (A[i] < pivot) {
                swap(A, storeIndex, i);
                storeIndex++;
            }
        }
        swap(A, storeIndex, right);
        return storeIndex;
    }

    // 배열 내 두 요소의 위치를 교환
    private static void swap(int[] A, int i, int j) {
        if(i == j) return;
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
