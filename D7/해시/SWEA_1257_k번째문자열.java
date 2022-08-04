package D7.해시;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_1257_k번째문자열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int k = Integer.parseInt(br.readLine());
            String str = br.readLine();
            String[] suffixArray = new String[str.length()]; // 접미사 배열
            // LCP는 바로 앞의 Suffix Array 와 비교해 앞에서부터 중첩되는 최대 개수를 구하는 배열
            int[] LCP = new int[str.length()]; // od, ood 의 o 가 중첩된다. 이 o 를 걸러내는 역할이 LCP

            for (int i = 0; i < str.length(); i++)
                suffixArray[i] = str.substring(i);

            Arrays.sort(suffixArray);
            LCP[0] = 0;

            for (int i = 1; i < str.length(); i++) {
                String prev = suffixArray[i - 1];
                String cur = suffixArray[i];
                int j = 0;
                while (j < prev.length() && j < cur.length() && prev.charAt(j) == cur.charAt(j))
                    j++;
                LCP[i] = j; // ex) od, ood, oood가 있으면 o의 중첩 횟수 저장 (0, 1, 2)
            }

            int subCnt = 0;
            for (int i = 0; i < str.length(); i++) {
                // od 까지의 부분집합의 합은 7이다. 아직 8이 되지 않았으므로 그 다음 SuffixArray로 넘어간다.
                // ood에서 첫 번째 값을 뽑으면 되는데, o는 제외해야한다. 여기서 바로 LCP가 사용된다.
                subCnt += suffixArray[i].length() - LCP[i];
                if (subCnt >= k) {
                    System.out.printf("#%d %s\n", tc,
                            suffixArray[i].substring(0, suffixArray[i].length() - (subCnt - k)));
                    break;
                }
            }
            /*
             * subCnt 는 ood 까지의 합이라고 보면 된다. (10)
             * 여기서 원하는 목표 k 는 8이므로
             * 3 - (10 - 8) = 1 이 된다. (suffixArray[i].length() 는 3이므로.)
             * 따라서 0에서 1까지의 substring 을 뽑으면 oo 이 출력된다.
             * LCP 로 중첩되는 문자만큼을 건너뛰는 것이다.
             */

            if (subCnt < k)
                System.out.printf("#%d none\n", tc);
        }
    }
}
