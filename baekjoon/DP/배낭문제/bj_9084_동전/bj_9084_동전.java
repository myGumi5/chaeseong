package gold;

import java.util.Arrays;
import java.util.Scanner;

public class bj_9084_동전 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int test_case = 0; test_case < T; test_case++) {
            int n = sc.nextInt();
            int[] value = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                value[i] = sc.nextInt();
            }
            int money = sc.nextInt();
            int[] dp = new int[money + 1];
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= money; j++) {
                    if (j == value[i]) dp[j]++;
                    if (j > value[i]) dp[j] = dp[j - value[i]] + dp[j];
                }
            }
                System.out.println(Arrays.toString(dp));
            System.out.println(dp[money]);
        }

    }

}
