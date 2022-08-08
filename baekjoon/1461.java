import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        FastInput st = new FastInput();
        
        int n = st.nextInt();
        int m = st.nextInt();
        int sum = 0;
        int onewayValue = 0;
//        ArrayList<Integer> minus = new ArrayList<>();
//        ArrayList<Integer> plus = new ArrayList<>();
        PriorityQueue<Integer> minus = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> plus = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < n; i++) {
            int t = st.nextInt();
            if (t < 0)
                minus.add(t * -1);
            else plus.add(t);
        }
        //정렬
//        Collections.sort(minus, Collections.reverseOrder());
//        Collections.sort(plus, Collections.reverseOrder());
        
        if (minus.size() < 1)
            sum -= plus.peek();
        else if (plus.size() < 1)
            sum -= minus.peek();
        else sum -= Math.max(minus.peek(), plus.peek());
        
        while(minus.size() > 0 || plus.size() > 0) {
            if (minus.size() > 0) {
            sum += minus.poll() * 2;
            for (int i = 1; i < m; i++) {
                if (minus.size() > 0)
                    minus.poll();
                }
            }
            if (plus.size() > 0) {
                sum += plus.poll() * 2;
                for (int i = 1; i < m; i++) {
                    if (plus.size() > 0)
                        plus.poll();
                }
            }
        }
        
//        
//        while(minus.size() > 0 || plus.size() > 0) {
//            int walkMinus = 0;
//            int walkPlus = 0;
//            ArrayList<Integer> deletedArr = null;
//            for(int i = 0; i < m; i++) {
//                if (minus.size() > i)
//                    walkMinus += minus.get(i);
//                if (plus.size() > i)
//                    walkPlus += plus.get(i);
//            }
//            if (walkMinus > walkPlus) {
//                deletedArr = minus;
//            } else {
//                deletedArr = plus;
//            }
//            sum += deletedArr.get(0) * 2;
//            for(int i = 0; i < m; i++) {
//                if (deletedArr.size() > 0) { 
//                    deletedArr.remove(0);
//                }
//            }
//        }
//        sum -= onewayValue;
        System.out.println(sum);
    }
    
    static class FastInput{
        BufferedReader br = null;
        StringTokenizer st = null;
        public FastInput() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        String next() {
            while(st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch(Exception e) {
                    e.printStackTrace();                    
                }
            }
            return st.nextToken();
        }
        
        public int nextInt() {
            return Integer.parseInt(next());
        }
        
    }
}