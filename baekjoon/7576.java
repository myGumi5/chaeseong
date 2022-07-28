import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static int[][] box = null;
    private static boolean[][] visited = null;

    public static void main(String[] args) throws IOException {
        int n, m;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        box = new int[n][m];
        visited = new boolean[n][m];

        ArrayList<int []> starts = new ArrayList<int[]>();
        int count = 0;
        // 박스 구성
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int t = Integer.parseInt(st.nextToken());
                box[j][i] = t;
                if (t == 1) {
                    //j: y, i: x
                    starts.add(new int[] {j, i});
                }
                if (t == -1) {
                    count++;
                }
            }
        }
        System.out.println(bfs(starts, count));
    }

    private static int bfs(ArrayList<int[]> starts, int count) {
        Queue<int[]> q = new LinkedList<int[]>();
        for (int[] start : starts) {
            //index 0 : y, index 1 : x
            int y = start[0];
            int x = start[1];
            visited[y][x] = true;
            q.offer(new int[] { y, x, 0 });
            count++;
        }
        int[] tx = { -1, 1, 0, 0 };
        int[] ty = { 0, 0, -1, 1 };
        int[] c_info = null;
        while (!q.isEmpty()) {
            c_info = q.poll();
            for (int i = 0; i < 4; i++) {
                int n_x = c_info[1] + tx[i];
                int n_y = c_info[0] + ty[i];
                //배열 범위 체크
                if (-1 < n_x && n_x < visited[0].length && -1 < n_y && n_y < visited.length) {
                    //방문 안했을 경우
                    if (!visited[n_y][n_x]) {
                        //벽일 경우 통과
                        if (box[n_y][n_x] != -1) {
                            visited[n_y][n_x] = true;
                            count++;
                            q.add(new int[] { n_y, n_x, c_info[2] + 1 });
                        } 
                    }
                }
            }
        }
        if (count == box.length*box[0].length) return c_info[2];
        return -1;
    }
}