import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[90];
        
        //f(1) = 1, f(2) = 1
        //n > 2; f(n) = f(n-2) + f(n - 1)
        
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < 90; i++) {
            arr[i] = arr[i - 2] + arr[i - 1];
        }
        System.out.println(arr[n-1]);
    }

}
