import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        //끝 지점이 빠른 순으로 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);
        //마지막으로 검사한 미사일 끝 지점
        int start = 0;
        for (int i = 0; i < targets.length; i++) {
            //현재 검사 중 미사일 출발지
            int s = targets[i][0];
            //현재 검사 중 미사일 도착지
            int e = targets[i][1];
            
            //이미 검사한 걸로 커버 가능
            if (s < start) {
                continue;
            } else {// 새로운 요격미사일 필요
                start = e;
                answer++;
            }
        }
        
        return answer;
    }
}