import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main
{
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    StringTokenizer st = null;
	    
	    int n = Integer.parseInt(br.readLine());
	    int [][] arr = new int[n][n];
	    
	    for (int i = 0; i  n; i++) {
	        st = new StringTokenizer(br.readLine());
	        for (int j = 0; j  n; j++)
	            arr[i][j] = Integer.parseInt(st.nextToken());
	    }
	    
	    k = 경유위치, i = 출발위치, j = 도착위치
	    for (int k = 0; k  n; k++) {
	        for (int i = 0; i  n; i++) {
	            for (int j = 0; j  n; j++) {
	                if (arr[i][j] == 1)
	                    continue;
	                if (arr[i][k] + arr[k][j] == 2) {
	                    arr[i][j] = 1;
	                }
	            }
	        }
	    }
		for (int i = 0; i  n; i++) {
		    for (int j = 0; j  n; j++) {
		        System.out.print(arr[i][j] +  );
		    }
		    System.out.println();
		}
	}
}
