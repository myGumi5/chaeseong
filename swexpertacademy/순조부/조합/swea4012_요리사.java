package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class swea4012_요리사 {

    static class FastIn {
        BufferedReader br;
        StringTokenizer st;
        
        FastIn() {
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
    
    public static int n;
    public static int ans;
    public static int[][] arr;
    public static boolean[] v;
    
    public static void combination(int cnt, int idx, int goal) {
        
        if (cnt == goal) {
            int aTaste = 0;
            int bTaste = 0;
            
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (v[i] == v[j]) {
                        if (v[i]) {
                            aTaste += arr[i][j];
                            aTaste += arr[j][i];
                        } else {
                            bTaste += arr[i][j];
                            bTaste += arr[j][i];
                        }
                    }
                }
            }
            ans = Math.min(ans, Math.abs(aTaste - bTaste));
            return;
        }
        
        for (int i = idx; i < n; i++) {
            if (!v[i]) {
                v[i] = true;
                
                combination(cnt+1, i + 1, goal);
                
                v[i] = false;
            }
        }
        
    }
    
    public static void main(String[] args) throws Exception {

        FastIn sc = new FastIn();
        int T;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T=sc.nextInt();
        
        for (int test_case = 1; test_case <= T; test_case++) {
            ans = Integer.MAX_VALUE;
            n = sc.nextInt();
            v = new boolean[n];
            arr = new int[n][n];
            
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            
            for (int i = 2; i <= n/2; i++) {
                combination(0, 0, i);
            }
            
            bw.write("#" + test_case + " " + ans + "\n");
            
        }
        bw.flush();
        bw.close();
    }

}
