import java.io.BufferedReader;
import java.io.InputStreamReader;
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

    static char[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        FastIn sc = new FastIn();
        int n = sc.nextInt();
        int m = sc.nextInt();
        int result = 0;

        map = new char[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = sc.next().toCharArray();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 'L' && isStart(i, j))
                    result = Math.max(result, bfs(i, j));
            }
        }
        System.out.println(result);
    }

    static boolean isStart(int y, int x) {
        int count = 0, wCount = 0, hCount = 0;

        for (int i = 0; i < 4; i++) {
            int tY = y + dy[i];
            int tX = x + dx[i];
            if (-1 < tY && tY < map.length && -1 < tX && tX < map[0].length && map[tY][tX] == 'L') {
                count++;
                if (i < 2)
                    wCount++;
                else
                    hCount++;
            }
        }
        return count == 1 || count == 2 && wCount == 1;
    }

    static int bfs(int y, int x) {
        int result = 0;
        Queue<int[]> q = new LinkedList<int[]>();
        visited = new boolean[map.length][map[0].length];

        visited[y][x] = true;
        q.offer(new int[] { y, x, 0 });

        while (!q.isEmpty()) {
            int[] cPosition = q.poll();
            int cY = cPosition[0];
            int cX = cPosition[1];
            int cDay = cPosition[2];
            result = cDay;
            for (int i = 0; i < 4; i++) {
                int tY = cY + dy[i];
                int tX = cX + dx[i];
                if (-1 < tY && tY < map.length && -1 < tX && tX < map[0].length && !visited[tY][tX]
                        && map[tY][tX] != 'W') {
                    visited[tY][tX] = true;
                    q.offer(new int[] { tY, tX, cDay + 1 });
                }
            }
        }
        return result;
    }
}