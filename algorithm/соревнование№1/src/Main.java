//дп назад
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String args[]){
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            int n = sc.nextInt();
            int m = sc.nextInt();
            long arr[]=new long[m];
            for (int i=0; i<m; i++){
                arr[i]=1;
            }
            for (int j=0; j<n-1; j++) {
                for (int i = 1; i < m; i++) {
                    arr[i] = (arr[i] + arr[i - 1]);
                    if (arr[i]>=1000000007){
                        arr[i]-=1000000007;
                    }
                }
            }

            FileWriter writer = new FileWriter("output.txt");
            writer.write(Long.toString(arr[m-1]%1000000007));
            writer.close();
        }catch (IOException ex){}
    }
}
