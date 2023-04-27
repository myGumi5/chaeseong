package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class bj_2294_µ¿Àü_2 {

	static int n, k, minCoinNum = Integer.MAX_VALUE;
	static StringTokenizer st;
	static HashSet coins = new HashSet<Integer>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			coins.add(Integer.parseInt(st.nextToken()));
		}
		Integer[] coinsArr = new Integer[coins.size()];
		coins.toArray(coinsArr);
		
		int[][] dp = new int[coinsArr.length + 1][k + 1];
		
		for (int i = 0; i <= coinsArr.length; i++) {
			for (int j = 1; j <= k; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 1; i <= coinsArr.length; i++) {
			for (int j = 1; j <= k; j++) {
				int coin = coinsArr[i - 1];
				dp[i][j] = dp[i - 1][j];
				if (j >= coin && dp[i][j - coin] != Integer.MAX_VALUE) {
					dp[i][j] = Math.min(dp[i][j], dp[i][j - coin] + 1);
				}
			}
		}
		if (dp[coinsArr.length][k] == Integer.MAX_VALUE) dp[coinsArr.length][k] = -1;
		System.out.println(dp[coinsArr.length][k]);
	}

}
