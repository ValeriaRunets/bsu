import java.io.*;
import java.util.*;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class Main implements Runnable {

    public static void main(String args[]) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    int arr[];
    int length[][], F[][];
    int num, size;
    char buf[]=new char[100000];

    public void run() {
        try {
            BufferedReader reader=new BufferedReader(new FileReader(new File("in.txt")));
            Scanner scanner = new Scanner(new File("in.txt"));
            size = reader.read();
            size=Integer.valueOf(Character.toString(buf[0]));
            num = reader.read();
            arr = new int[size + 1];
            length = new int[size + 1][size + 1];
            F=new int [size+1][size+1];
            for (int i = 1; i <= size; i++) {
                arr[i] = reader.read();
            }
            reader.close();
            for (int i = 2; i <= size; i++) {
                for (int j = 1; j <= i - 1; j++) {
                    F[1][i]+= abs(arr[i] - arr[j]);
                }
            }

            for (int i = 2; i <= size; i++) {
                for (int j = 1; j < size; j++) {
                    int sum = 0;
                    for (int t = j + 1; t <= i - 1; t++) {
                        sum += min(abs(arr[i] - arr[t]), abs(arr[j] - arr[t]));
                    }
                    length[i][j] = sum;
                }
            }

            for (int k = 2; k <= num; k++) {
                for (int i = 2; i <= size; i++) {
                    int sum=Integer.MAX_VALUE;
                    for (int j = 1; j <= i - 1; j++) {
                        sum =min(sum, abs(length[i][j] + F[k-1][j]));
                    }
                    F[k][i]=sum;
                }
            }
            for (int i = 1; i <= size - 1; i++) {
                for (int j = i + 1; j <= size; j++) {
                    F[num][i] += abs(arr[i] - arr[j]);
                }
            }
            int res = Integer.MAX_VALUE;
            for (int i = 1; i <= size; i++) {
                res =min(res, abs(F[num][i]));
            }

            FileWriter writer = new FileWriter("out.txt");
            writer.write(Integer.toString(res));
            writer.close();
        } catch (IOException ex) {
        }
    }
}