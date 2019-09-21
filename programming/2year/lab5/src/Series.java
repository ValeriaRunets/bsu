import java.io.FileWriter;
import java.io.IOException;

public abstract class Series {
    protected int firstElem;
    protected int n;
    protected double delta;
    public Series(){}

    public Series(int firstElem, int n, double delta) {
        this.firstElem = firstElem;
        this.n = n;
        this.delta = delta;
    }

    abstract public double getElem(int ind);
    public double sum(){
        double sum=0;
        for (int i=0; i<n; i++){
            sum+=this.getElem(i);
        }
        return sum;
    }
    public String toString() {
        StringBuffer str=new StringBuffer();
        for (int i=0; i<n; i++){
            str.append(this.getElem(i)+" ");
        }
        return str.toString();
    }
    public void toFile(String fileName) throws IOException{
        FileWriter writer=new FileWriter(fileName);
        writer.write(this.toString()+"\n"+"Sum="+this.sum());
        writer.close();
    }
}
