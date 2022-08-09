package D10.이분탐색;

import java.util.*;
import java.io.*;

class SWEA_9999_광고시간정하기 {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter output = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; ++tc) {
            int L = Integer.parseInt(br.readLine()), N = Integer.parseInt(br.readLine());
            List<Peek> peeks = new ArrayList<Peek>();

            int s = 0;
            for (int i = 0; i < N; i++) {
                String[] ar = br.readLine().split(" ");
                int x = Integer.parseInt(ar[0]), y = Integer.parseInt(ar[1]);
                s += y - x;
                peeks.add(new Peek(x, y, s));
            }

            int max = -1;

            for (int i = 0; i < N; i++) {
                int sum = 0, begin = peeks.get(i).begin, end = begin + L;
                Peek lastPeek = findPeek(peeks, end);

                sum = lastPeek.sum - peeks.get(i).sum + peeks.get(i).end - peeks.get(i).begin;

                if (lastPeek.end > end && lastPeek.begin < end) {
                    sum -= lastPeek.end - end;
                } else if (lastPeek.end > end) {
                    sum -= lastPeek.end - lastPeek.begin;
                }

                max = Math.max(max, sum);
            }

            output.write("#" + tc + " " + max + "\n");
        }

        output.flush();
    }

    static Peek findPeek(List<Peek> peeks, int target) { // end가 target 이상인 peek
        int start = 0, end = peeks.size() - 1;

        while (end > start) {
            int mid = (start + end) / 2;

            if (peeks.get(mid).end >= target)
                end = mid;
            else
                start = mid + 1;
        }

        return peeks.get(end);
    }

    static class Peek {
        int begin, end, sum;

        public Peek(int begin, int end, int sum) {
            this.begin = begin;
            this.end = end;
            this.sum = sum;
        }
    }
}