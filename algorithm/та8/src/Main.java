import java.io.*;
import java.util.*;
public class Main {

    static int n, m, k, amount=0;
    static int[][] board;
    static int min = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> answer=new ArrayList<>();
    public static void main(String[] args)
    {
        try {
            FastScanner scanner = new FastScanner("input.txt");
            n=scanner.nextInt();
            m=scanner.nextInt();
            k=scanner.nextInt();
            min=5+k;
            board = new int[n][m];
            for (int i=0; i<k; i++){
                int buf=scanner.nextInt();
                board[(buf-1)/m][(buf-1)%m]=2;
            }
            findPlace(0, 0, 0);
            FileWriter writer=new FileWriter("output.txt");
            for (ArrayList<Integer> arr:answer){
                if (arr.size()== min){
                    amount++;
                    for (Integer i: arr){
                        writer.write(Integer.toString(i)+" ");
                    }
                    writer.write('\n');
                }
            }
            writer.write(Integer.toString(amount));
            writer.close();
        }catch(IOException ex){}
    }

    static void findPlace(int num, int row, int col) {
        boolean ok=true;
        if (num > min) {
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (!ok){
                break;
            }
            for (int j = 0; j < m; ++j) {
                if (!isBeat(i, j)) {
                    ok = false;
                    break;
                }
            }
        }
        if (ok){
            min = num;
            saveAns();
            return;
        }

        for (int i = row; i < n; i++) {
            int begin;
            if (i==row){
                begin=col;
            }else{
                begin=0;
            }
            for (int j = begin; j < m; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    findPlace(num + 1, i, j);
                    board[i][j] = 0;
                }
            }
        }
        return;
    }
    static void saveAns()
    {
        ArrayList<Integer> curBoard=new ArrayList<>();
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++){
                if (board[i][j]==1){
                    curBoard.add(i*m+j+1);
                }
            }
        }
        answer.add(curBoard);
    }
    static boolean isBeat(int row, int col)
    {
        int i, j;
        if (board[row][col]==2){
            return true;
        }
        for (i = row; i >= 0; i--) {
            if (board[i][col] == 1)
                return true;
            if (board[i][col] == 2)
                break;
        }
        for (i = row; i < n; i++) {
            if (board[i][col] == 1)
                return true;
            if (board[i][col] == 2)
                break;
        }
        for (j = col; j < m; j++) {
            if (board[row][j] == 1)
                return true;
            if (board[row][j] == 2)
                break;
        }
        for (j = col; j >=0; j--) {
            if (board[row][j] == 1)
                return true;
            if (board[row][j] == 2)
                break;
        }
        for (i = 0; i < Math.min(n, m); i++) {
            if (row - i >= 0 && col - i >= 0 && board[row - i][col - i] == 1)
                return true;
            if (row - i >= 0 && col - i >= 0 && board[row - i][col - i] == 2)
                break;
        }
        for (i = 0; i < Math.min(n, m); i++) {
            if (row - i >= 0 && col + i < m && board[row - i][col + i] == 1)
                return true;
            if (row - i >= 0 && col + i < m && board[row - i][col + i] == 2)
                break;
        }
        for (i = 0; i < Math.min(n, m); i++) {
            if (row + i < n && col - i >= 0 && board[row + i][col - i] == 1)
                return true;
            if (row + i < n && col - i >= 0 && board[row + i][col - i] == 2)
                break;
        }
        for (i = 0; i < Math.min(n, m); i++) {
            if (row + i < n && col + i < m && board[row + i][col + i] == 1)
                return true;
            if (row + i < n && col + i < m && board[row + i][col + i] == 2)
                break;
        }
        return false;
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