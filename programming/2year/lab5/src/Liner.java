public class Liner extends Series {
    public Liner(int fE, int s, double d){
        super(fE, s, d);
    }
    public double getElem(int ind){
        double el=firstElem+delta*ind;
        return el;
    }
    public double sum(){
        double sum=(2*firstElem+(n-1)*firstElem)*n/2;
        return sum;
    }
}