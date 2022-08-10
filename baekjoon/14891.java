import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    static final int BOTH = 0;
    static final int LEFT = 1;
    static final int RIGHT = 2;

    static LinkedList<Integer>[] gears = new LinkedList[4];

    public static void main(String[] args) {
        FastIn st = new FastIn();
        int result = 0;
        for (int i = 0; i < 4; i++) {
            gears[i] = new LinkedList<Integer>();
            int input = st.nextInt();
            for (int j = 0; j < 8; j++) {
                if (input > 0) {
                    gears[i].add(0, input % 10);
                    input /= 10;
                } else gears[i].add(0, 0);       
            }
        }
        int k = st.nextInt();
        for (int i = 0; i < k; i++) {
            rotateGear(st.nextInt() - 1, st.nextInt(), BOTH);
        }
        if (gears[0].peek() == 1) result += 1;
        if (gears[1].peek() == 1) result += 2;
        if (gears[2].peek() == 1) result += 4;
        if (gears[3].peek() == 1) result += 8;
        System.out.println(result);
    }

    public static void rotateGear(int gearNum, int orientation, int toCallGear) {
        // 12시 : 0, 3시 : 2, 6시 : 4, 9시 : 6
        int currentLeft, currentRight, leftGear, rightGear;
        leftGear = 0; rightGear = 0;
        currentLeft = gears[gearNum].get(6);
        currentRight = gears[gearNum].get(2);
        if (gearNum - 1 > -1)
            leftGear = gears[gearNum - 1].get(2);
        if (gearNum + 1 < 4)
            rightGear = gears[gearNum + 1].get(6);
        // 기어 회전
        if (orientation > 0) { // 시계
            //맨 뒤를 맨 앞으로
            gears[gearNum].addFirst(gears[gearNum].pollLast());
        } else { // 반시계
            //맨 앞을 맨 뒤
            gears[gearNum].addLast(gears[gearNum].pollFirst());
        }
        // 왼쪽 확인
        if (gearNum - 1 > -1 && (toCallGear == BOTH || toCallGear == LEFT)) {
            if (currentLeft != leftGear)
                rotateGear(gearNum - 1, orientation * -1, LEFT);
        }
        // 오른쪽 확인
        if (gearNum + 1 < 4 && (toCallGear == BOTH || toCallGear == RIGHT)) {
            if (currentRight != rightGear)
                rotateGear(gearNum + 1, orientation * -1, RIGHT);
        }
    }

    static class FastIn {

        BufferedReader br;
        StringTokenizer st;

        public FastIn() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

    }
}
