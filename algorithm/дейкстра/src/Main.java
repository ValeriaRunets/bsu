import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        try{
            FastScanner scanner=new FastScanner("input.txt");
            int n=scanner.nextInt();
            int m=scanner.nextInt();
            boolean check[]=new boolean[n+1];
            Graph edges[]=new Graph[n];
            for (int i=0; i<n; i++){
                edges[i]=new Graph();
            }
            for (int i=0; i<m; i++){
                long pos=scanner.nextInt();
                long to=scanner.nextInt();
                long point=scanner.nextInt();
                edges[(int)pos-1].list.add(new Line(to, point));
                edges[(int)to-1].list.add(new Line(pos, point));
            }
            long min=Long.MAX_VALUE;
            PriorityQueue<Line> q=new PriorityQueue<>();
            q.add(new Line(1, 0));
            while (!q.isEmpty())
            {
                Line cur =q.poll();
                if (cur.to==n && cur.weight<min){
                    min=cur.weight;
                }
                if (!check[(int)cur.to]) {
                    for (int i = 0; i < edges[(int)cur.to-1].list.size(); ++i) {
                        if (!check[(int)edges[(int)cur.to-1].list.get(i).to]){
                            q.add(new Line(edges[(int)cur.to-1].list.get(i).to, cur.weight+edges[(int)cur.to-1].list.get(i).weight));
                        }
                    }
                }
                check[(int)cur.to]=true;
            }
            FileWriter writer=new FileWriter("output.txt");
            writer.write(String.valueOf(min));
            writer.close();
        }catch (IOException ex){}
    }
}
class Line implements Comparable{
    long to;
    long weight;

    public Line(long to, long weight) {
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Object o) {
        return (int)(weight-((Line)o).weight);
    }
}
class Graph{
    ArrayList<Line> list;

    public Graph() {
        list=new ArrayList<>();
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
