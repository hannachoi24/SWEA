package D1.비트연산;

import java.util.Scanner;

public class SWEA_1288_새로운불면증치료법 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            String s = sc.next();
            int n = Integer.parseInt(s);

            boolean[] arr = new boolean[10]; // 확인 배열

            int k = 1; // 곱해주는 변수

            int cnt = 0;

            while (true) {
                for (int i = 0; i < s.length(); i++) {
                    int temp = Integer.parseInt(s.substring(i, i + 1)); // 값이 있는 경우 인덱스 위치 배열 1씩 증가
                    System.out.println(temp);
                    if (arr[temp] == false) {
                        arr[temp] = true;
                        cnt++;
                        // System.out.println(cnt);
                    }
                    // System.out.println(cnt);
                }

                if (cnt == 10)
                    break;

                else {
                    k++;
                    s = Integer.toString(k * n);
                }
            }

            System.out.printf("#%d %s\n", tc, s);
        }
    }
}
