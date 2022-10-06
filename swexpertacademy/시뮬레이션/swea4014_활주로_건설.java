package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class swea4014_활주로_건설 {

    static class FastIn {

        BufferedReader br;
        StringTokenizer st;

        public FastIn() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            if (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }

    static int ans, n, x;
    static int[][] map;
    static boolean[][] slope;

    public static void main(String[] args) {
        FastIn sc = new FastIn();
        int T;
        T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {

            ans = 0;
            n = sc.nextInt();
            x = sc.nextInt();
            map = new int[n][n];
            // 초기화
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                // 높은 곳 -> 작은 곳인 경우
                boolean flag = false;
                int continuous = 0;
                int pre = 0;
                for (int j = 0; j < n; j++) {

                    if (j == 0) {
                        continuous = 1;
                        pre = map[i][j];
                        continue;
                    }
                    if (pre == map[i][j]) {
                        continuous++;
                        if (flag && continuous >= x) {
                            flag = false;
                            continuous = 0;
                        }
                    } else {
                        // 낮은 곳 -> 높은 곳
                        if (pre < map[i][j]) {
                            if (pre - map[i][j] != -1 || continuous < x) {
                                flag = true;
                                break;
                            }
                            continuous = 1;
                            pre = map[i][j];
                        } else if (pre > map[i][j]) {
                            // 높은 곳 -> 낮은 곳
                            if (pre - map[i][j] != 1) {
                                flag = true;
                                break;
                            }
                            if (flag && continuous < x)
                                break;
                            flag = true;
                            continuous = 1;
                            pre = map[i][j];
                        }
                    }
                }
                if (flag) {
                    continue;
                }
                ans++;
            }

            for (int j = 0; j < n; j++) {
                // 실패 판정 트리거
                boolean flag = false;
                int continuous = 0;
                int pre = 0;
                for (int i = 0; i < n; i++) {
                    if (i == 0) {
                        continuous = 1;
                        pre = map[i][j];
                        continue;
                    }
                    if (pre == map[i][j]) {
                        continuous++;
                        if (flag && continuous >= x) {
                            flag = false;
                            continuous = 0;
                        }
                    } else {
                        // 낮은 곳 -> 높은 곳
                        if (pre < map[i][j]) {
                            if (pre - map[i][j] != -1 || continuous < x) {
                                flag = true;
                                break;
                            }
                            continuous = 1;
                            pre = map[i][j];
                        } else if (pre > map[i][j]) {
                            if (pre - map[i][j] != 1) {
                                flag = true;
                                break;
                            }
                            if (flag && continuous < x) {
                                flag = true;
                                break;
                            }
                            flag = true;
                            continuous = 1;
                            pre = map[i][j];
                        }
                    }
                }
                if (flag) {
                    continue;
                }
                ans++;
            }

            sb.append("#" + test_case + " " + ans + "\n");
        }
        System.out.println(sb);

    }

}
