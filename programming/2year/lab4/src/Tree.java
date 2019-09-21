class MyEx extends Exception{
	private static final long serialVersionUID = 1L;

	public MyEx (String a) {super (a);}
}

public class Tree <T extends Comparable<T>>{
	class Node{
		T el;
		Node left, right;
		Node (T t){el=t;}
	}
	Node root;
	Tree(){root=null;}
	void add(T t) {
		if (root==null) {
			root=new Node(t);
		}
		else {
			Node temp=root;
			while (temp!=null) {
				if (temp.el.compareTo(t)>0) {
					if (temp.left==null) {
						temp.left=new Node(t);
						break;
					}
					else {
					temp=temp.left;
					}
				}
				if (temp.el.compareTo(t)<0) {
					if(temp.right==null) {
						temp.right=new Node(t);
						break;
					}
					else {
						temp=temp.right;
					}
				}
			}
		}
	}
	boolean find(T t) {
		Node temp=root;
		while (temp!=null && temp.el.compareTo(t)!=0) {
			if (temp.el.compareTo(t)>0) {
				temp=temp.left;
			}
			else {
				temp=temp.right;
			}
		}
		if (temp!=null) {
			return true;
		}
		else {
			return false;
		}
	}
	void delete(T t) throws MyEx {
		Node temp=root;
		Node temp1=root;
		if (!find(t)) {
			throw new MyEx(t+" Not found");
		}
		while (temp.el.compareTo(t)!=0) {
			if (temp.el.compareTo(t)>0) {
				temp1=temp;
				temp=temp.left;
			}
			else {
				temp1=temp;
				temp=temp.right;
			}
		}
		if (temp.right!=null) {
			Node buf1=temp.right;
			Node buf2=temp;
			while (buf1.left!=null) {
				buf2=buf1;
				buf1=buf1.left;
			}
			if (buf2==temp) {
				buf2.right=buf1.right;
			}
			else {
				buf2.left=buf1.right;
			}
			temp.el=buf1.el;
		}
		else if (temp.left!=null) {
			temp1.left=temp.left;
		}
		else {
			if (temp1.left==temp) {
				temp1.left=null;
			}
			else {
				temp1.right=null;
			}	
		}
	}
	public void ler() {
		ler(root);
	}
	private void ler(Node n) {
		if (n!=null) {
			ler(n.left);
			System.out.print(n.el+" ");
			ler(n.right);
		}
	}
	public void elr() {
		elr(root);
	}
	private void elr(Node n) {
		if (n!=null) {
			System.out.print(n.el+" ");
			elr(n.left);
			elr(n.right);
		}
	}
	public void lre() {
		lre(root);
	}
	private void lre(Node n) {
		if (n!=null) {
			lre(n.left);
			lre(n.right);
			System.out.print(n.el+" ");
		}
	}
}
