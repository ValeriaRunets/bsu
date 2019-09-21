import java.io.*;
import java.util.*;

import static java.lang.Math.max;


public class Main {
    Main(){
        try{
            FastScanner sc=new FastScanner("in.txt");
            int n=sc.nextInt();
            int m=sc.nextInt();
            Cell[] arr=new Cell[4*n*m];
            int first[][]=new int[n][m];
            int last[][]=new int[n][m];
            boolean check[][]=new boolean[n][m];
            for (int i=0; i<n; i++){
                for (int j=0; j<m; j++){
                    first[i][j]=sc.nextInt();
                    last[i][j]=Integer.MAX_VALUE;
                }
            }
            int count=0;
            for (int i=0; i<n; i++) {
                count++;
                arr[count]=new Cell(i, 0, first[i][0]);
                count++;
                arr[count]=new Cell(i, m - 1, first[i][m - 1]);
            }
            for (int i=1;i<m-1; i++){
                count++;
                arr[count]=new Cell(0, i, first[0][i]);
                count++;
                arr[count]=new Cell(n-1, i, first[n-1][i]);
            }
            int leftChild;
            int rightChild;
            int largestChild;

            for (int i = count / 2; i > 0; i--) {
                for (; ; ) {
                    leftChild = 2 * i ;
                    rightChild = 2 * i + 1;
                    largestChild = i;

                    if (leftChild <= count && arr[leftChild].prior < arr[largestChild].prior) {
                        largestChild = leftChild;
                    }

                    if (rightChild <= count && arr[rightChild].prior < arr[largestChild].prior) {
                        largestChild = rightChild;
                    }

                    if (largestChild == i ) {
                        break;
                    }

                    Cell temp = arr[i];
                    arr[i] = arr[largestChild];
                    arr[largestChild] = temp;
                    int j=i;
                    i = largestChild;
                    if (i>count/2){
                        i=j;
                    }
                }
            }
            while(count!=0){
                Cell cell=arr[1];
                arr[1]=arr[count];
                count--;
                int i=1;
                for (; ; ) {
                    leftChild = 2 * i ;
                    rightChild = 2 * i + 1;
                    largestChild = i;

                    if (leftChild <= count && arr[leftChild].prior < arr[largestChild].prior) {
                        largestChild = leftChild;
                    }

                    if (rightChild <= count && arr[rightChild].prior < arr[largestChild].prior) {
                        largestChild = rightChild;
                    }

                    if (largestChild == i) {
                        break;
                    }

                    Cell temp = arr[i];
                    arr[i] = arr[largestChild];
                    arr[largestChild] = temp;
                    i = largestChild;
                }
                if (!check[cell.i][cell.j]){
                    last[cell.i][cell.j]=cell.prior;
                    check[cell.i][cell.j]=true;
                    if (cell.j>0){
                        if (last[cell.i][cell.j-1]>last[cell.i][cell.j]){
                            arr=add(arr, count, new Cell(cell.i, cell.j-1, max(last[cell.i][cell.j], first[cell.i][cell.j-1])));
                            count++;
                        }
                    }
                    if (cell.j<m-1){
                        if (last[cell.i][cell.j+1]>last[cell.i][cell.j]){
                            arr=add(arr, count, new Cell(cell.i, cell.j+1, max(last[cell.i][cell.j], first[cell.i][cell.j+1])));
                            count++;
                        }
                    }
                    if (cell.i>0){
                        if (last[cell.i-1][cell.j]>last[cell.i][cell.j]){
                            arr=add(arr, count, new Cell(cell.i-1, cell.j, max(last[cell.i][cell.j], first[cell.i-1][cell.j])));
                            count++;
                        }
                    }
                    if (cell.i<n-1){
                        if (last[cell.i+1][cell.j]>last[cell.i][cell.j]){
                            arr=add(arr, count, new Cell(cell.i+1, cell.j, max(last[cell.i][cell.j], first[cell.i+1][cell.j])));
                            count++;
                        }
                    }
                }
            }
            int sum=0;
            for (int i=0; i<n; i++){
                for (int j=0; j<m; j++){
                    sum+=(last[i][j]-first[i][j]);
                }
            }
            FileWriter writer=new FileWriter("out.txt");
            writer.write(Integer.toString(sum));
            writer.close();
        }catch(IOException ex){}
    }
    public static void main(String args[]){new Main();}
    Cell[] add(Cell[]arr, int count, Cell cell){
        arr[count+1]=cell;
        count++;
        int i = count;
        int parent = i  / 2;

        while (i > 1 && arr[parent].prior > arr[i].prior)
        {
                Cell temp = arr[i];
                arr[i] = arr[parent];
                arr[parent] = temp;

                i = parent;
                parent = i / 2;
        }
        return arr;
}

class Cell implements Comparable{
    int i;
    int j;
    int prior;

    public Cell(int i, int j, int prior) {
        this.i = i;
        this.j = j;
        this.prior = prior;
    }

    public int getPrior() {
        return prior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell)) return false;
        Cell cell = (Cell) o;
        return i == cell.i &&
                j == cell.j &&
                getPrior() == cell.getPrior();
    }

    @Override
    public int compareTo(Object o) {
        if (((Cell)o).prior==prior){
            if (((Cell)o).i==i){
                return ((Cell)o).j-j;
            }
            else return ((Cell)o).i-i;
        }else return ((Cell)o).prior-prior;
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
}}
