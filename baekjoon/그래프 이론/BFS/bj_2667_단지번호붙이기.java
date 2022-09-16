package gold;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class bj_2667_단지번호붙이기 {

    static int n;
    static int[][] arr;
    static boolean[][] v;
    static LinkedList<Integer> l;
    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    static int dfs(int y, int x, int result) {
        if (v[y][x])
            return -1;
        v[y][x] = true;

        for (int i = 0; i < 4; i++) {
            int ty = y + dy[i];
            int tx = x + dx[i];

            if (-1 < ty && ty < n && -1 < tx && tx < n) {
                if (!v[ty][tx] && arr[ty][tx] == 1) {
                    result = dfs(ty, tx, result + 1);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        arr = new int[n][n];
        v = new boolean[n][n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < n; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        l = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!v[i][j] && arr[i][j] == 1) {
                    l.add(dfs(i, j, 1));
                }
            }
        }
        System.out.println(l.size());
        Collections.sort(l);
        for (int a : l) {
            System.out.println(a);
        }
    }

}
