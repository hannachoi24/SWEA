package D3.힙;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
    int data;
    int left;
    int right;
    String op;
}

public class SWEA_1232_사칙연산 {
    public static Node[] node = new Node[10000];
    public static int len;
    public static int result;

    public static int postOrder(int cur) {
        String op = node[cur].op;

        if (op != null && op.equals("+"))
            result = postOrder(node[cur].left) + postOrder(node[cur].right);
        else if (op != null && op.equals("-"))
            result = postOrder(node[cur].left) - postOrder(node[cur].right);
        else if (op != null && op.equals("*"))
            result = postOrder(node[cur].left) * postOrder(node[cur].right);
        else if (op != null && op.equals("/"))
            result = postOrder(node[cur].left) / postOrder(node[cur].right);
        else
            result = node[cur].data;

        return result;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int test_case = 1; test_case <= 10; test_case++) {
            len = Integer.parseInt(br.readLine());

            for (int i = 1; i <= len; i++)
                node[i] = new Node();

            for (int i = 0; i < len; i++) {
                st = new StringTokenizer(br.readLine());
                int idx = Integer.parseInt(st.nextToken()); // 정점 번호
                String op = st.nextToken(); // 연산자

                /*
                 * 정점이 연산자이면 정점번호, 연산자, 해당정점의 왼쪽 자식, 오른쪽 자식의 정점번호가 차례대로 주어진다.
                 */
                if (op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")) {
                    node[idx].op = op;
                    node[idx].left = Integer.parseInt(st.nextToken());
                    node[idx].right = Integer.parseInt(st.nextToken());
                } else
                    node[idx].data = Integer.parseInt(op); // 정점이 단순한 수이면 정점번호와 해당 양의 정수가 주어진다
            }
            System.out.println("#" + test_case + " " + postOrder(1)); // 루트 정점의 번호는 반드시 1
        }
    }
}
