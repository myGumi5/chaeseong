import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class swea_5658_보물상자비밀번호 {

    static class FastIn {
        BufferedReader br = null;
        StringTokenizer st = null;

        public FastIn() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            if (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    
    public static int ans;
    public static int n;
    public static int k;
    public static int len;
    public static PriorityQueue<Integer> pQ;

    public static int decode(char c) {
        if (c >= '0' && c <= '9')
            return c - '0';
        else
            return 10 + c - 'A';
    }

    public static void rotate(LinkedList<Integer> lock) {
        for (int num = 0; num < len + 1; num++) {
            for (int i = 0; i < 4; i++) {
                int t = 0;
                for (int j = 0; j < len; j++) {
                    t += lock.get(i * len + j) * Math.pow(16, len - j - 1);
                }
                if (!pQ.contains(t))
                    pQ.add(t);
            }
            if (num != len) {
                lock.addFirst(lock.removeLast());
            }
        }
        for (int i = 0; i < k; i++) {
            ans = pQ.poll();
        }
    }

    public static void main(String[] args) {
        FastIn sc = new FastIn();
        int T;
        T = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int test_case = 1; test_case <= T; test_case++) {
            // 초기화
            ans = 0;
            n = sc.nextInt();
            k = sc.nextInt();
            len = n / 4;
            pQ = new PriorityQueue<>(Collections.reverseOrder());
            LinkedList<Integer> lock = new LinkedList<>();
            String code = sc.next();

            for (int i = 0; i < code.length(); i++)
                lock.add(decode(code.charAt(i)));

            rotate(lock);

            sb.append("#" + test_case + " " + ans + "\n");
        }
        System.out.println(sb);
    }

}
