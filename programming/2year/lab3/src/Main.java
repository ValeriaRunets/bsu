import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
static double [][] readMatr (String nameFile) throws FileNotFoundException, InputMismatchException, NoSuchElementException{
	Scanner scan;
	scan = new Scanner(new File(nameFile));
	int size=scan.nextInt();
	double[][] matr=new double [size][size+1];
	for (int i=0; i<size; i++) {
		for (int j=0; j<size+1; j++) {
			matr[i][j]=scan.nextDouble();
		}
	}
	scan.close();
	return matr;
}
public static void main(String[] arg) {
	try {
	int s=0;
	double matr[][]=readMatr("matr.txt");
	for (int k=0; k<matr.length; k++) {
		if (matr[k][k]==0) {
			s++;
			if (s==matr.length)
				break;
			double[] t=matr[k];
			for (int l=k; l<matr.length-1;l++) {
				matr[l]=matr[l+1];
			}
			matr[matr.length-1]=t;
			k--;
			continue;
		}
		for (int i=0; i+k<matr.length-1; i++) {
			
			double temp=-matr[i+k+1][k]/matr[k][k];
			for (int j=0; j<matr[i].length; j++) {
				matr[i+1+k][j]+=matr[k][j]*temp;
			}
		}
	}
	for (int k=matr.length-1; k>0; k--) {
		if (matr[k][k]==0) {
			double[] t=matr[k];
			for (int l=k; l<matr.length-1;l++) {
				matr[l]=matr[l+1];
			}
			matr[matr.length-1]=t;
			k--;
			continue;
		}
		for (int i=0; i<k; i++) {
			double temp=-matr[i][k]/matr[k][k];
			for (int j=matr[i].length-1; j>=k; j--) {
				matr[i][j]+=matr[k][j]*temp;
			}
		}
	}
	for (int i=0; i<matr.length; i++) {
		if (matr[i][i]!=0) {
			System.out.print(matr[i][matr.length]/matr[i][i]+" ");
		}
		else {
			System.out.print("Нет решений ");
			break;
		}
	}
}
	catch(FileNotFoundException e) {System.out.println(e.getMessage());}
	catch(NoSuchElementException e) {System.out.println("You should enter numbers");}
}
}
