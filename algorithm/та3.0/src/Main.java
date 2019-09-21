import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    Main(){
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            boolean bool = true;
            int size = scanner.nextInt() + 1;
            int arr[] = new int[size];
            for (int i = 1; i < size; i++) {
                arr[i] = scanner.nextInt();
            }
            if (size%2==1) {
                if (arr[size - 1] < arr[(size - 1) / 2]) {
                    bool = false;
                }
            }
            for (int i = 1; 2 * i+1 < size; i++) {
                if (arr[2 * i] < arr[i] || arr[2 * i + 1] < arr[i]) {
                    bool = false;
                    break;
                }
            }
            FileWriter writer = new FileWriter("output.txt");
            if (bool) {
                writer.write("Yes");
            } else {
                writer.write("No");
            }
            writer.close();
        }catch (IOException ex){}
    }

    public static void main(String args[]){
        new Main();
    }
}
