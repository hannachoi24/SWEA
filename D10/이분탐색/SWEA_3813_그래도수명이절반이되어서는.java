package D10.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_3813_그래도수명이절반이되어서는 {
    static final int MAX_VALUE = 200000;
    static int[] W, S; // W: Wear Level 값 저장, S: 덩어리 수 저장
    static int N, K, answer; // N: 블록 수, K: 초기 데이터의 덩어리 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        W = new int[MAX_VALUE + 1];
        S = new int[MAX_VALUE + 1];

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                W[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                S[i] = Integer.parseInt(st.nextToken());
            }

            answer = MAX_VALUE;

            parametricSearch();

            // 정답 출력
            System.out.println("#" + tc + " " + answer);
        }
    }

    /*
     * 차지한 데이터 덩어리의 Wear Level을 기준값(mid)로 정해서
     * 데이터를 실제로 놓을 수 있는지 확인하며 최적의 기준값(mid)를 찾아갑니다.
     */
    private static void parametricSearch() {
        int left = 1;
        int right = MAX_VALUE; // 200000

        while (left < right) {
            int mid = (left + right) / 2;

            if (isPossible(mid)) {
                right = mid;
                answer = right;
            } else {
                left = mid + 1;
            }
        }
    }

    /*
     * mid 값 만족여부는 해당 문제에서는 데이터 덩어리들이
     * 각각 mid값 이하의 공간을 차지할 수 있는지이다.
     */
    private static boolean isPossible(int mid) {
        int data = 1; // 첫번째 데이터부터 확인
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            if (W[i] <= mid) {
                cnt++;
            } else {
                cnt = 0;
            }

            // 데이터 덩어리 크기를 만족하는지 확인
            if (cnt == S[data]) {
                data++;
                cnt = 0;
                // 모든 데이터 덩어리를 만족하였는지 확인
                if (data > K) {
                    return true;
                }
            }
        }
        return false;
    }
}

/*
 * 완전 탐색으로 접근시 O(N2)으로 TLE 발생
 * Parametric Search 이용해서 일반 문제를 결정 문제로 전환
 * ▶ 차지한 데이터 덩어리의 Wear Level을 기준값(mid)로 정해서
 * 데이터를 실제로 놓을 수 있는지 확인하며 최적의 기준값(mid)를 찾아갑니다.
 * [탐색 과정] ← 이분 탐색
 * 
 * 최적의 해 『6』 을 찾아가는 과정 (6이상의 Wear Level을 가질 수 있습니다.)
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14
 * X X X X X O O O O O O O O O
 * 
 * ① (left = 1, right = 14) → mid = 7 ▶ 만족 O
 * 
 * ② (left = 1, right = 7) → mid = 4 ▶ 만족 X
 * 
 * ③ (left = 5, right = 7) → mid = 6 ▶ 만족 O
 * 
 * ④ (left = 5, right = 6) → mid = 5 ▶ 만족 X
 * 
 * ⑤ (left = 6, right = 6) → left ≥ right이므로 지금까지 가장 최적의 해 = 『 6 』
 * 
 * mid 값 만족여부는 해당 문제에서는 데이터 덩어리들이
 * 각각 mid값 이하의 공간을 차지할 수 있는지 입니다.
 */