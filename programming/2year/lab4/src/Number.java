
public class Number implements Comparable<Number> {
	private Integer n;
	Number(Integer a){n=a;}
	public int compareTo(Number k) {
		if (n<k.n) {
			return -1;
		}
		if (n>k.n) {
			return 1;
		}
		return 0;
	}
	public  String toString() {
		return n.toString(); 
	}
}
