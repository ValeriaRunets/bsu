import java.io.*;
import java.util.StringTokenizer;
import  java.util.*;

public class Main {
    static int n, matr[][], count=0, finish;
    static boolean isExist;
    static Point arr[];
    static long ans[];
    static LinkedList<Long>[] path;
    public static void main(String args[]) {
        try {
            FastScanner sc = new FastScanner("input.txt");
            int m;
            n = sc.nextInt();
            m = sc.nextInt();
            ans=new long[2*m];
            arr=new Point[n];
            matr=new int[n][n];
            for (int i=0; i<m; i++){
                long x1=sc.nextInt();
                long y1=sc.nextInt();
                long x2=sc.nextInt();
                long y2=sc.nextInt();
                long r=sc.nextInt();
                long c=sc.nextInt();
                arr[(int)r-1]=new Point(x1, y1, r-1, 0, 0);
                arr[(int)c-1]=new Point(x2, y2, c-1, 0, 0);
                matr[(int)r-1][(int)c-1]=1;
                matr[(int)c-1][(int)r-1]=1;
            }
            path=new LinkedList[n+1];
            for (int i = 0; i < n+1; i++) {
                path[i] = new LinkedList<>();
                path[i].add((long)0);
            }
            long start=sc.nextInt();
            finish=sc.nextInt();
            arr[(int)start-1].prevx=arr[(int)start-1].i;
            arr[(int)start-1].prevy=arr[(int)start-1].j-1;
            dfs(arr[(int)start-1]);
            FileWriter writer=new FileWriter("output.txt");
            if (isExist){
                writer.write("Yes\n");
                long ans[]=new long[2*m];
                int count=0;
                for (long i=finish, j=0; i!=0; i=path[(int)i].pollLast(), j++){
                    ans[(int)j]=i;
                    count++;
                }
                for (int j=count-1; j>=0; j--){
                    writer.write(ans[j]+" ");
                }
            }else{
                writer.write("No");
            }
            writer.close();
        } catch (IOException ex) {}
    }
    static void dfs(Point p){
        for (int j=0; j<n; j++){
            if (matr[(int)p.num][j] == 1 && p.prevx*p.j+arr[j].i*p.prevy+p.i*arr[j].j-arr[j].i*p.j-p.prevx*arr[j].j-p.i*p.prevy<=0 && (arr[j].i!=p.prevx || arr[j].j!=p.prevy)){
                matr[(int)p.num][j]=0;
                ans[count]=p.num+1;
                count++;
                arr[j].prevx=p.i;
                arr[j].prevy=p.j;
                path[j + 1].add(p.num + 1);
                if (j==finish-1){
                    isExist=true;
                    return;
                }
                dfs(arr[j]);
            }
        }
    }
}
class Point{
    long i;
    long j;
    long num;
    long prevx;
    long prevy;

    public Point(long i, long j, long num, long prevx, long prevy) {
        this.i = i;
        this.j = j;
        this.num = num;
        this.prevx = prevx;
        this.prevy = prevy;
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