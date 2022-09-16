package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_14502_연구소 {
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
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
    static int n, m, wallCnt, ans;
    static int[][] arr;
    static boolean[][] v;
    static LinkedList<int[]> virusList;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    static void bfs() {
        int vCnt = 0;
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        for (int[] is : virusList) {
            visited[is[0]][is[1]] = true;
            vCnt++;
            q.add(new int[] { is[0], is[1] });
        }

        while (!q.isEmpty()) {
            int[] cP = q.poll();
            int cy = cP[0];
            int cx = cP[1];

            for (int i = 0; i < dx.length; i++) {
                int ty = cy + dy[i];
                int tx = cx + dx[i];

                if (-1 < ty && ty < n && -1 < tx && tx < m) {
                    if (arr[ty][tx] == 0 && !visited[ty][tx]) {
                        visited[ty][tx] = true;
                        vCnt++;
                        q.add(new int[] { ty, tx });
                    }
                }

            }
        }
        ans = Math.max(ans, n * m - vCnt - wallCnt - 3);
    }

    static void combination(int newWallCnt, int y, int x) {
        if (newWallCnt == 3) {
            bfs();
            return;
        }

        for (int i = y; i < n; i++) {
            int j = 0;
            if (i == y) j = x;
            for (; j < m; j++) {
                if (!v[i][j]) {
                    v[i][j] = true;

                    if (arr[i][j] == 0) {
                        arr[i][j] = 1;

                        if (i < n - 1 && j == m - 1) {
                            combination(newWallCnt + 1, y + 1, 0);
                        } else {
                            combination(newWallCnt + 1, y, x + 1);
                        }

                        arr[i][j] = 0;
                    }
                    v[i][j] = false;
                }
            }
        }

    }

    public static void main(String[] args) {
        FastIn sc = new FastIn();
        ans = 0;
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n][m];
        v = new boolean[n][m];
        virusList = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] == 2) {
                    virusList.add(new int[] { i, j });
                } else if (arr[i][j] == 1)
                    wallCnt++;
            }
        }
        combination(0, 0, 0);
        System.out.println(ans);
    }

}
