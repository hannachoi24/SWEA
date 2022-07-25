package 기본기.비트연산;

import java.util.Scanner;

public class SWEA_1240_단순2진암호코드 {
    public static String[] code = { "0001101", "0011001", "0010011", "0111101",
            "0100011", "0110001", "0101111", "0111011", "0110111", "0001011" };

    public static int[] toNum(String s) {
        int num[] = new int[8];
        int idx = 0;
        for (int i = 0; i < s.length(); i += 7) {
            String tmp = s.substring(i, i + 7);
            for (int j = 0; j < code.length; j++) {
                if (tmp.equals(code[j])) {
                    num[idx++] = j;
                    break;
                }
            }
        }
        return num;
    }

    public static int isValid(String code) {
        int num[] = toNum(code);
        int sum = 0;
        int total = 0;

        for (int i = 0; i < num.length; i++) {
            sum += num[i];
            if (i % 2 == 0) {
                total += 3 * num[i];
            } else
                total += num[i];
        }

        if (total % 10 == 0)
            return sum;
        return 0;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int TC = 1; TC <= T; TC++) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            String code = "";
            for (int i = 0; i < n; i++) {
                String s = sc.next();
                if (code.isEmpty()) {
                    for (int j = m - 1; j >= 0; j--) {
                        if (s.charAt(j) == '1') {
                            code = s.substring(j - 55, j + 1);
                            break;
                        }
                    }
                }
            }
            System.out.println("#" + TC + " " + isValid(code));
        }
    }

}

/*
 * 암호 코드가 주어졌을 때 정상인지 아닌지를 판별하는 문제다.
 *  
 * 암호 코드는 총 8개의 숫자로 이루어져 있고 앞 7자리는 상품 고유의 번호, 마지막 자리는 검증 코드를 나타낸다.
 * "홀수 자리의 합 * 3 + 짝수 자리의 합 + 검증 코드"를 계산하여 10으로 나누어 떨어진다면 그 암호 코드는 정상이다.
 *  
 * 직사각형 배열에 암호코드 정보가 포함되어 전달되고 배열은 1, 0으로 이루어져 있다.
 * 여기서 암호코드를 찾아서 판별하는 방법은 다음과 같다.
 * 
 * 그림에서 보면 모든 숫자들이 1로 끝난다.
 * 한 줄씩 입력받을 때마다 오른쪽 끝에서부터 확인해서 1을 찾고 거기서부터 56번째 전 숫자까지 자르면 그게 바로 암호 코드다.
 * 같은 암호 코드가 여러 줄에 걸쳐 입력되므로 이미 암호를 찾은 상태라면 나머지는 검사하지 않아도 된다.
 *  
 * 숫자에 매칭되는 암호 코드들을 code 배열에 저장해두었기 때문에 숫자로 바꿀 때는 그 암호 코드의 인덱스가 곧 숫자다.
 * 그렇게 8자리를 숫자로 바꾼 후 "홀수 자리의 합 * 3 + 짝수 자리의 합 + 검증 코드"를 계산했을 때 10의 배수라면 각 자리수의
 * 합을 리턴하고 그렇지 않으면 0을 리턴한다.
 */