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
    static int[][] visited2;
    static int h, w;
    static int[] dx = { 1, 0, -1, 0 };
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) {
        FastIn sc = new FastIn();

        w = sc.nextInt();
        h = sc.nextInt();

        map = new char[h][w];
        visited2 = new int[h][w];

        for (int i = 0; i < h; i++) {
            map[i] = sc.next().toCharArray();
        }
        bfs(0, 0);
        System.out.println(visited2[h-1][w-1] - 1);
    }

    public static int bfs(int y, int x) {
        int result = 0;

        Queue<int []> q = new LinkedList<>();
        visited2[y][x] = 1;
        q.offer(new int[] {y, x, 1});
        
        while (!q.isEmpty()) {
            int[] cP = q.poll();
            int cy = cP[0];
            int cx = cP[1];
            int brokenNum = cP[2];
            
            for (int i = 0; i < 4; i++) {
                int ty = cy + dy[i];
                int tx = cx + dx[i];
                //유효성 검사
                if (-1 < ty && ty < h && -1 < tx && tx < w) {
                    if (map[ty][tx] == '1') { // 벽 일 경우
                       if (visited2[ty][tx] == 0 || visited2[ty][tx] > brokenNum + 1) {
                           visited2[ty][tx] = brokenNum + 1;
                           q.offer(new int[] {ty, tx, brokenNum + 1});
                       }
                    } else { // 벽 아닐 경우
                        if (visited2[ty][tx] == 0 || visited2[ty][tx] > brokenNum) {
                            visited2[ty][tx] = brokenNum;
                            q.offer(new int[] {ty, tx, brokenNum});
                        } 
                    }
                }
            }   
        }   
        return result;
    }
}
