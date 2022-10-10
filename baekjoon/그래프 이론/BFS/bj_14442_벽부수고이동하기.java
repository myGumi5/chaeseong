package gold;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bj_14442_벽부수고이동하기 {

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
    
    static int n, m, k, ans;
    static char[][] map;
    static int[][] visited;
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    public static void bfs() {
        int sx = 0, sy = 0;
        Queue<int[]> q = new LinkedList<int[]>();

        // 거리, 지나온 벽, y, x
        q.add(new int[] { 1, 0, sy, sx });
        visited[sy][sx] = 0;
        while (!q.isEmpty()) {
            int[] temp = q.poll();
            int cD = temp[0];
            int ck = temp[1];
            int cy = temp[2];
            int cx = temp[3];
            if (cy == n - 1 && cx == m - 1) {
                ans = cD;
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int tx = cx + dx[i];
                int ty = cy + dy[i];
                int tk = ck;
                int tD = cD + 1;
                if (ty < 0 || ty >= n || tx < 0 || tx >= m)
                    continue;
                if (map[ty][tx] == '1' && ++tk > k)
                    continue;
                if (visited[ty][tx] != -1 && visited[ty][tx] <= tk)
                    continue;
                q.add(new int[] {tD, tk, ty, tx});
                visited[ty][tx] = tk;
            }
        }
    }

    public static void main(String[] args) {

        FastIn sc = new FastIn();
        StringTokenizer st;
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();
        ans = -1;
        map = new char[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j);
                visited[i][j] = -1;
            }
        }
        
        bfs();
        System.out.println(ans);
    }

}
