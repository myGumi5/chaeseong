package acmicpc_1914;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void hanoi(int n, int start, int by, int end) {
        if (n == 1) {
            System.out.println(start + " " + end);
            return;
        }
        hanoi(n-1, start, end, by);
        System.out.println(start + " " + end);
        hanoi(n-1, by, start, end);
    }
    public static void main(String [] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        BigInteger p = new BigInteger("1");
        for(int i = 0; i < n; i++) {
            p = p.multiply(new BigInteger("2"));
        }
        p = p.subtract(new BigInteger("1"));
        System.out.println(p);
        if (n > 20)
            return;
        hanoi(n, 1, 2, 3);
    }
}
