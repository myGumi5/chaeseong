package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

enum Numbers {
    ZRO,
    ONE,
    TWO,
    THR,
    FOR,
    FIV,
    SIX,
    SVN,
    EGT,
    NIN
}

public class swea1221_GNS {

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
    
    public static void main(String args[]) throws Exception
    {
        FastIn sc = new FastIn();
        int T;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        T=sc.nextInt();
        
        for(int test_case = 1; test_case <= T; test_case++)
        {
            sc.next();
            int n = sc.nextInt();
            PriorityQueue<Numbers> pQ = new PriorityQueue<>();
            
            for (int i = 0; i < n; i++) {
                pQ.add(Numbers.valueOf(sc.next()));
            }
            
            bw.write("#" + test_case + "\n");
            while(!pQ.isEmpty()) {
                bw.write(pQ.poll() + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }
    
}