package D8.트리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class SWEA_3000_중간값구하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            String[] arr = br.readLine().split(" ");
            Queue<Integer> min = new PriorityQueue<Integer>();
            Queue<Integer> max = new PriorityQueue<Integer>((x, y) -> Integer.compare(y, x));

            int n = Integer.parseInt(arr[0]), mid = Integer.parseInt(arr[1]), sum = 0;

            for (int i = 0; i < n; i++) {
                arr = br.readLine().split(" ");
                int X = Integer.parseInt(arr[0]), Y = Integer.parseInt(arr[1]);

                // 최소힙의 top > 중간값 > 최대힙의 top이 유지되도록 값을 유지하면 중간값이 항상 유지된다.
                if (X < mid) // 원소가 아직 남아 있는 경우
                    max.add(X);
                else
                    min.add(X);

                if (Y < mid)
                    max.add(Y);
                else
                    min.add(Y);

                // 우선순위 큐 size를 동일하게 유지해가면서 값을 삽입
                while (min.size() < max.size()) {
                    min.add(mid);
                    mid = max.poll();
                }

                while (max.size() < min.size()) {
                    max.add(mid);
                    mid = min.poll();
                }

                sum = (sum + mid) % 20171109;
            }

            System.out.println("#" + tc + " " + sum);
        }
    }
}
