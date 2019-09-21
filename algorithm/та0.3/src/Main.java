import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main implements Runnable {

    public static void main(String args[]) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    int val;
    int n[];
    int m[];
    int matr[][];

    public void run() {
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            val = scanner.nextInt();
            n=new int[val+1];
            m=new int[val+1];
            matr = new int[val+1][val+1];
            for(int i=1; i<=val; i++) {
                n[i]=scanner.nextInt();
                m[i]=scanner.nextInt();
            }
            for (int i = 1; i <= val; i++) {
                matr[i][i] = 0;
            }
            for (int j = 1; j <= val ; j++) {
                for (int i = 1; i <= val - j; i++) {
                    int min = Integer.MAX_VALUE;
                    int l = i + j;
                    for (int k = i; k <=l-1 ; k++) {
                        if (min > matr[i][k] + matr[k + 1][l] + n[i] * m[k] * m[l]) {
                            min = matr[i][k] + matr[k + 1][l] + n[i] * m[k] * m[l];
                        }
                    }
                    matr[i][l] = min;
                }
            }
            FileWriter writer = new FileWriter("output.txt");
            writer.write(Integer.toString(matr[1][val]));
            writer.close();
        } catch (IOException ex) {}
    }
}