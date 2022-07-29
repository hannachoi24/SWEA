package D4.그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Info {
    public int x;
    public int y;

    public Info(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class SWEA_1249_보급로 {
    public static int N, answer;
    public static int[][] map, memo; // memo: 중복 계산을 피하기 위해 input 결과를 기록하는 배열
    public static int[] dx = { -1, 1, 0, 0 };
    public static int[] dy = { 0, 0, -1, 1 };
    public static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            memo = new int[N][N];
            visit = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                for (int j = 0; j < N; j++) {
                    map[i][j] = s.charAt(j) - '0';
                }
            }

            answer = Integer.MAX_VALUE;

            bfs();

            System.out.printf("#%d %d\n", tc, answer);

        }
    }

    public static void bfs() {
        Queue<Info> q = new LinkedList<Info>();
        q.offer(new Info(0, 0));

        while (!q.isEmpty()) {
            Info info = q.poll();

            if (info.x == N - 1 && info.y == N - 1) {
                answer = (answer > memo[info.x][info.y] ? memo[info.x][info.y] : answer);
                continue;
            }

            if (answer <= memo[info.x][info.y])
                continue;

            for (int i = 0; i < 4; i++) {
                int nx = info.x + dx[i];
                int ny = info.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N)
                    continue;

                /*
                 * bfs를 이용해서 모든 경우를 살펴보면서 방문하지 않았거나 방문했는데 기존의 값보다 작은 값이 들어올 수 있다면 값을 갱신하고
                 * 마지막으로 도착지에 오는 경우가 여러개이므로 최소값을 갱신하게하여 해결
                 */
                if (!visit[nx][ny] || memo[info.x][info.y] + map[nx][ny] < memo[nx][ny]) {
                    visit[nx][ny] = true;
                    memo[nx][ny] = memo[info.x][info.y] + map[nx][ny];
                    q.offer(new Info(nx, ny));
                }
            }
        }
    }
}
