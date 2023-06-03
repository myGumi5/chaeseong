import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        /*
        야근 피로도 : 야근 시작한 시점 에서 남은 일 작업량 제곱하여 더한 값
        모두 평균과 가까울 때 가장 야근지수가 작음
        => 큰 거 순으로 - 1 계속 반복.
        */
        
        PriorityQueue<Integer> pQ = new PriorityQueue(Collections.reverseOrder());
        
        for(int work: works) {
            pQ.add(work);
        }
        
        while (n > 0 && !pQ.isEmpty()) {
            n--;
            int now = pQ.poll();
            if (now > 1)
                pQ.add(--now);
        }
        
        while (!pQ.isEmpty()) {
            int current = pQ.poll();
            answer += current * current;
            //System.out.print(current + " ");
        }
        
        return answer;
    }
}