import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    
    static ArrayList<Integer>[] arr;
    static int [] visited = null;
    static int count = 0;
    public static void dfs(int current) {
        if (visited[current] == 1)
            return;
        visited[current] = 1;
        // System.out.println("c : " + current);
        if (arr[current] == null)
            return;
        for (int i = 0; i < arr[current].size(); i++) {
            int next = arr[current].get(i);
            // System.out.println("next : " + next);
            if (visited[next] == 0) {
                count++;
                dfs(next);
            }
        }
    }
    
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int v_num = Integer.parseInt(br.readLine());
        arr = new ArrayList[n+1];
        visited = new int[n+1];
        
        for (int i = 0; i < v_num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (arr[s] == null)
                arr[s] = new ArrayList<Integer>();
            if (arr[e] == null)
                arr[e] = new ArrayList<Integer>();
            arr[s].add(e);
            arr[e].add(s);
        }
        
        dfs(1);
        System.out.println(count);
    }
}