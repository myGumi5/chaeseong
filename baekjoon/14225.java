import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Set<Integer> s = new HashSet<Integer>();
    static int n;
    static int [] arr = null;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        arr = new int[20];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        solv(0, 0);
        for (int i = 1; i < 2_000_000; i++) {
            if (!s.contains(i)) {
                System.out.println(i);
                return;
            }
        }
    }

    private static void solv(int v, int sum) {
        s.add(sum);
        for (int i = v; i < n; i++) {
            solv(i+1, sum+arr[i]);
        }
    }

}
