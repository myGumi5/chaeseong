package gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_17070_파이프옮기기1 {
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
    
    static int n;
    static int[][] arr;
    static int ans = 0;

    public static void dfs(int p, int y, int x) {
        if (y == n - 1 && x == n - 1) {
            ans++;
            return;
        }
        boolean xFlag = false;
        boolean yFlag = false;
        boolean diagonalFlag = false;
        // 가로 검사
        if (x < n - 1 && arr[y][x + 1] != 1) {
            xFlag = true;
        }
        // 세로 검사
        if (y < n - 1 && arr[y + 1][x] != 1) {
            yFlag = true;
        }
        if (xFlag && yFlag && arr[y + 1][x + 1] != 1) {
            diagonalFlag = true;
        }
        if (xFlag && p <= 1)
            dfs(0, y, x + 1);
        if (yFlag && p >= 1)
            dfs(2, y+1, x);
        if (diagonalFlag)
            dfs(1, y+1, x + 1);
    }
//    시간초과
//    public static void bfs() {
//        Queue<int[]> q = new LinkedList<>();
//        // 모양 : 0 -> 가로 1 -> 대각선 2 -> 세로, 1, y, x
//        q.add(new int[] { 0, 0, 1 });
//
//        while (!q.isEmpty()) {
//            int[] cP = q.poll();
//            int p = cP[0];
//            int y = cP[1];
//            int x = cP[2];
//            if (y == n - 1 && x == n - 1) {
//                ans++;
//                continue;
//            }
//            boolean xFlag = false;
//            boolean yFlag = false;
//            boolean diagonalFlag = false;
//            // 가로 검사
//            if (x < n - 1 && arr[y][x + 1] != 1) {
//                xFlag = true;
//            }
//            // 세로 검사
//            if (y < n - 1 && arr[y + 1][x] != 1) {
//                yFlag = true;
//            }
//            if (xFlag && yFlag && arr[y + 1][x + 1] != 1) {
//                diagonalFlag = true;
//            }
//            if (xFlag && p <= 1)
//                q.add(new int[] { 0, y, x + 1 });
//            if (yFlag && p >= 1)
//                q.add(new int[] { 2, y + 1, x });
//            if (diagonalFlag)
//                q.add(new int[] { 1, y + 1, x + 1 });
//        }
//    }

    public static void main(String[] args) {
        FastIn sc = new FastIn();

        n = sc.nextInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

//        bfs();
        dfs(0, 0, 1);
        System.out.println(ans);
    }

}
