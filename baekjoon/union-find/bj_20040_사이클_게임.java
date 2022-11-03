package baekjoon;

import java.util.Scanner;

public class bj_20040_사이클_게임 {
    static int n, m;
    static int[] arr;
    
    static int find(int num) {
        if (arr[num] == num)
            return num;
        return find(arr[num]);
    }
    
    static void union(int a, int b, int idx) {
        int pa = find(a);
        int pb = find(b);
        //사이클 완성
        if (pa == pb) {
            System.out.println(idx);
            System.exit(0);
        }
        arr[a] = pb;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();
        arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        
        for (int i = 1; i <= m; i++) {
            union(sc.nextInt(), sc.nextInt(), i);
        }
        System.out.println(0);
    }
}
