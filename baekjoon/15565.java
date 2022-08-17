import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    public static class FastIn {
        BufferedReader br;
        StringTokenizer st;

        public FastIn() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            if (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) {
        FastIn sc = new FastIn();

        int n = sc.nextInt();
        int k = sc.nextInt();
        LinkedList<Integer> dolls = new LinkedList<>();

        int result = Integer.MAX_VALUE;
        int subSetLength = 0;
        for (int i = 0; i < n; i++) {
            if (sc.nextInt() == 1) {
                dolls.add(i);
                if (dolls.size() == k) {
                    subSetLength = i - dolls.peek();
                    result = Math.min(result, subSetLength + 1);
                } else if (dolls.size() > k) {
                    dolls.poll();
                    subSetLength = i - dolls.peek();
                    result = Math.min(result, subSetLength + 1);
                }
            }
        }
        if (dolls.size() < k)
            System.out.println(-1);
        else System.out.println(result);
    }
}