package gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bj_7579_앱 {

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
    
    public static void main(String[] args) {
        FastIn sc = new FastIn();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int costSum = 0;
        int[] memories = new int[n + 1];
        int[] costs = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            memories[i] = sc.nextInt();
        }
        for (int i = 1; i <= n; i++) {
            costs[i] = sc.nextInt();
            costSum += costs[i];
        }
        int[][] dp = new int[n + 1][costSum + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= costSum; j++) {
                if (j < costs[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - costs[i]] + memories[i]);
                }
            }
        }

//        for (int i = 0; i <= n; i++) {
//            System.out.println("dp[" + i + "] : " + Arrays.toString(dp[i]));
//        }
        
        for (int i = 1; i <= costSum; i++) {
            if (dp[n][i] >= m) {
                System.out.println(i);
                break;
            }
        }
    }
}
