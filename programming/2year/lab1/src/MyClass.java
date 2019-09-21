class MyEx extends Exception{
	private static final long serialVersionUID = 1L;

	public MyEx (String a) {super (a);}
}
public class MyClass {
	static double sum(double x, double e) {
		int k=1;
		double s=0;
		double addend=(-1)*x/k;
		while(Math.abs(addend)>e)
		{
			s=s+addend;
			k++;
			addend=addend*(-1)*x*(k-1)/k;
		}
		return s;
	}
	public static void main(String[] args) {
		try {
			if (args.length!=2)
				throw new MyEx("length error: please, enter 2 parametres");
			double x= Double.parseDouble(args[0]); 
			double e=Double.parseDouble(args[1]);
			double s=sum(x, e);
			System.out.println("Ñóììà="+s);
		}
		catch (MyEx e) {System.out.println(e.getMessage());}
		catch (NumberFormatException e) {System.out.println(e.getMessage()+"You should enter double");}
	}

}
