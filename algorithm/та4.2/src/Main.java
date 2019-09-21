import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    public static void main(String[] args){
        try {
            int entrance[], matr[][], count=0;
            String prev;
            FastScanner sc = new FastScanner("input.txt");
            int n=sc.nextInt();
            int m=sc.nextInt();
            int k=sc.nextInt();
            entrance=new int[k];
            matr=new int[n][m];
            int help[][]=new int[n][m];
            int temp;
            int exit[]=new int[m];
            Stack<Cell> stack=new Stack<>();
            for (int i=0; i<k; i++){
                entrance[i]=sc.nextInt();
            }
            for (int i=0; i<m; i++){
                exit[i]=1;
            }
            for (int i=0; i<k; i++){
                temp=sc.nextInt();
                exit[temp-1]=0;
            }
            for (int i=0; i<n; i++){
                for (int j=0; j<m; j++){
                    matr[i][j]=sc.nextInt();
                }
            }
            for (int i=0; i<k; i++) {
                for (int l=0; l<n; l++){
                    for (int j=0; j<m; j++){
                        help[l][j]=matr[l][j];
                    }
                }
                for (int l=0; l<n; l++){
                    if (exit[l]==1) {
                        help[n - 1][l] = 1;
                    }
                }
                if (matr[0][entrance[i]-1]==0) {
                    stack.push(new Cell(0, entrance[i] - 1, "down"));
                    prev="down";
                }else {
                    continue;
                }
                help[0][entrance[i]-1]=1;
                while (!stack.empty()) {
                    Cell cell = stack.peek();
                    if (cell.prev.equals("down") || cell.prev.equals("right")) {
                        if (cell.i == n - 1 && exit[cell.j]==0) {
                            while (!stack.empty()) {
                                Cell buf = stack.pop();
                                matr[buf.i][buf.j] = i + 2;
                            }
                            count++;
                        } else if (cell.j > 0 && help[cell.i][cell.j - 1] == 0) {
                            stack.push(new Cell(cell.i, cell.j - 1, "left"));
                            help[cell.i][cell.j - 1] = 1;
                            prev = "left";
                        } else if (cell.i < n - 1 && help[cell.i + 1][cell.j] == 0) {
                            stack.push(new Cell(cell.i + 1, cell.j, "down"));
                            help[cell.i + 1][cell.j] = 1;
                            prev = "down";
                        } else if (cell.j < m - 1 && help[cell.i][cell.j + 1] == 0) {
                            stack.push(new Cell(cell.i, cell.j + 1, "right"));
                            help[cell.i][cell.j + 1] = 1;
                            prev = "right";
                        } else if (cell.i > 0 && help[cell.i - 1][cell.j] == 0) {
                            stack.push(new Cell(cell.i - 1, cell.j, "up"));
                            help[cell.i - 1][cell.j] = 1;
                            prev = "up";
                        } else {
                            stack.pop();
                        }
                    }else{
                            if (cell.i == n - 1 && exit[cell.j]==0) {
                                while (!stack.empty()) {
                                    Cell buf = stack.pop();
                                    matr[buf.i][buf.j] = i + 2;
                                }
                                count++;
                            } else if (cell.j < m - 1 && help[cell.i][cell.j + 1] == 0) {
                                stack.push(new Cell(cell.i, cell.j + 1, "right"));
                                help[cell.i][cell.j + 1] = 1;
                                prev = "right";
                            } else if (cell.i > 0 && help[cell.i - 1][cell.j] == 0) {
                                stack.push(new Cell(cell.i - 1, cell.j, "up"));
                                help[cell.i - 1][cell.j] = 1;
                                prev = "up";
                            } else if (cell.j > 0 && help[cell.i][cell.j - 1] == 0) {
                                stack.push(new Cell(cell.i, cell.j - 1, "left"));
                                help[cell.i][cell.j - 1] = 1;
                                prev = "left";
                            } else if (cell.i < n - 1 && help[cell.i + 1][cell.j] == 0) {
                                stack.push(new Cell(cell.i + 1, cell.j, "down"));
                                help[cell.i + 1][cell.j] = 1;
                                prev = "down";
                            } else {
                                stack.pop();
                            }
                    }
                }
            }
            FileWriter writer=new FileWriter("output.txt");
            writer.write(Integer.toString(count)+'\n');
            for (int i=0; i<n; i++){
                for (int j=0; j<m; j++){
                    writer.write(Integer.toString(matr[i][j])+" ");
                }
                writer.write('\n');
            }
            writer.close();
        }catch (IOException ex){}
    }
}
class Cell {
    int i;
    int j;
    String prev;

    public Cell(int i, int j, String prev) {
        this.i = i;
        this.j = j;
        this.prev=prev;
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
