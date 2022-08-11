package D1.비트연산;

import java.util.Scanner;

public class SWEA_10726_이진수표현 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            int x = (1 << n) - 1;

            if ((m & x) == x)
                System.out.printf("#%d ON\n", tc);
            else
                System.out.printf("#%d OFF\n", tc);
        }
    }
}
