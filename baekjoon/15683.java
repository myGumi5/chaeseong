import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
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

    static int[][] map;
    static int h, w;
    static boolean[][] visited;
    static int[] dx = { 1, 0, -1, 0 }; // 오, 아, 왼, 위
    static int[] dy = { 0, 1, 0, -1 };

    public static void main(String[] args) {
        FastIn sc = new FastIn();
        h = sc.nextInt();
        w = sc.nextInt();
        int blindSpotNum = 0;
        map = new int[h][w];
        visited = new boolean[h][w];
        LinkedList<int[]> l1 = new LinkedList<>();
        LinkedList<int[]> l2 = new LinkedList<>();
        
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0)
                    blindSpotNum++;
                else if (map[i][j] != 6) {
                    if (map[i][j] == 5)
                        l2.add(new int[] { map[i][j], i, j });
                    else
                        l1.add(new int[] { map[i][j], i, j });
                }
            }
        }

        // 4방위 카메라 먼저 탐색.
        while (!l2.isEmpty()) {
            int[] allRoundCamera = l2.poll();
            int y = allRoundCamera[1];
            int x = allRoundCamera[2];
            visited[y][x] = true;
            for (int i = 0; i < 4; i++) {
                int ty = y + dy[i];
                int tx = x + dx[i];
                while (isAvailable(ty, tx) && map[ty][tx] != 6) {
                    if (!visited[ty][tx] && map[ty][tx] == 0) {
                        visited[ty][tx] = true;
                        blindSpotNum--;
                    }
                    ty += dy[i];
                    tx += dx[i];
                }
            }
        }

        System.out.println(dfs(l1, blindSpotNum));
    }

    public static boolean isAvailable(int y, int x) {
        if (-1 < y && y < h && -1 < x && x < w)
            return true;
        return false;
    }

    public static int dfs(LinkedList<int[]> list, int bSN) {
        if (list.isEmpty())
            return bSN;
        int result = Integer.MAX_VALUE;
        LinkedList<int[]> visitedList = new LinkedList<>();
        int[] camera = list.poll();

        for (int i = 0; i < 4; i++) {// 위 오른쪽 아래 왼쪽
            bSN = watchCCTV(camera[0], camera[1], camera[2], i, visitedList, bSN);
            result = Math.min(result, dfs((LinkedList<int[]>) list.clone(), bSN));
            while(!visitedList.isEmpty()) {
                int[] temp = visitedList.poll();
                visited[temp[0]][temp[1]] = false;
                bSN++;
            }
        }
        return result;
    }

    public static int watchCCTV(int kind, int y, int x, int direction, LinkedList<int[]> visitedList, int bSN) {
        visited[y][x] = true;
//        visitedList.add(new int[] { y, x });
        if (kind == 1) { // 단방향
            int ty = y + dy[direction];
            int tx = x + dx[direction];
            while (isAvailable(ty, tx) && map[ty][tx] != 6) {
                if (!visited[ty][tx] && map[ty][tx] == 0) {
                    visited[ty][tx] = true;
                    visitedList.add(new int[] { ty, tx });
                    bSN--;
                }
                ty += dy[direction];
                tx += dx[direction];
            }
        } else if (kind == 2) { // 위 아래
            for (int i = 0; i < 2; i++) {
                int t = direction + i * 2;
                int ty = y + dy[t % 4];
                int tx = x + dx[t % 4];
                while (isAvailable(ty, tx) && map[ty][tx] != 6) {
                    if (!visited[ty][tx] && map[ty][tx] == 0) {
                        visited[ty][tx] = true;
                        visitedList.add(new int[] { ty, tx });
                        bSN--;
                    }
                    ty += dy[t % 4];
                    tx += dx[t % 4];
                }
            }
        } else if (kind == 3) { // 위 오른쪽
            for (int i = 0; i < 2; i++) {
                int t = direction + i;
                int ty = y + dy[t % 4];
                int tx = x + dx[t % 4];
                while (isAvailable(ty, tx) && map[ty][tx] != 6) {
                    if (!visited[ty][tx] && map[ty][tx] == 0) {
                        visited[ty][tx] = true;
                        visitedList.add(new int[] { ty, tx });
                        bSN--;
                    }
                    ty += dy[t % 4];
                    tx += dx[t % 4];
                }
            }
        } else if (kind == 4) { // 왼 위 오
            for (int i = 0; i < 3; i++) {
                int t = direction + i;
                int ty = y + dy[t % 4];
                int tx = x + dx[t % 4];
                while (isAvailable(ty, tx) && map[ty][tx] != 6) {
                    if (!visited[ty][tx] && map[ty][tx] == 0) {
                        visited[ty][tx] = true;
                        visitedList.add(new int[] { ty, tx });
                        bSN--;
                    }
                    ty += dy[t % 4];
                    tx += dx[t % 4];
                }
            }
        }
        return bSN;
    }

}
