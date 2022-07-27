package D2.연결리스트;

import java.util.LinkedList;
import java.util.Scanner;

/* SWEA_1228 암호문 리스트로 풀기
x의 위치에 y개의 숫자를 add해주면 된다. 다만 x의 위치는 add함에 따라 증가시켜준다.
*/

public class SWEA_1229_암호문2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n, k; // n: 원본 암호문의 길이, k: 명령어 개수
        for (int t = 1; t <= 10; t++) {
            int count = 0;
            n = sc.nextInt();

            LinkedList<Integer> list = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                list.add(sc.nextInt()); // 원본암호문 추가
            }

            k = sc.nextInt(); // 명령어 개수

            for (int j = 0; j < k; j++) {
                String s = sc.next(); // i 제거
                int x = sc.nextInt(); // 넣을 인덱스
                int y = sc.nextInt(); // 넣을 숫자 개수

                // 1. I(삽입) x, y, s : 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입한다. s는 덧붙일 숫자들이다.
                if (s.equals("I")) {
                    for (int i = 0; i < y; i++) {
                        list.add(x, sc.nextInt()); // 삽입하면서 x값을 증가시켜 인덱스도 증가
                        x++;
                    }
                    // 2. D(삭제) x, y : 앞에서부터 x의 위치 바로 다음부터 y개의 숫자를 삭제한다.
                } else {
                    for (int i = 0; i < y; i++) { // 제거
                        list.remove(x);
                    }
                }
            }

            System.out.printf("#%d ", t);
            while (!(list.isEmpty())) {
                if (count > 9)
                    break;
                System.out.println(list.poll() + " ");
                count++;

            }
            System.out.println();
        }
    }
}
