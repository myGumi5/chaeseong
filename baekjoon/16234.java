import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int [] dx = {-1, 1, 0, 0};
    static int [] dy = {0, 0, -1, 1};
    static int[][] visited;
    static int [][] arr;
    static int n, l, r;
    
    public static boolean bfs(int sx, int sy, int day) {
        Queue<int []> q = new LinkedList<>();
        List<int []> list = new LinkedList<>();
        int sum = 0;
        
        q.offer(new int[] {sx, sy});
        visited[sx][sy] = day;
        while (!q.isEmpty()) {
            int[] cPosition = q.poll();
            list.add(cPosition);
            sum += arr[cPosition[0]][cPosition[1]];
            
            for (int i = 0; i < 4; i++) {
                int x = cPosition[0] + dx[i];
                int y = cPosition[1] + dy[i];
                //범위 판단
                if (x < 1 || x > n || y < 1 || y > n)
                    continue;
                
                int temp = Math.abs(arr[cPosition[0]][cPosition[1]] - arr[x][y]); 
                //방문했거나 or 국경선 열리지 않은 경우 생략
                if (visited[x][y] == day || temp < l || temp > r)
                    continue;
                int [] nP = {x, y};
//                System.out.println(x + " "+ y);
                //방문처리
                q.offer(nP);
                visited[x][y] = day; 
            }
        }
        if (list.size() < 2)
            return false;
        int avg = sum / list.size();
        for(int i = 0; i < list.size(); i++) {
            int cx = list.get(i)[0];
            int cy = list.get(i)[1];
            
            arr[cx][cy] = avg;
        }
        return true;
    }
    
    public static void main(String[] args) throws IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         StringTokenizer st = new StringTokenizer(br.readLine());
         
         n = Integer.parseInt(st.nextToken());
         l = Integer.parseInt(st.nextToken());
         r = Integer.parseInt(st.nextToken());
         arr = new int[n+1][n+1];
         visited = new int[n+1][n+1]; 
         for (int i = 1; i < n+1; i++) {
             st = new StringTokenizer(br.readLine());
             for (int j = 1; j < n+1; j++) {
                 arr[i][j] = Integer.parseInt(st.nextToken());
             }
         }

         int day = 0;
         boolean isMoved = false;
         do {
             day++;
             isMoved = false;
             for (int i = 1; i < n+1; i++) {
                 for (int j = 1; j < n+1; j++) {
                     if (visited[i][j] == day)
                         continue;
                     isMoved = isMoved | bfs(i, j, day);
                 }
             }
//             System.out.println("_____________________________");
//             for (int k = 1; k < n+1; k++) {
//                 System.out.println(Arrays.toString(arr[k]));
//             }
//             System.out.println("______________________________");
//             for (int k = 1; k < n+1; k++) {
//                 System.out.println(Arrays.toString(visited[k]));
//             }
         } while (isMoved);
         System.out.println(day-1);
    }

}
