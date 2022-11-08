package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Friend implements Comparable<Friend> {
	private int cost = 0;
	private int idx = 0;

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public Friend(int cost, int idx) {
		super();
		this.cost = cost;
		this.idx = idx;
	}

	@Override
	public int compareTo(Friend o) {
		return this.cost - o.cost;
	}
}

public class bj_16562_Ä£±¸ºñ {

	static int n, m, k;
	static int[] parent;
	static Friend[] friends;

	public static int find(int u) {
		if (parent[u] == u)
			return u;
		return parent[u] = find(parent[u]);
	}
	
	public static void union(int u, int v) {
		u = find(u);
		v = find(v);
		if (u == v) return;
		if (friends[u].getCost() < friends[v].getCost()) {
			parent[v] = u;
		} else {
			parent[u] = v;			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		int ans = 0;
		
		parent = new int[n+1];
		friends = new Friend[n + 1];
		friends[0] = new Friend(0, 0);
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			friends[i] = new Friend(Integer.parseInt(st.nextToken()), i);
			parent[i] = i;
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			union(u, v);
		}
		for (int i = 1; i <= n; i++) {
			int p = find(friends[i].getIdx()); 
			if (p == 0) continue;
			ans += friends[i].getCost();
			if (k < ans) {
				System.out.println("Oh no");
				System.exit(0);;
			}
			parent[p] = 0;
		}
		for (int i = 1; i <= n; i++) {
			if (find(friends[i].getIdx()) != 0) {
				System.out.println("Oh no");
				System.exit(0);;
			}
		}
		System.out.println(ans);
	}

}
