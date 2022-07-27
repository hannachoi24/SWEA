package D2.연결리스트;

import java.io.*;
import java.util.*;

public class SWEA_13501_수열편집 {
    public static void main(String[] args) throws IOException {
        int T;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 수열 길이
            int M = Integer.parseInt(st.nextToken()); // 추가 횟수
            int L = Integer.parseInt(st.nextToken()); // 출력할 인덱스 번호

            List<Integer> list = new LinkedList<>();
            st = new StringTokenizer(br.readLine());

            while (N-- > 0)
                list.add(Integer.parseInt(st.nextToken()));

            while (M-- > 0) {
                st = new StringTokenizer(br.readLine());
                int idx, n;
                String s = st.nextToken();
                switch (s) {
                    case "I": // idx번 인덱스에 숫자 n을 추가
                        idx = Integer.parseInt(st.nextToken());
                        n = Integer.parseInt(st.nextToken());
                        list.add(idx, n);
                        break;

                    case "D": // idx번 인덱스 자리를 지움
                        idx = Integer.parseInt(st.nextToken());
                        list.remove(idx);
                        break;

                    case "C": // idx번 인덱스 자리를 숫자 n으로 바꾼다.
                        idx = Integer.parseInt(st.nextToken());
                        n = Integer.parseInt(st.nextToken());
                        list.set(idx, n);
                        break;

                    default:
                        break;
                }
            }
            int result = list.size() > L ? list.get(L) : -1;
            System.out.println("#" + test_case + " " + result);
        }

    }

}
