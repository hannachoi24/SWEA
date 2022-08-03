package D6.그리디;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
통로라는 새로운 배열을 만들면 쉽게 해결할 수 있다.
room1과 room2가 공유하는 복도가 corridor[1]이 된다. 
이렇게 끝방까지 총 1에서 200까지의 통로가 있다. 해당 통로를 지나가는 학생이 있다면 해당 통로의 값에 +1을 해준다. 
학생들이 지나간 후 corridor배열의 max값을 출력하면 총 걸리는 시간이 나온다.
*/

public class SWEA_4408_자기방으로돌아가기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int count = 0;
            int corridor[] = new int[201];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int A = (Integer.parseInt(st.nextToken()) + 1) / 2; // 현재 방
                int B = (Integer.parseInt(st.nextToken()) + 1) / 2; // 돌아가야될 방

                if (A < B) {
                    for (int j = A; j <= B; j++) {
                        corridor[j]++;
                    }
                } else {
                    for (int j = B; j <= A; j++) {
                        corridor[j]++;
                    }
                }
            }
            Arrays.sort(corridor);
            count = corridor[200];
            sb.append("#" + tc + " " + count + '\n');
        }

        System.out.println(sb);
    }
}
