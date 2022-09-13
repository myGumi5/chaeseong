package gold;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class bj_17281_야구 {

    static int N;
    static int[][] player;
    static int[] lineup = new int[10];
    static boolean[] v = new boolean[10];
    static int ans = Integer.MIN_VALUE;

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

    static int baseball() {
        int score = 0;
        int curPlayer = 1;
        for (int in = 0; in < N; in++) {
            int outCnt = 0;
            boolean[] base = new boolean[4];
            while (outCnt < 3) {
                int ability = player[in][lineup[curPlayer]];
                switch (ability) {
                case 0:
                    outCnt++;
                    break;
                case 1:
                    if (base[3]) {
                        score++;
                        base[3] = false;
                    }
                    if (base[2]) {
                        base[3] = true;
                        base[2] = false;
                    }
                    if (base[1]) {
                        base[2] = true;
                        base[1] = false;
                    }
                    base[1] = true;
                    break;
                case 2:
                    if (base[3]) {
                        score++;
                        base[3] = false;
                    }
                    if (base[2]) {
                        score++;
                        base[2] = false;
                    }
                    if (base[1]) {
                        base[3] = true;
                        base[1] = false;
                    }
                    base[2] = true;
                    break;
                case 3:
                    if (base[3]) {
                        score++;
                        base[3] = false;
                    }
                    if (base[2]) {
                        score++;
                        base[2] = false;
                    }
                    if (base[1]) {
                        score++;
                        base[1] = false;
                    }
                    base[3] = true;
                    break;
                case 4:
                    if (base[3]) {
                        score++;
                        base[3] = false;
                    }
                    if (base[2]) {
                        score++;
                        base[2] = false;
                    }
                    if (base[1]) {
                        score++;
                        base[1] = false;
                    }
                    score++;
                    break;
                }
                if (curPlayer == 9) {
                    curPlayer = 1;
                } else {
                    curPlayer++;
                }
            }
        }
        return score;
    }

    static int c = 0;

    public static void dfs(int n) {
        if (n == 10) {
            ans = Math.max(ans, baseball());
            return;
        }
        for (int i = 1; i < 10; i++) {
            if (!v[i]) {
                lineup[i] = n;
                v[i] = true;
                dfs(n + 1);
                v[i] = false;
                lineup[i] = 0;
            }

        }
    }

    public static void main(String[] args) {
        FastIn sc = new FastIn();

        N = sc.nextInt();
        player = new int[N][10];

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < 10; j++) {
                player[i][j] = sc.nextInt();
            }
        }
        lineup[4] = 1;
        v[4] = true;
        dfs(2);
        System.out.println(ans);
    }

}
