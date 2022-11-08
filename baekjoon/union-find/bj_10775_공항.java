package baekjoon;

import java.util.Scanner;

public class bj_10775_공항 {

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
		//전략 1. 자기 자신이랑 가까운 거
		//전략 2. 최소 경우 
		mainRoop: for (int i = 0; i < p; i++) {
			int pId = sc.nextInt();
			if(!doDork(pId)) break;
		}
		System.out.println(ans);
	}

}
