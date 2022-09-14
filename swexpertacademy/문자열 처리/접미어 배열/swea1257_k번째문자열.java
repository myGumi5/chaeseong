package swea;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class swea1257_k번째문자열 {

    static class FastIn {
        BufferedReader br;
        StringTokenizer st;

        FastIn() {
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

    public static void main(String args[]) throws Exception {
        FastIn sc = new FastIn();
        int T;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            String ans = "";
            int k = sc.nextInt();
            String str = sc.next();
            int n = str.length();

            // 접미사 배열
            String[] suffixArr = new String[n];
            int[] LCP = new int[n];
            for (int i = 0; i < n; i++) {
                suffixArr[i] = str.substring(i);
            }

            // 접미사 배열 정렬
            Arrays.sort(suffixArr);
            // LCP 배열 초기화
            for (int i = 1; i < n; i++) {
                int count = 0;
                for (int j = 0; j < suffixArr[i - 1].length(); j++) {
                    if (suffixArr[i - 1].charAt(j) == suffixArr[i].charAt(j)) {
                        count++;
                    } else {
                        break;
                    }
                }
                LCP[i] = count;
            }
            int subStrCount = 0;
            boolean flag = false;
            root: for (int i = 0; i < n; i++) {
                int l = suffixArr[i].length();
                int t = subStrCount + l - LCP[i];
                // 같은 경우
                if (t == k) {
                    ans = suffixArr[i];
                    flag = true;
                } else if (t > k) {
                    int gap = k - subStrCount;
                    int c = LCP[i] + gap;
                    ans = suffixArr[i].substring(0, LCP[i] + gap);
                    flag = true;
                }
                subStrCount = t;
                if (flag)
                    break root;
            }
            if (k > subStrCount)
                ans = "none";
            bw.write("#" + test_case + " " + ans + "\n");
        }
        bw.flush();
        bw.close();
    }

}
