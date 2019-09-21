import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]){
        try{
            FastScanner sc=new FastScanner("input.txt");
            int n=sc.nextInt();
            int arr[]=new int[n];
            for (int i=0; i<n; i++){
                for (int j=0; j<n; j++) {
                    if(sc.nextInt()==1){
                        arr[j]=i+1;
                    }
                }
            }
            FileWriter writer=new FileWriter("output.txt");
            for (int i=0; i<n; i++){
                writer.write(arr[i]+ " ");
            }
            writer.close();
        }catch (IOException ex){}
    }
}
class FastScanner {
    BufferedReader reader;
    StringTokenizer tokenizer;

    public FastScanner(String fileName) throws IOException {
        reader = new BufferedReader(new FileReader(fileName));
    }

    public String next() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = reader.readLine();
            if (line == null) {
                throw new EOFException();
            }
            tokenizer = new StringTokenizer(line);
        }
        return tokenizer.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }
}
