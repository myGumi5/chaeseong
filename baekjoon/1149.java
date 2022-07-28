import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int n = Integer.parseInt(br.readLine());
        int [][] arr = new int[n+1][3];
        for (int i = 1; i < n+1; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                //입력 받음
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (i != 1) {
                    //다른 색상 선택
                    int t1 = (j + 1) % 3;
                    int t2 = (j + 2) % 3;
                    //다른 색상 중 작은 거 선택해 비용 합침
                    arr[i][j] += Math.min(arr[i-1][t1], arr[i-1][t2]);
                }
            }
        }
        System.out.println(Math.min(Math.min(arr[n][0], arr[n][1]), arr[n][2]));
    }
}
