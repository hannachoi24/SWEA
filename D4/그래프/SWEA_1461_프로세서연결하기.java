package D4.그래프;

import java.io.*;
import java.util.*;

public class SWEA_1461_프로세서연결하기 {
    static class Core { // 코어 위치 정보 저장
        int y, x;

        public Core(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    // N : 셀크기, cell : 코어와 전선을 담을 맵, minWireCnt : 전선 최소값, maxCore : 코어 최대값
    private static int N, cell[][], minWireCnt, maxCore;
    private static int dx[] = { 0, 0, -1, 1 }; // 상 하 좌 우 델타
    private static int dy[] = { -1, 1, 0, 0 };
    private static List<Core> clist; // 코어 위치를 담을 리스트

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            N = Integer.parseInt(br.readLine());

            cell = new int[N][N];
            clist = new ArrayList<>();

            StringTokenizer st;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int in = Integer.parseInt(st.nextToken());
                    if (in == 1) { // 코어인 경우만 판단. 배열은 0 초기화이기 때문에
                        cell[i][j] = in;
                        // 가장자리에 있는 코어라면
                        if (i == 0 || j == 0 || i == N - 1 || j == N - 1) {
                            continue; // 리스트 저장 안함.
                        }
                        clist.add(new Core(i, j));
                    }
                }
            }

            minWireCnt = Integer.MAX_VALUE;
            maxCore = Integer.MIN_VALUE;
            // 전선 연결 시작
            // 어떻게 연결을 해야할까??
            dfs(0, 0, 0);

            System.out.println("#" + test_case + " " + minWireCnt);
        } // test_case end

    }

    /**
     * 전선 연결 DFS
     * 해당 코어 위치에서 사방 탐색을 한다.
     * 한 방향으로 계속해서 나아갔을 때, 범위를 벗어나면
     * 방해물이 없는 것으로 판단. 전선을 설치한다.
     * 가는 도중에 코어나 전선을 만난다면 방향을 바꿔서 다시 카운팅한다.
     * 카운팅이 되었다면 해당 정보를 누적해서 다음 DFS
     * 카운팅이 없다면 인덱스만 늘려서 DFS
     */
    private static void dfs(int idx, int corecnt, int wirecnt) {
        if (idx == clist.size()) {
            if (maxCore < corecnt) {
                maxCore = corecnt;
                minWireCnt = wirecnt;
            } else if (maxCore == corecnt) {
                minWireCnt = Math.min(wirecnt, minWireCnt);
            }
            return;
        }
        // 인덱스 위치의 코어의 좌표
        int x = clist.get(idx).x;
        int y = clist.get(idx).y;

        // 4방향 탐색
        for (int dir = 0; dir < 4; dir++) {
            int count = 0;
            int nx = x;
            int ny = y;

            while (true) {
                nx += dx[dir];
                ny += dy[dir];

                // 범위를 벗어났다는 소리는 가는 도중 다른 코어나
                // 전선을 만나지 않았다는 소리.
                if (outRange(ny, nx))
                    break;
                // 가는 길에 다른 코어 혹은 전선이 존재한다면
                // 다른 방향으로
                if (cell[ny][nx] == 1) {
                    count = 0;
                    break;
                }
                // 어떠한 방해도 없다면 카운트+1
                count++;
            } // while end

            // count 개수만큼 1로 채워줌.
            int fill_x = x;
            int fill_y = y;

            for (int i = 0; i < count; i++) {
                fill_y += dy[dir];
                fill_x += dx[dir];
                cell[fill_y][fill_x] = 1;
            }

            // 카운트가 0이라는 소리는 어떤 경우에도 전선을 설치 할 수 없다는 뜻.
            // 연결을 다 해도 연결이 안되는 코어가 존재 할 수도 있다는 것.
            // 인덱스만 하나 늘리고 코어와 전선의 수는 그대로 다음 탐색
            if (count == 0)
                dfs(idx + 1, corecnt, wirecnt);
            else {
                // 카운트가 되었다는 소리는 카운트의 숫자만큼 전선이 깔렸다는 소리.
                // 다음 인덱스와 코어를 하나 증가시키고 전선을 카운트 수만큼 더해서 다음 탐색.
                dfs(idx + 1, corecnt + 1, wirecnt + count);

                // DFS를 빠져나온 다음 원본 맵을 다시 0으로 되돌려 줌.
                // 전선 파괴
                fill_x = x;
                fill_y = y;
                for (int i = 0; i < count; i++) {
                    fill_y += dy[dir];
                    fill_x += dx[dir];
                    cell[fill_y][fill_x] = 0;
                }
            }
        }
    }

    /** 범위 벗어났는지 판단 */
    private static boolean outRange(int ny, int nx) {
        if (ny < 0 || ny >= N || nx < 0 || nx >= N)
            return true;
        return false;
    }
}
