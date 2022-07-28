import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] v = new int[n + 1];
        int[] w = new int[n + 1];
        int[][] arr = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // 넣으려는 무게
                int c_w = w[i];
                // 넣으려는 가치
                int c_v = v[i];
                if (j < c_w)
                    arr[i][j] = arr[i - 1][j];
                else
                    arr[i][j] = Math.max(arr[i - 1][j], c_v + arr[i - 1][j - c_w]);
            }
        }
        System.out.println(arr[n][k]);
    }
}
