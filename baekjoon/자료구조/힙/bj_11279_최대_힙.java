package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_11279_최대_힙 {

    static StringBuilder sb = new StringBuilder();
    static int[] maxHeap;
    static int count = 0;

    public static void add(int num) {
        maxHeap[++count] = num;
        int current = count;
        while (current != 1) {
            int parent = current / 2;
            if (maxHeap[parent] < num) {
                maxHeap[current] = maxHeap[parent];
                maxHeap[parent] = num;
                current = parent;
            } else {
                return;
            }
        }
    }

    public static void delete() {
        if (count == 0) {
            sb.append(0 + "\n");
            return;
        }
        sb.append(maxHeap[1] + "\n");
        int num = maxHeap[count];
        int current = 1;
        maxHeap[1] = maxHeap[count];
        maxHeap[count--] = 0;

        while (current < maxHeap.length) {
            int lChild = current * 2;
            int child = lChild;
            int rChild = current * 2 + 1;

            if (lChild > count) break;
            if (rChild <= count && maxHeap[lChild] < maxHeap[rChild]) child = rChild;
            
            if (maxHeap[current] < maxHeap[child]) {
                maxHeap[current] = maxHeap[child];
                maxHeap[child] = num;
                current = child;
            } else {
                return;
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        maxHeap = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) {
                delete();
            } else {
                add(num);
            }
        }
        System.out.println(sb);
    }

}
