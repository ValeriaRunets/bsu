import java.io.File;
import java.io.IOException;
import java.util.*;
class Detail implements Comparable{
    long deliv;
    long work;

    public Detail(long deliv, long work) {
        this.deliv = deliv;
        this.work = work;
    }

    @Override
    public int compareTo(Object o) {
        return (int)(deliv+work-(((Detail)o).deliv+((Detail)o).work));
    }
}
public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("input.txt"));
            int n=sc.nextInt();
            int m=sc.nextInt();
            PriorityQueue queue=new PriorityQueue<Detail>();
            for (int i=0; i<n; i++) {
                queue.add(new Detail(sc.nextLong(), sc.nextLong()));
            }
            while (!queue.isEmpty()){
                
            }
        }catch (IOException ex){}
    }
}
