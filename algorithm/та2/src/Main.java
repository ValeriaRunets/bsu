import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main implements Runnable {

    public static void main(String args[]) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    int size, place;
    int fRow[], sRow[];
    int right , left ;
    public void run() {
        try {
            Scanner scanner = new Scanner(new File("in.txt"));
            size = scanner.nextInt();
            size++;
            place = scanner.nextInt();
            fRow = new int[size];
            sRow = new int[size];
            right = place;
            left = place;
            int sumr = 0, suml = 0, sum;
            for (int i = 0; i < size-1; i++) {
                fRow[i] = scanner.nextInt();
                sRow[i] = scanner.nextInt();
            }
            for (int i=0; i<place; i++){
                if (fRow[i]==1 || sRow[i]==1){
                    left=i;
                    break;
                }
            }
            for (int i=size-1; i>=place; i--){
                if (fRow[i]==1 || sRow[i]==1){
                    right=i+1;
                    break;
                }
            }
            sumr = countr(place, size);
            suml = countl(place);
            if (suml + 2 * (right-place) < sumr + 2 * (place - left)) {
                sum = suml + 2 * (right-place);
            } else {
                sum = sumr + 2 * (place - left);
            }
            FileWriter writer=new FileWriter("out.txt");
            writer.write(Integer.toString(sum));
            writer.close();
        }catch(IOException ex){}
    }
    int countr(int ind, int s){
        int arr[]=new int[right-ind+1];
        arr[0]=2*(right-ind);
        int k=0;
        for (int i=ind+1; i<=right; i++){
            int buf=0;
            k++;
            if (fRow[i-1]==1 && sRow[i-1]==1){
                if (i-1==right-1){
                    buf++;
                }else {
                    buf += 2;
                }
            }
            arr[k]=buf+arr[k-1]-1;
        }
        int min=arr[0];
        for (int i=0; i<=right-ind; i++){
            if (min>arr[i]){
                min=arr[i];
            }
        }
        return min;
    }
    int countl(int ind){
        int arr[]=new int[ind-left+1];
        arr[0]=2*(ind-left);
        int k=0;
        for (int i=ind-1; i>=left; i--){
            k++;
            int buf=0;
            if (fRow[i]==1 && sRow[i]==1){
                if (i==left){
                    buf++;
                }else {
                    buf += 2;
                }
            }
            arr[k]=arr[k-1]+buf-1;
        }
        int min=arr[0];
        for (int i=0; i<=ind-left; i++){
            if (min>arr[i]){
                min=arr[i];
            }
        }
        return min;
    }
}
