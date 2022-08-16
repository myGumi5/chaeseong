import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class FastIn {
		BufferedReader br;
		StringTokenizer st;

		public FastIn() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		public String next() {
			if (st == null || !st.hasMoreTokens()) {
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

	public static boolean dfs(int y, int x) {
		if (visited[y][x] || !map[y][x]) {
			return false;
		}
		visited[y][x] = true;
		int h = visited.length;
		int w = visited[0].length;

		for (int i = 0; i < dx.length; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (-1 < tx && tx < w && -1 < ty && ty < h && !visited[ty][tx] && map[ty][tx]) {
				dfs(ty, tx);
			}
		}
		return true;
	}

	static boolean[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0, -1, 1, -1, 1 };
	static int[] dy = { 0, 0, -1, 1, -1, -1, 1, 1 };

	public static void main(String[] args) {
		int w, h;
		FastIn sc = new FastIn();
		int count = 0;
		while (true) {
			w = sc.nextInt();
			h = sc.nextInt();
			count = 0;
			if (w == 0 && h == 0) {
				return;

			}
			map = new boolean[h][w];
			visited = new boolean[h][w];

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (sc.nextInt() == 1)
						map[i][j] = true;
					else
						map[i][j] = false;
				}
			}
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (dfs(i, j))
						count++;
				}
			}
			System.out.println(count);
		}
	}

}
