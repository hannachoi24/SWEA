package D10.이분탐색;

import java.io.*;

public class SWEA_9843_촛불이벤트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            long num = Long.parseLong(br.readLine());
            long result = 0;
            long index = -1;

            for (long n = (long) Math.sqrt(num * 2);; ++n) {
                result = (n * (n + 1)) / 2;
                if (result == num) {
                    index = n;
                    break;
                } else if (result > num) {
                    break;
                }
            }

            bw.write("#" + tc + " " + index + "\n");
        }

        br.close();
        bw.close();
    }
}

/*
 * 결국 1단부터 K단까지 쌓게되면 위에 공식처럼 K(K+1)/2 가되는데
 * K(K+1)/2 = N 이라 할때 우리는 N을 받기 때문에
 * K(K+1) = 2N이 되고 약간 계산하기 편하게 하여
 * K^2 = 2N ---> K = route(2N) 이라고 보면 K의 최소값을 알 수 있다.
 * K(K+1) >= K^2 이기 때문에 이때 구한 K부터 1씩 증가시켜보면서 확인 절차도 해볼 것이다.
 * 증가시키더라도 K+a단 쌓았을 때 N이 넘어가면 그만확인해보면 해결이 가능하다.
 */