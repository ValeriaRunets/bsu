import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main {
    int met[], n, matr[][], count=0;
    Main(){
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            n=sc.nextInt();
            matr= new int[n][n];
            met=new int[n];
            for (int i=0; i<n; i++){
                for (int j=0; j<n; j++){
                    matr[i][j]=sc.nextInt();
                }
            }
            for (int i=0; i<n; i++){
                if (met[i]==0){
                    dfs(i);
                }
            }
            FileWriter writer=new FileWriter("output.txt");
            for (int i=0; i<n; i++){
                writer.write(met[i]+" ");
            }
            writer.close();
        }catch (IOException ex){}
    }
    public static void main(String[] args) {
        new Main();
    }
    void dfs(int i){
        count++;
        met[i]=count;
        for (int j=0; j<n; j++){
            if (matr[i][j]==1 && met[j]==0){
                dfs(j);
            }
        }
    }
}
