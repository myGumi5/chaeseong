package gold;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_2638_ġ�� {

	static int[][] map;
	static boolean[][] v;
	static int[] dy = new int[] {
			-1, 0, 1, 0
	};
	static int[] dx = new int[] {
			0, 1, 0, -1
	};
	static Queue<int []> tQ = new LinkedList<>();
	static void initOutAir(int n, int m) {
		//��� ġ�� üũ
		Queue<int []> mQ = new LinkedList<>();  
		v = new boolean[n][m];
		tQ.add(new int[] {0, 0});
		v[0][0] = true;
		
		int count = 0;
		
		while(!tQ.isEmpty()) {
			count++;
			//�� ���� Ž��
			while(!tQ.isEmpty()) {
				int[] c = tQ.poll();
				int cy = c[0];
				int cx = c[1];
				
				for (int i = 0; i < dx.length; i++) {
					int ty = cy + dy[i];
					int tx = cx + dx[i];

					//�迭 ���� ���� ���
					if (ty < 0 || tx < 0 || ty >= n || tx >= m)continue;
					//�湮�� ������ ���
					if (v[ty][tx]) continue;
					//�ٱ� ������ ���
					if (map[ty][tx] == -1) {
						v[ty][tx] = true;
						tQ.add(new int[] {ty, tx});
					} else { // ġ���� ��� �����ϴ� �ٱ� ���� ī��Ʈ
						if (++map[ty][tx] == 2) mQ.add(new int[] {ty, tx});
					}
				}
			}
			while(!mQ.isEmpty()) {
				int[] c = mQ.poll();
				int cy = c[0];
				int cx = c[1];
				v[cy][cx] = true;
				map[cy][cx] = -1;
				tQ.add(new int[] {cy, cx});
			}
		}
		System.out.println(count -1);
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		//�� �ʱ�ȭ
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				int isCheese = Integer.parseInt(st.nextToken());
				map[i][j] = isCheese - 1;
			}
		}
		
		initOutAir(n, m);
	}

}
