import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class FastInput {
        BufferedReader br;
        StringTokenizer st;

        public FastInput() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            if (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static String[][] arr;

    static int isComposedOneKind(String str) {
        Integer t = null;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == ')')
                continue;
            if (t == null) {
                t = Integer.parseInt(str.substring(i, i+1));
                continue;
            }
            if (t != Integer.parseInt(str.substring(i, i+1)))
                return -1;
        }
        if (t != null)
            return t;
        return -1;
    }

    static String compressFile(int x, int y, int size) {
        if (size == 1)
            return arr[y][x];
        int halfSize = size / 2;
        int nextX = x + halfSize;
        int nextY = y - halfSize;
        String strLeftUp = compressFile(x, y, halfSize);
        String strRightUp = compressFile(nextX, y, halfSize);
        String strLeftDown = compressFile(x, nextY, halfSize);
        String strRightDown = compressFile(nextX, nextY, halfSize);
        int rLU = isComposedOneKind(strLeftUp);
        int rLD = isComposedOneKind(strLeftDown);
        int rRD = isComposedOneKind(strRightUp);
        int rRU = isComposedOneKind(strRightDown);
        if (rLU != -1 && rLU == rLD && rRD == rRU && rLU == rRU)
            return strLeftUp;
        else
            return "(" + strLeftUp + strRightUp + strLeftDown + strRightDown + ")";
    }

    public static void main(String[] args) {
        FastInput st = new FastInput();
        int n = st.nextInt();
        arr = new String[n][n];

        for (int i = 0; i < n; i++) {
            String s = st.next();
            for (int j = 0; j < n; j++) {
                arr[n - 1 - i][j] = s.substring(j, j + 1);
            }
        }
        System.out.println(compressFile(0, n - 1, n));
    }

}
