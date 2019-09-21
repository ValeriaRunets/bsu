import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) {
        try {
            FastScanner sc = new FastScanner("input.txt");
            int s = sc.nextInt();
            int n = sc.nextInt();
            int f = sc.nextInt();
            int matr[][] = new int[n * 2 + s + f + 2][n * 2 + s + f + 2];
            for (int i = 0; i < n * 2 + s + f + 2; i++) {
                for (int j = 0; j < n * 2 + s + f + 2; j++) {
                    matr[i][j] = -1;
                }
            }
            for (int i = 1; i < s + 1; i++) {
                matr[0][i] = 1;
                matr[i][1] = 0;
            }
            for (int i = s + 1; i < s + n + 1; i++) {
                matr[2 * (i - s - 1) + s + 1][2 * (i - s - 1) + s + 2] = sc.nextInt();
                matr[2 * (i - s - 1) + s + 2][2 * (i - s - 1) + s + 1] = 0;
            }
            for (int i = 1; i < s + 1; i++) {
                int k = sc.nextInt();
                for (int j = 0; j < k; j++) {
                    int buf = sc.nextInt();
                    if (buf > s + n) {
                        matr[i][n + buf] = Integer.MAX_VALUE;
                        matr[n + buf][i] = 0;
                    } else {
                        matr[i][2 * (buf - s - 1) + s + 1] = Integer.MAX_VALUE;
                        matr[2 * (buf - s - 1) + s + 1][i] = 0;
                    }
                }
            }
            for (int i = s + 1; i < s + n + 1; i++) {
                int k = sc.nextInt();
                for (int j = 1; j < k + 1; j++) {
                    int buf = sc.nextInt();
                    if (buf > s + n) {
                        matr[2 * (i - s - 1) + s + 2][n + buf] = Integer.MAX_VALUE;
                        matr[n + buf][2 * (i - s - 1) + s + 2] = 0;
                    } else {
                        matr[2 * (i - s - 1) + s + 2][2 * (buf - s - 1) + s + 1] = Integer.MAX_VALUE;
                        matr[2 * (buf - s - 1) + s + 1][2 * (i - s - 1) + s + 2] = 0;
                    }
                }
            }
            for (int i = s + 2 * n + 1; i < s + 2 * n + f + 1; i++) {
                matr[i][s + 2 * n + f + 1] = 1;
                matr[s + 2 * n + f + 1][i] = 0;
            }
            FileWriter writer = new FileWriter("output.txt");
            int M=0;
            boolean flag=true;
            while (flag) {
                int min = dfs(matr, new boolean[matr.length], 0, s + 2 * n + f + 1, Integer.MAX_VALUE);
                if (min == 0) {
                    flag = false;
                }
                M += min;
            }
            writer.write(Integer.toString(M));
            writer.close();
        } catch (IOException ex) {
        }
    }

    static int dfs(int[][] cap, boolean[] vis, int u, int t, int f) {
        if (u == t)
            return f;
        vis[u] = true;
        for (int v = 0; v < vis.length; v++)
            if (!vis[v] && cap[u][v] > 0) {
                int df = dfs(cap, vis, v, t, Math.min(f, cap[u][v]));
                if (df > 0) {
                    cap[u][v] -= df;
                    cap[v][u] += df;
                    return df;
                }
            }
        return 0;
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
