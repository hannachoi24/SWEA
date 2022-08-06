package D9.분할정복;

import java.util.*;

public class SWEA_1247_최적경로 {
    static int N;
    static int min;

    public static void Perm(int num, int[][] map, int[] result, boolean[] check) {
        // 집 도착
        if (num == N + 1) {
            int sum = 0;
            // 최소 거리의 합 계산
            for (int i = 0; i < N + 1; i++) {
                sum += Math.abs(map[result[i]][0] - map[result[i + 1]][0])
                        + Math.abs(map[result[i]][1] - map[result[i + 1]][1]);
            }

            // 가지치기 -> 실행 시간 줄일 수 있음
            if (sum < min)
                min = sum;
            return;
        }

        // 집
        result[0] = 0;
        check[0] = true;

        // 회사
        result[N + 1] = N + 1;
        check[N + 1] = true;

        // 순열 생성
        for (int i = 1; i < N + 1; i++) {
            if (check[i])
                continue;
            result[num] = i;
            check[i] = true;
            Perm(num + 1, map, result, check);
            check[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt(); // 고객의 수

            int[][] map = new int[N + 2][2]; // 좌표 저장 배열
            boolean[] check = new boolean[N + 2];
            int[] result = new int[N + 2]; // 뽑는 결과

            min = Integer.MAX_VALUE;

            // 집 좌표
            map[0][0] = sc.nextInt();
            map[0][1] = sc.nextInt();

            // 회사 좌표
            map[N + 1][0] = sc.nextInt();
            map[N + 1][1] = sc.nextInt();

            // 고객의 좌표 입력 받아서 1 ~ N까지 입력
            for (int i = 1; i <= N; i++) {
                map[i][0] = sc.nextInt();
                map[i][1] = sc.nextInt();
            }

            Perm(1, map, result, check);
            System.out.printf("#%d %d\n", tc, min);
        }
        sc.close();
    }
}
