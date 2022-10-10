package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bj_2096_내려가기 {
    
    static class FastIn {
        BufferedReader br;
        StringTokenizer st;
        
        public FastIn() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        public String next() {
            if (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        public int nextInt() {
            return Integer.parseInt(next());
        }
        
    }

    static int n, max, min;
    static int[] dx = {-1, 0, 1};
    
    public static void main(String[] args) {
        FastIn sc = new FastIn();
        
        n = sc.nextInt();
        max = 0;
        min = Integer.MAX_VALUE;
        int[][] arr = new int[n][3];
        int[][] maxDp = new int[n][3];
        int[][] minDp = new int[n][3];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
                if (i == 0) {
                    maxDp[i][j] = arr[i][j];
                    minDp[i][j] = arr[i][j];
                } else {
                    minDp[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    int tx = j + dx[k];
                    if (0 <= tx && tx < 3) {
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i-1][tx] + arr[i][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i-1][tx] + arr[i][j]);
                    }
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            max = Math.max(max, maxDp[n-1][i]);
            min = Math.min(min, minDp[n-1][i]);
        }
        System.out.println(max + " " + min);
    }

}
