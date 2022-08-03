package D6.그리디;

import java.util.Scanner;

public class SWEA_1970_거스름돈 {
    static int n; // 거스름돈
    static int coinTypes[] = { 50000, 10000, 5000, 1000, 500, 100, 50, 10 };
    static int cnt[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        cnt = new int[8];

        for (int tc = 1; tc <= T; tc++) {
            n = sc.nextInt();
            System.out.printf("#%d\n", tc);

            for (int i = 0; i < coinTypes.length; i++) {
                cnt[i] = n / coinTypes[i];
                n %= coinTypes[i]; // 나머지 계산을 위한 n 갱신 (i가 1일 때 n = 2850)
            }

            for (int c : cnt) { // cnt 값을 차례대로 c에 넣어줌
                System.out.printf("%d ", c);
            }
            System.out.println();

        }
        sc.close();
    }
}
