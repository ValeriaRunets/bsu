import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            int n=sc.nextInt();
            int matr[][] = new int[n][n];
            int met[]=new int[n];
            int count=0, val=0;

            Queue queue=new LinkedList();
            for (int i=0; i<n; i++){
                for (int j=0; j<n; j++){
                    matr[i][j]=sc.nextInt();
                    if (matr[i][j]==1){
                        val++;
                    }
                }
            }
            for (int k=0; k<n; k++) {
                if (met[k]==0) {
                    count++;
                    met[k]=1;
                    queue.add(k);
                    while (!queue.isEmpty()) {
                        int i = (int) queue.poll();
                        for (int j = 0; j < n; j++) {
                            if (matr[i][j] == 1 && met[j] == 0) {
                                met[j] = 1;
                                queue.add(j);
                            }
                        }
                    }
                }
            }
            FileWriter writer=new FileWriter("output.txt");
            if (count==1 && val/2==n-1){
                writer.write("Yes");
            }else{
                writer.write("No");
            }
            writer.close();
        }catch (IOException ex){}
    }
}