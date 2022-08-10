import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
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
    
    public static int bfs(int y, int x) {
        int result = 0;
        Queue<int []> q = new LinkedList();
        visited[y][x] = 1;
        q.add(new int[] {y, x});
        
        int [] dx = new int[] {-1,1,0,0};
        int [] dy = new int[] {0,0,-1,1};
        
        while (!q.isEmpty()) {
            int[] cP = q.poll();
            if (arr[cP[0]][cP[1]] == 1)
                result++;
            
            for (int i = 0; i < 4; i++) {
                int tx = cP[1] + dx[i];
                int ty = cP[0] + dy[i];
                if (-1 < tx && tx < m && -1 < ty && ty < n && visited[ty][tx] == 0 && arr[ty][tx] == 1) {
                    visited[ty][tx] = 1;
                    q.add(new int[] {ty, tx});
                }
            }
        }
        return result;
    }
    
    private static int[][] arr;
    private static int[][] visited;
    private static int n, m;
    public static void main(String[] args) {
        FastIn sc = new FastIn();
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        visited = new int[n][m];
        int max = 0;
        int k = sc.nextInt();
        
        for (int i = 0; i < k; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            arr[r-1][c-1] = 1;
        }
        for (int y = 0; y < n; y++) {
            for(int x = 0; x < m; x++) {
                if (visited[y][x] == 1)
                    continue;
                else max = Math.max(max, bfs(y, x));
            }
        }
        System.out.println(max);
    }
}
