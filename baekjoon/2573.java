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

    static int h, w;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = { -1, 1, 0, 0 };
    static int[] dx = { 0, 0, -1, 1 };

    public static void main(String[] args) {
        FastIn sc = new FastIn();
        h = sc.nextInt();
        w = sc.nextInt();

        map = new int[h][w];
        int day = 0;
        int[][] copiedMap;

        // 초기화
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        while (true) {
            int count = 0;
            day++;
            copiedMap = new int[h][w];
            visited = new boolean[h][w];
            // 빙산 녹는 로직
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 0)
                        continue;
                    copiedMap[i][j] = map[i][j];
                    for (int k = 0; k < 4; k++) {
                        int ty = i + dy[k];
                        int tx = j + dx[k];
                        if (isAvailable(ty, tx) && map[ty][tx] == 0) {
                            if (copiedMap[i][j] <= 0)
                                copiedMap[i][j] = 0;
                            else
                                copiedMap[i][j]--;
                        }
                    }
                }
            }
            map = copiedMap;
            copiedMap = null;

//            for (int i = 0; i < h; i++) {
//                System.out.println(Arrays.toString(map[i]));
//            }
//            System.out.println("-------------------------");

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 0)
                        continue;
                    if (!visited[i][j] && dfs(i, j))
                        count++;
                    if (count > 1) {
                        System.out.println(day);
                        return;
                    }
                }
            }
            if (count == 0) {
                System.out.println(0);
                return;
            }
        }
    }

    public static boolean isAvailable(int y, int x) {
        return (-1 < y && y < h && -1 < x && x < w);
    }

    public static boolean dfs(int y, int x) {
        boolean result = false;

        // 종료조건
        if (visited[y][x] == true || map[y][x] == 0)
            return result;
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int tx = x + dx[i];
            int ty = y + dy[i];
            if (isAvailable(ty, tx) && !visited[ty][tx] && map[ty][tx] != 0)
                dfs(ty, tx);
        }
        return true;
    }
    
    public static boolean bfs(int y, int x) {
        // 종료조건
        if (visited[y][x] == true || map[y][x] == 0) return false;
        
        Queue<int []> q = new LinkedList<int[]>();
        visited[y][x] = true;
        q.offer(new int[] {y, x});
        
        while(!q.isEmpty()) {
            int[] cP = q.poll();
            int cy = cP[0];
            int cx = cP[1];
            
            for(int i = 0; i < 4; i++) {
                int ty = cy + dy[i];
                int tx = cx + dx[i];
                if (isAvailable(ty, tx) && !visited[ty][tx] && map[ty][tx] != 0) {
                    visited[ty][tx] = true;
                    q.offer(new int [] {ty, tx});
                }
            }
        }
        
        return true;
    }

}
