package D10.이분탐색;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/* 모든 위치값을 받은 후 비교하면 N*M으로 인해 시간초과가 발생한다.
최소값이 되기에 유망한 위치만 binSearch로 찾은 후 비교하여 해결하였다.*/

class SWEA_8898_3차원농부 {
    public static void main(String args[]) throws Exception {

        Scanner sc = new Scanner(System.in);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = sc.nextInt();
        int N, M;
        for (int test_case = 1; test_case <= T; test_case++) {
            N = sc.nextInt();
            M = sc.nextInt();
            int dx = Math.abs(sc.nextInt() - sc.nextInt());

            int[] cows = new int[N];
            for (int i = 0; i < N; i++) {
                cows[i] = sc.nextInt();
            }
            Arrays.sort(cows);

            int min = Integer.MAX_VALUE;
            int count = 0;

            for (int i = 0; i < M; i++) {
                int hPos = sc.nextInt();
                int cIdx = binSearch(cows, hPos);

                if (0 <= cIdx && cIdx < N) {
                    int cPos = cows[cIdx];
                    int dist = Math.abs(cPos - hPos);
                    if (min > dist) {
                        min = dist;
                        count = 1;
                    } else if (min == dist) {
                        count++;
                    }
                }
                // 이전 cows에 대해서 검사할 수 있을 때 검사
                if (0 < cIdx && cIdx < N && cows[cIdx] != hPos) {
                    int cPos = cows[cIdx - 1];
                    int dist = Math.abs(cPos - hPos);
                    if (min > dist) {
                        min = dist;
                        count = 1;
                    } else if (min == dist) {
                        count++;
                    }

                }
            }

            bw.write("#" + test_case + " " + (dx + min) + " " + count + "\n");
        }

        bw.flush();
        bw.close();

    }

    private static int binSearch(int[] arr, int value) {
        int left = 0;
        int right = arr.length - 1;
        int mid = (left + right) / 2;

        if (value < arr[left])
            return 0;
        if (arr[right] < value)
            return arr.length - 1;

        while (left <= right) {
            mid = (left + right) / 2;

            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (arr[mid] < value)
            mid++;

        return mid;
    }
}