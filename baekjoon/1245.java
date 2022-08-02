import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // visitCheck가 true면 visited를 true로
    public static void dfs(boolean visitCheck, int n, int x, int y) {
        if (visited[y][x] == 1)
            return;
        if (visitCheck) 
            visited[y][x] = 1;
        else
            visited[y][x] = -1;
        int [] dx = {-1, -1, -1,  0, 0, 1, 1, 1};
        int [] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
        
        for (int i = 0; i < dx.length; i++) {
            int _x = x + dx[i];
            int _y = y + dy[i];
            //범위 검사
            if (_x < 0 || _x >= arr[0].length || _y < 0 || _y >= arr.length)
                continue;
            int t_n = arr[_y][_x];
            //탐색 할 곳이 방문안됬을 경우
            if (visited[_y][_x] != 1) {
                if (t_n == n && visited[_y][_x] != -1) dfs(visitCheck, t_n, _x, _y);
                if (visitCheck && t_n == n) dfs(true, t_n, _x, _y);
                if (t_n < n)
                    dfs(true, t_n, _x, _y);   
            }
            
        }
    }

    static int[][] arr = null;
    static int[][] visited = null;

    public static void main(String[] args) throws IOException {
        int n, m, result = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new int[n][m];
        
        //초기화
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        //1차 dfs
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(false, arr[i][j], j, i);
            }
        }
        //2차 dfs -> 산봉우리 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == -1) {
                    result++;
                    dfs(true, arr[i][j], j, i);
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.println(Arrays.toString(visited[i]));
//        }
        System.out.println(result);
    }

}
