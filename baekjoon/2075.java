import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
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
        PriorityQueue<Integer> pQ = new PriorityQueue<>(Collections.reverseOrder());
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                pQ.offer(sc.nextInt());
            }
        }
        for (int i = 0; i < n-1; i++) {
            pQ.poll();
        }
        System.out.println(pQ.poll());
    }

}
