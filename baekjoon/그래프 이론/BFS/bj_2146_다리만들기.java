import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bj_2146_다리만들기 {
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

    static int n, ans;
    static int[][] map;
    static int[][] v;
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    public static void dfs(int y, int x, int num) {
        if (v[y][x] != 0)
            return;
        v[y][x] = num;
        for (int i = 0; i < 4; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];
            if (ty < 0 || ty > n - 1 || tx < 0 || tx > n - 1)
                continue;
            if (v[ty][tx] == 0 && map[ty][tx] == 1)
                dfs(ty, tx, num);
        }

    }

    public static void bfs(int y, int x) {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> vList = new LinkedList<int[]>();
        q.add(new int[] {y, x, 0});
        int startIsland = v[y][x];
        
        findShortCut: while(!q.isEmpty()) {
            int[] temp = q.poll();
            int cy = temp[0];
            int cx = temp[1];
            int cDay = temp[2];
            
            for (int i = 0; i < 4; i++) {
                int ty = cy + dy[i];
                int tx = cx + dx[i];
                if (ty < 0 || ty > n - 1 || tx < 0 || tx > n - 1)
                    continue;
                if (v[ty][tx] > 0 && v[ty][tx] != startIsland) {
                    ans = Math.min(cDay, ans);
                    break findShortCut;
                }
                if (v[ty][tx] == 0) {
                    v[ty][tx] = -1;
                    q.add(new int[] {ty, tx, cDay + 1});
                    vList.add(new int[] {ty, tx});
                }
            }
        }
        while(!vList.isEmpty()) {
            int[] temp = vList.poll();
            v[temp[0]][temp[1]] = 0;
        }
    }

    public static void main(String[] args) {
        FastIn sc = new FastIn();

        ans = 1_000_000;
        n = sc.nextInt();
        map = new int[n][n];
        v = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        int islandNum = 1;
        // 섬 번호 매기기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (v[i][j] == 0 && map[i][j] == 1)
                    dfs(i, j, islandNum++);
            }
        }
        // 다리 만들기 연습
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1)
                    bfs(i, j);
            }
        }
        System.out.println(ans);
    }

}
