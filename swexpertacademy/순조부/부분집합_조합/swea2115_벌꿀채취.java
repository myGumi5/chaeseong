package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class swea2115_벌꿀채취 {
    static class FastIn {
        BufferedReader br;
        StringTokenizer st;

        FastIn() {
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

    public static int ans;
    public static int n, m, c;
    public static int[][] arr;
    public static int[][] vArr;
    public static boolean[][] v;

    public static int milling(int cnt, int amt, int y, int x, int value) {
        int result = 0;
        if (amt > c)
            return -1;
        if (cnt == m) {
            return value;
        }

        int cx = x + cnt;
        int amount = arr[y][cx];
        // 미포함
        result = Math.max(result, milling(cnt + 1, amt, y, x, value));
        // 포함
        if (amount + amt <= c) {
            result = Math.max(result, milling(cnt + 1, amount + amt, y, x, value + amount * amount));
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        FastIn sc = new FastIn();
        int T;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            ans = 0;
            n = sc.nextInt();
            m = sc.nextInt();
            c = sc.nextInt();
            arr = new int[n][n];
            v = new boolean[n][n];

            int vSize = n - m + 1;
            vArr = new int[n][vSize];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            // 벌통 선택 최대 이익 배열 초기화
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < vSize; j++) {
                    vArr[i][j] = milling(0, 0, i, j, 0);
                }
            }
            // 결과 구하기
            for (int y1 = 0; y1 < n; y1++) {
                for (int y2 = y1; y2 < n; y2++) {
                    for (int x1 = 0; x1 < vSize; x1++) {
                        int s = 0;
                        if (y1 == y2) {
                            s = x1 + m;
                            if (s >= vSize)
                                break;
                        }
                        for (int x2 = s; x2 < vSize; x2++) {
                            int valueSum = vArr[y1][x1] + vArr[y2][x2];
                            int a = ans;
                            ans = Math.max(ans, valueSum);
                        }
                    }
                }
            }
            bw.write("#" + test_case + " " + ans + "\n");
        }
        bw.flush();
        bw.close();
    }
}
