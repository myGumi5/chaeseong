import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int answer = 0;
        HashSet<Integer> set = new HashSet();
        
        /* 
        원형 수열
        */
        
        for (int i = 0; i < elements.length; i++) { // 시작위치
            for (int j = 0; j < elements.length; j++) { // 원소 개수
                int count = 0;
                if (i > 0 && j == elements.length - 1) 
                    continue;
                for (int k = 0; k <= j; k++) {
                    count += elements[(i + k) % elements.length];
                }
                set.add(count);
            }
        }
        
        answer = set.size();
        return answer;
    }
}