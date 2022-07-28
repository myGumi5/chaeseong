import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        int n;
        int l;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        
        int _i = 0;
        while (l < 101) {
            int t = n / l - _i;
            int s = t;
            for (int i = 1; i < l; i++)
                s += t + i;
            if (s > n && t >= 1)
                _i++;
            else if (s == n) {
                System.out.print(t);
                for (int k = 1; k < l; k++) {
                    System.out.print(" " + (t + k));
                }
                return;
            } else {
                _i = 0;
                l++;
            }
        }
        System.out.println(-1);
    }
}
