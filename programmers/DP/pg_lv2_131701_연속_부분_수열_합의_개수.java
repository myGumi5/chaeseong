import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        HashSet<Integer> set = new HashSet();

        //DP 풀이
        int[][] dp = new int[elements.length+1][elements.length+1];
        for (int i = 1; i <= elements.length; i++) { // 원소 개수
            for (int j = 1; j <= elements.length; j++) { // 시작 위치
                dp[i][j] = dp[i - 1][j] + elements[(i + j) % elements.length];
                set.add(dp[i][j]);
            }
        }
        
        answer = set.size();
        return answer;
    }
}