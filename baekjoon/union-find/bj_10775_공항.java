package baekjoon;

import java.util.Scanner;

public class bj_10775_���� {

	static int find(int a) {
		if (ports[a] == a) return a;
		return ports[a] = find(ports[a]);
	}
		
	static boolean doDork(int a) {
		a = find(a);
		if (a == 0) {
			return false;
		}
		ans++;
		ports[a]--;
		return true;
	}
	
	static int g, p, ans = 0;
	static int[] ports;
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		g = sc.nextInt();
		p = sc.nextInt();
		
		ports = new int[g+1];
		for (int i = 1; i <= g; i++)
			ports[i] = i;
		//���� 1. �ڱ� �ڽ��̶� ����� ��
		//���� 2. �ּ� ��� 
		mainRoop: for (int i = 0; i < p; i++) {
			int pId = sc.nextInt();
			if(!doDork(pId)) break;
		}
		System.out.println(ans);
	}

}
