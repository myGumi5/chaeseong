package silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj_1927_최소_힙 {

    static int ROOT = 1;

    static StringBuilder sb = new StringBuilder();
    static int[] minHeap;
    static int count = 0;

    public static void delete() {
        if (count < 1) {
            sb.append(0 + "\n");
            return;
        }
        sb.append(minHeap[ROOT] + "\n");
        minHeap[ROOT] = minHeap[count];
        minHeap[count--] = 0;
        int num = minHeap[ROOT];
        int current = ROOT;
        while (true) {
            int leftChild = current * 2;
            int rightChild = current * 2 + 1;
            int child = leftChild;
            if (leftChild > count)
                return;
            if (rightChild <= count && (minHeap[leftChild] > minHeap[rightChild])) {
                child = rightChild;
            }

            if (minHeap[child] < num) {
                minHeap[current] = minHeap[child];
                minHeap[child] = num;
                current = child;
            } else {
                break;
            }
        }
    }

    public static void add(int num) {
        minHeap[++count] = num;
        int current = count;

        while (current != 1) {
            int parent = current / 2;
            if (minHeap[parent] > num) {
                minHeap[current] = minHeap[parent];
                minHeap[parent] = num;
                current = parent;
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        minHeap = new int[n + 1];

        for (int i = 0; i < n; i++) {
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
