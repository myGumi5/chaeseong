import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    /*
     * 1 <= n <= h H(n) = D(n) + U(h - n + 1) D => 석순 배열 U => 종유석 배열
     * 
     */
    public static void main(String[] args) throws IOException {
        int[] U = new int[500_001];
        int[] D = new int[500_001];
        int min = Integer.MAX_VALUE;
        int count = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            int t = Integer.parseInt(br.readLine());
            if (i % 2 == 0) 
                D[t]++;
            else
                U[t]++;
        }
        for (int i = h-1; i > 0; i--) {
            D[i] += D[i+1];
            U[i] += U[i+1];
        }
        for (int i = 1; i <= h; i++) {
            int t = D[i] + U[h - i + 1];
            if (min > t) {
                min = t;
                count = 1;
            }
            else if (min == t) {
                count++;
            }
        }
        System.out.println(min + " " + count);
    }

}
