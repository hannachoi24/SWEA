package D5.DP;

import java.util.*;

public class SWEA_3304_최장공통부분수열 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            char[] X = sc.next().toCharArray();
            char[] Y = sc.next().toCharArray();

            // 각 문자열의 길이가 다르므로 따로 저장해둔다.
            int m = X.length;
            int n = Y.length;

            // 각 문자의 비교가 끝났을 때, 해당 위치에서 가질 수 있는 LCS의 값을 저장할 2차원 dp테이블을 정의한다.
            // 첫 행에서도 이전 문자를 참고할 수 있도록 패딩을 준다.
            int dp[][] = new int[m + 1][n + 1];

            // Y의 모든 문자열을 X문자열과 비교
            for (int i = 0; i <= m; i++) {
                for (int j = 0; j <= n; j++) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 0;
                        continue;
                    }
                    // 만일 두 문자가 같은 경우 (해당 위치에서 문자가 일치하는 경우)
                    if (X[i - 1] == Y[j - 1]) {
                        // 대각선의 값을 참고하여 LCS의 값을 + 1한다.
                        // 대각선 숫자 + 1
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        continue;
                    }
                    // 두 문자가 다른 경우 각 문자열의 이전 문자 중 최대 LCS값을 선택(해당 위치에서 문자가 일치하지 않는 경우)
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
            System.out.format("#%d %d\n", tc, dp[m][n]);
        }
        sc.close();
    }
}
