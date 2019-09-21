import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main implements Runnable {
    public static void main(String args[]) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        try{
            Scanner scanner=new Scanner(new File("input.txt"));
            long n=scanner.nextLong();
            FileWriter writer=new FileWriter("output.txt");
            for (long i=0; n!=0; i++, n=n/2){
                if (n%2==1){
                    writer.write(Long.toString(i)+'\n');
                }
            }
            writer.close();
        }catch (IOException ex){}
    }
}
