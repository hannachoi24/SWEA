package D9.분할정복;

import java.util.*;
import java.io.*;

public class SWEA_7701_염라대왕의이름정렬 {
    static int N;
    static HashSet<String> set; // 중복을 피하기 위해 HashSet사용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb = new StringBuilder();
            sb.append("#" + tc + "\n");
            N = Integer.parseInt(br.readLine());
            set = new HashSet<String>();

            for (int i = 0; i < N; i++) {
                String s = br.readLine();
                set.add(s);
            }

            List list = new ArrayList(set);
            Collections.sort(list, new Comparator<String>() {

                @Override
                public int compare(String s1, String s2) {
                    if (s1.length() == s2.length()) {
                        return s1.compareTo(s2);
                    } else
                        return s1.length() - s2.length();
                }
            });

            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i) + "\n");
            }

            System.out.print(sb.toString());

        }
    }
}
