package D6.그리디;

import java.util.Scanner;

class SWEA_1245_균형점 {
    static int n = 0;
    static double[] px = new double[10];
    static int[] m = new int[10];

    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            n = sc.nextInt();
            for (int i = 0; i < n; i++) {
                px[i] = sc.nextDouble(); // x좌표
            }
            for (int i = 0; i < n; i++) {
                m[i] = sc.nextInt(); // 질량
            }

            System.out.printf("#%d ", test_case);
            for (int i = 0; i < n - 1; i++) {
                binarySearch(i, px[i], px[i + 1]);
            }

            System.out.println();
        }
    }

    static void binarySearch(int slash, double left, double right) {
        double x = 0;
        double sum;
        int cnt = 0;
        while (cnt <= 100) {
            x = (left + right) / 2.0;

            sum = 0;
            for (int i = 0; i <= slash; i++) {
                sum += gravity(i, x);
            }
            for (int i = n - 1; i > slash; i--) {
                sum -= gravity(i, x);
            }

            if (sum > 0) {
                left = x;
            } else if (sum < 0) {
                right = x;
            }
            cnt++;
        }
        System.out.printf("%.10f ", x);
    }

    static double gravity(int idx, double x) {
        return m[idx] / ((px[idx] - x) * (px[idx] - x));
    }
}