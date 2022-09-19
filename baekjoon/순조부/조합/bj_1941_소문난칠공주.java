package gold;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class bj_1941_소문난칠공주 {

    static int ans = 0;
    static char[][] arr = new char[5][5];
    static int[][] v = new int[5][5];
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, 1, 0, -1 };
    static int dfsCnt = 0;

    public static void dfs(int n, int x, int y, int[][] v2) {
        if (++dfsCnt == 7) {
            ans++;
            dfsCnt = 0;
            return;
        }
        for (int i = 0; i < 4; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];

            if (-1 < ty && ty < 5 && -1 < tx && tx < 5 && v2[ty][tx] == 1) {
                v2[ty][tx] = 2;
                dfs(n + 1, tx, ty, v2);
            }
        }
    }

    static void combination(int cnt, int x, int y, int preX, int preY, int sCnt, int yCnt) {
        if (cnt == 7) {
            dfsCnt = 0;
            int[][] v2 = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    v2[i][j] = v[i][j];
                }
            }

            v2[preY][preX] = 2;
            dfs(1, preX, preY, v2);
            return;
        }

        for (int i = y; i < 5; i++) {
            int j = 0;
            if (i == y)
                j = x;
            for (; j < 5; j++) {
                if (v[i][j] == 0) {
                    v[i][j] = 1;
                    int ny = i;
                    int nx = j + 1;
                    if (nx == 5) {
                        if (ny < 4) {
                            ny = ny + 1;
                            nx = 0;
                        }
                    }
                    if (arr[i][j] == 'Y' && yCnt + 1 < 4) {
                        combination(cnt + 1, nx, ny, j, i, sCnt, yCnt + 1);
                    } else if (arr[i][j] == 'S') {
                        combination(cnt + 1, nx, ny, j, i, sCnt + 1, yCnt);
                    }
                    v[i][j] = 0;
                }

            }
        }

    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < 5; j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        combination(0, 0, 0, 0, 0, 0, 0);

        System.out.println(ans);

    }

}
