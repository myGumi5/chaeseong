package gold;
import java.util.Scanner;

public class bj_3980_선발명단 {

    static int ans = 0;
    static int[][] player;
    static int[] positions;
    static boolean[] v;

    public static void permutation(int n) {

        if (n == 12) {
            int sum = 0;
            for (int i = 1; i <= 11; i++) {
                sum += player[positions[i]][i];
            }
            ans = Math.max(ans, sum);
            return;
        }

        for (int i = 1; i <= 11; i++) {
            if (!v[i] && player[i][n] != 0) {
                v[i] = true;
                //positions에 선수번호 지정
                positions[n] = i;
                permutation(n + 1);
                positions[n] = 0;
                v[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int test_case = 0; test_case < T; test_case++) {
            ans = 0;
            player = new int[12][12];
            positions = new int[12];
            v = new boolean[12];

            for (int i = 1; i <= 11; i++) {
                for (int j = 1; j <= 11; j++) {
                    player[i][j] = sc.nextInt();
                }
            }

            permutation(1);
            System.out.println(ans);
        }
    }

}
