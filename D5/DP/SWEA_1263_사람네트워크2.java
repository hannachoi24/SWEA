package D5.DP;

import java.util.*;

public class SWEA_1263_사람네트워크2 {
    static final int INF = 9999999;
    static int[][] adjMatrix;

    public static void main(String[] args) {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st = new StringTokenizer(br.readLine());
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int n = sc.nextInt();

            adjMatrix = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    adjMatrix[i][j] = sc.nextInt();

                    if (i != j && adjMatrix[i][j] == 0)
                        adjMatrix[i][j] = INF;
                }
            }

            for (int k = 0; k < n; ++k) {
                for (int i = 0; i < n; ++i) {
                    if (i == k)
                        continue;
                    for (int j = 0; j < n; ++j) {
                        if (i == j || k == j)
                            continue;
                        if (adjMatrix[i][j] > adjMatrix[i][k] + adjMatrix[k][j]) {
                            adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
                        }
                    }
                }
            }
            int min = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                int sum = 0;
                for (int j = 0; j < n; j++) {
                    sum += adjMatrix[i][j];
                }
                min = Math.min(min, sum);
            }

            System.out.printf("#%d %d\n", tc, min);
        }
    }
}

/*
 * 이 문제는 플로이드 워셜 알고리즘으로 풀었다.
 * 플로이드 워셜 알고리즘은 출발지 -> 경유지 -> 도착지 순으로 풀거나 경유지 -> 출발지 -> 도착지 순으로 풀면 된다.
 * 인접행렬을 구하여 경유지를 거칠 경우에 인접행렬을 계속 갱신해준다.
 * 단 번에 가는것이 경유지(K)를 거치는 것보다 크다면 인접행렬 i,j 위치에 경유지를 거친 값을 넣는다.
 * 이 과정이 끝난 인접행렬에서 각 행마다의 값을 찾아서 최소값의 CC를 찾으면 된다.
 */