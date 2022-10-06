package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class swea_무선_충전 {

    static class FastIn {
        
        BufferedReader br;
        StringTokenizer st;
        
        public FastIn() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        public String next() {
            if (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        
        public int nextInt() {
            return Integer.parseInt(next());
        }
        
    }
    
    final static int MAPSIZE = 11;
    static int m, a, ans;
    static int[] aMoveList;
    static int[] bMoveList;
    static int[] chargeAmount;
    static Location[][] map;
    static int[] dy = { 0, -1, 0, 1, 0 };
    static int[] dx = { 0, 0, 1, 0, -1 };

    static class Location {
        private boolean[] isLinkedCharger;

        Location(int a) {
            isLinkedCharger = new boolean[a];
        }

        public void setIsLinkedCharger(int idx) {
            isLinkedCharger[idx] = true;
        }

        public boolean[] getIsLinkedCharger() {
            return isLinkedCharger;
        }
    }

    static void move(int n, int ax, int ay, int bx, int by, int result) {
        if (n == m) {
            ans = Math.max(ans, result);
            return;
        }

        List<Integer> aAvailableCharger = new LinkedList<>();
        List<Integer> bAvailableCharger = new LinkedList<>();

        ax += dx[aMoveList[n]];
        ay += dy[aMoveList[n]];
        bx += dx[bMoveList[n]];
        by += dy[bMoveList[n]];

        boolean[] aLocationCList = map[ay][ax].getIsLinkedCharger();
        boolean[] bLocationCList = map[by][bx].getIsLinkedCharger();

        for (int i = 0; i < a; i++) {
            if (aLocationCList[i])
                aAvailableCharger.add(i);
            if (bLocationCList[i])
                bAvailableCharger.add(i);
        }

        if (aAvailableCharger.isEmpty())
            aAvailableCharger.add(-1);
        if (bAvailableCharger.isEmpty())
            bAvailableCharger.add(-1);

        int tresult = 0;
        for (int i = 0; i < aAvailableCharger.size(); i++) {
            for (int j = 0; j < bAvailableCharger.size(); j++) {
                int cresult = result;
                int aLinkedChargerIdx = aAvailableCharger.get(i);
                int bLinkedChargerIdx = bAvailableCharger.get(j);
                if (aLinkedChargerIdx == bLinkedChargerIdx) {
                    if (aLinkedChargerIdx != -1)
                        cresult += chargeAmount[aLinkedChargerIdx];
                } else {
                    if (aLinkedChargerIdx != -1)
                        cresult += chargeAmount[aLinkedChargerIdx];
                    if (bLinkedChargerIdx != -1)
                        cresult += chargeAmount[bLinkedChargerIdx];
                }
                tresult = Math.max(tresult, cresult);
            }
        }
        move(n + 1, ax, ay, bx, by, tresult);
    }

    public static void main(String[] args) {
        FastIn sc = new FastIn();
        int T;
        T = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int test_case = 1; test_case <= T; test_case++) {
            ans = 0;
            m = sc.nextInt() + 1;
            a = sc.nextInt();
            aMoveList = new int[m];
            bMoveList = new int[m];
            chargeAmount = new int[a];
            map = new Location[MAPSIZE][MAPSIZE];

            for (int i = 1; i < m; i++) {
                aMoveList[i] = sc.nextInt();
            }
            for (int i = 1; i < m; i++) {
                bMoveList[i] = sc.nextInt();
            }
            for (int i = 1; i < MAPSIZE; i++) {
                for (int j = 1; j < MAPSIZE; j++) {
                    map[i][j] = new Location(a);
                }
            }

            for (int i = 0; i < a; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int c = sc.nextInt();
                int p = sc.nextInt();
                chargeAmount[i] = p;

                int sy = y - c;
                int ey = y + c + 1;
                if (sy < 1)
                    sy = 1;
                if (ey > MAPSIZE)
                    ey = MAPSIZE;
                int sx = x - c;
                int ex = x + c + 1;
                if (sx < 1)
                    sx = 1;
                if (ex > MAPSIZE)
                    ex = MAPSIZE;

                for (int ty = sy; ty < ey; ty++) {
                    for (int tx = sx; tx < ex; tx++) {
                        int d = Math.abs(ty - y) + Math.abs(tx - x);
                        if (d <= c)
                            map[ty][tx].setIsLinkedCharger(i);
                    }
                }
            }
            
            move(0, 1, 1, MAPSIZE - 1, MAPSIZE - 1, 0);
            sb.append("#" + test_case + " " + ans + "\n");
        }
        System.out.println(sb);
    }

}
