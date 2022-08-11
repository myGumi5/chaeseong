import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class FastInput {
        BufferedReader br;
        StringTokenizer st;

        public FastInput() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
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

    static int[][] arr;
    static int[][] visited;

    static void getSafeAreaNum(int y, int x, int h) {
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = h;
        q.add(new int[] { y, x });

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        while (!q.isEmpty()) {
            int[] cP = q.poll();
            int cy = cP[0];
            int cx = cP[1];

            for (int i = 0; i < 4; i++) {
                int ty = cy + dy[i];
                int tx = cx + dx[i];

                if (-1 < ty && ty < arr.length && -1 < tx && tx < arr.length) {
                    if (visited[ty][tx] != h) {
                        visited[ty][tx] = h;
                        q.add(new int[] { ty, tx });
                    }
                }
            }
        }
    }
    
    static void bfs(int y, int x, int h) {
        Queue<int[]> q = new LinkedList<>();
        visited[y][x] = h;
        q.add(new int[] { y, x });

        int[] dx = { -1, 1, 0, 0 };
        int[] dy = { 0, 0, -1, 1 };

        while (!q.isEmpty()) {
            int[] cP = q.poll();
            int cy = cP[0];
            int cx = cP[1];

            for (int i = 0; i < 4; i++) {
                int ty = cy + dy[i];
                int tx = cx + dx[i];

                if (-1 < ty && ty < arr.length && -1 < tx && tx < arr.length) {
                    if (visited[ty][tx] < h && arr[ty][tx] <= h) {
                        visited[ty][tx] = h;
                        q.add(new int[] { ty, tx });
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        FastInput sc = new FastInput();

        int result = 1;
        int n = sc.nextInt();
        int maxH = 0;
        arr = new int[n][n];
        visited = new int[n][n];

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                arr[y][x] = sc.nextInt();
                maxH = Math.max(maxH, arr[y][x]);
            }
        }

        for (int i = 1; i <= maxH; i++) {
            for (int y = 0; y < arr.length; y++) {
                for (int x = 0; x < arr.length; x++) {
                    if (visited[y][x] < i && arr[y][x] <= i)
                        bfs(y, x, i);
                }
//                System.out.println(Arrays.toString(visited[y]));
            }
            int t_result = 0;
            for (int y = 0; y < arr.length; y++) {
                for (int x = 0; x < arr.length; x++) {
                    if (visited[y][x] != i) {
                        getSafeAreaNum(y, x, i);
                        t_result++;
                    }
                }
            }
            result = Math.max(result, t_result);
        }
        System.out.println(result);
    }

}
