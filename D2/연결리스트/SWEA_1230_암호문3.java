package D2.연결리스트;

import java.util.*;
import java.io.*;

public class SWEA_1230_암호문3 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tk;

        for (int test = 1; test <= 10; test++) {

            int n = Integer.parseInt(br.readLine());
            tk = new StringTokenizer(br.readLine());

            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int t = Integer.parseInt(tk.nextToken());
                list.add(t);
            }

            int com = Integer.parseInt(br.readLine());

            tk = new StringTokenizer(br.readLine());
            int cnt = 0;
            while (true) {
                String or = tk.nextToken();
                if (or.equals("I")) {
                    int x = Integer.parseInt(tk.nextToken());
                    int y = Integer.parseInt(tk.nextToken());
                    for (int p = 0; p < y; p++) {
                        int num = Integer.parseInt(tk.nextToken());
                        list.add(x + p, num);
                    }
                    cnt = cnt + 1;
                } else if (or.equals("D")) {
                    int x = Integer.parseInt(tk.nextToken());
                    int y = Integer.parseInt(tk.nextToken());
                    for (int p = 0; p < y; p++) {
                        list.remove(x);
                    }
                    cnt = cnt + 1;
                } else {
                    int y = Integer.parseInt(tk.nextToken());
                    for (int p = 0; p < y; p++) {
                        int num = Integer.parseInt(tk.nextToken());
                        list.add(num);
                    }
                    cnt = cnt + 1;
                }

                if (cnt == com)
                    break;
            }

            bw.write("#" + test + " ");
            for (int c = 0; c < 10; c++) {
                bw.write(list.get(c) + " ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }
}
