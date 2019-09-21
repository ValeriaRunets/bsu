import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    Main(){
        try{
            Scanner scanner=new Scanner(new File("in.txt"));
            int size=scanner.nextInt();
            int num=scanner.nextInt();
            int arr[]=new int[size];
            for (int i=0; i<size; i++){
                arr[i]=scanner.nextInt();
            }
        }catch (IOException ex){}
    }
}
