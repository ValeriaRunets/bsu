
public class Main {

	public static void main(String[] args) {
//		Tree<Integer> tree=new Tree<Integer>();
//		tree.add(4);
//		tree.add(2);
//		tree.add(8);
//		tree.add(6);
//		tree.add(9);
//		tree.add(5);
//		tree.add(7);
//		tree.add(10);
//		tree.add(3);
//		tree.add(1);
		Tree<Number> tree=new Tree<Number>();
		tree.add(new Number(4));
		tree.add(new Number(2));
		tree.add(new Number(8));
		tree.add(new Number(6));
		tree.add(new Number(9));
		tree.add(new Number(5));
		tree.add(new Number(7));
		tree.add(new Number(10));
		tree.add(new Number(3));
		tree.add(new Number(1));
		try {
		tree.delete(new Number(4));
		}
		catch (MyEx e) {System.out.println(e.getMessage());}
		System.out.print("ler:");
		tree.ler();
		System.out.print("\nelr:");
		tree.elr();
		System.out.print("\nlre:");
		tree.lre();
	}
}
