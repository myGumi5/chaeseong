import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int max = -1;
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[501][501];
        for (int i = 1; i < n + 1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (i != 1) {
                    if (j == 1)
                        arr[i][j] += arr[i - 1][j];
                    else if (j == i) {
                        arr[i][j] += arr[i - 1][j - 1];
                    } else
                        arr[i][j] += Math.max(arr[i - 1][j], arr[i - 1][j - 1]);
                }
                max = Math.max(max, arr[i][j]);
            }
        }
        System.out.println(max);
    }
}
