import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
    long c;
    ArrayList<long []> ls = new ArrayList();
    Map<Integer, Long> map = new HashMap<Integer, Long>();   
        
    public static void main(String[] args) throws IOException {
        Main m = new Main();

        long a, b;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        m.c = Integer.parseInt(st.nextToken());

        System.out.println(m.sol(a, b) % m.c);
    }

    private long sol(long a, long b) {
        if (b == 0)
            return 1;
        if (b == 1) 
            return a;
        long t;
        for (long[] is : ls) {
            if (is[0] == b) {
                return is[1];
            }   
        }
        if (b%2 == 0)
            t = (sol(a, b/2)%c) * (sol(a, b/2)%c) % c;
        else
            t = (sol(a, b/2)%c) * (sol(a, (b/2 + 1))%c) %c;
        ls.add(new long[]{b, t});
        return t;
    }
}

