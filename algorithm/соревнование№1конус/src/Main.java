import java.io.*;
import java.util.StringTokenizer;
import java.util.*;

public class Main {
    public static void main(String[] args){
        try {
            int matr[][], count=0;
            FastScanner sc = new FastScanner("in.txt");
            int m=sc.nextInt();
            int n=sc.nextInt();
            matr=new int[m][n];
            Stack<Cell> stack=new Stack<>();
            for (int i=0; i<m; i++){
                for (int j=0; j<n; j++){
                    matr[i][j]=sc.nextInt();
                }
            }
            for (int i=0; i<m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matr[i][j]==0){
                        stack.push(new Cell(i, j));
                        matr[i][j]=0;
                        count++;
                    }
                    while (!stack.empty()) {
                        Cell cell = stack.pop();
                        if (cell.j > 0 && matr[cell.i][cell.j - 1] == 0) {
                            stack.push(new Cell(cell.i, cell.j - 1));
                            matr[cell.i][cell.j - 1] = 1;
                        }
                        if (cell.i < m - 1 && matr[cell.i + 1][cell.j] == 0) {
                            stack.push(new Cell(cell.i + 1, cell.j));
                            matr[cell.i + 1][cell.j] = 1;
                        }
                        if (cell.j < n - 1 && matr[cell.i][cell.j + 1] == 0) {
                            stack.push(new Cell(cell.i, cell.j + 1));
                            matr[cell.i][cell.j + 1] = 1;
                        }
                        if (cell.i > 0 && matr[cell.i - 1][cell.j] == 0) {
                            stack.push(new Cell(cell.i - 1, cell.j));
                            matr[cell.i - 1][cell.j] = 1;
                        }
                        if(cell.i==m-1 && matr[0][cell.j]==0){
                            stack.push(new Cell(0, cell.j));
                            matr[0][cell.j]=1;
                        }
                        if(cell.i==0 && matr[m-1][cell.j]==0){
                            stack.push(new Cell(m-1, cell.j));
                            matr[m-1][cell.j]=1;
                        }
                    }
                }
            }
            FileWriter writer=new FileWriter("out.txt");
            writer.write(Integer.toString(count));
            writer.close();
        }catch (IOException ex){}
    }
}
class Cell {
    int i;
    int j;

    public Cell(int i, int j) {
        this.i = i;
        this.j = j;
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
