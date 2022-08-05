package D8.트리;

import java.util.*;

public class SWEA_2930_힙 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#");
            sb.append(tc);
            pq.clear();
            int N = sc.nextInt();

            for (int i = 0; i < N; i++) {
                int operation = sc.nextInt();

                if (operation == 1) {
                    pq.add(sc.nextInt());
                    continue;
                }
                if (pq.isEmpty()) {
                    sb.append(" -1");
                    continue;
                }
                sb.append(" ");
                sb.append(pq.poll());
            }
            sb.append("\n");
        }
        System.out.print(sb);
        sc.close();
    }
}