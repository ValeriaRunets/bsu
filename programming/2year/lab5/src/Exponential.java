class Exponential extends Series {
    public Exponential(int fE, int s, double d){
        super(fE, s, d);
    }
    public double getElem(int ind){
        double el=firstElem*(Math.pow(delta, ind));
        return el;
    }
    public double sum(){
        if (delta>1) {
            double sum = firstElem * (Math.pow(delta, n - 1)) / (delta - 1);
            return sum;
        }
        else{
            return super.sum();
        }
    }
}