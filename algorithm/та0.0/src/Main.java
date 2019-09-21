import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main implements Runnable {

    public static void main(String args[]) {
        new Thread(null, new Main(), "", 64 * 1024 * 1024).start();
    }

    public void run() {
        try{
        Tree tree = new Tree();
            Scanner scanner = new Scanner(new File("in.txt"));
            while (scanner.hasNext()) {
                tree.add(scanner.nextInt());
            }
            tree.setParam();
            int node=tree.check();
            tree.delete(node);
            tree.elr();
            tree.file.close();
        } catch (IOException ex){}
    }
}
class Tree  {
    class Node {
        int el;
        Node left, right;
        int MSL, h, min;

        Node(int t) {
            el = t;
        }
    }
    int  max;
    Node x;
    FileWriter file;
    Node root;

    Tree() throws IOException {
        root = null;
        x=null;
        max=-1;
        file = new FileWriter("out.txt");
    }

    public int check(){
        int node=check(root);
        return node;
    }

    private int check(Node n){
        if (n!=null) {
            check(n.left);
            check(n.right);
            if (max<n.MSL){
                max=n.MSL;
                x=n;
            }else if(max==n.MSL){
                if (n.min<x.min){
                    max=n.MSL;
                    x=n;
                }else if(n.min==x.min){
                    if (n.left!=null && n.right!=null&&n.left.min+n.right.min<x.left.min+x.right.min) {
                        max = n.MSL;
                        x = n;
                    }else if(n.left==null&&n.el+n.right.min<x.left.min+x.right.min){
                        max = n.MSL;
                        x = n;
                    }else if(n.right==null&&n.left.min+n.el<x.left.min+x.right.min){
                        max = n.MSL;
                        x = n;
                    }
                }
            }
        }
        if (x!=null){
            return x.el;
        }else{
            return 0;
        }
    }

    public void setParam() {
        setParam(root);
    }
    private void setParam(Node n) {
        if (n!=null) {
            setParam(n.left);
            setParam(n.right);
            if(n.right==null && n.left==null){
                n.h=0;
                n.MSL=0;
                n.min=n.el;
            }else if (n.right==null && n.left!=null){
                n.min=n.left.min;
                n.h=n.left.h+1;
                n.MSL=n.left.h+1;
            }else if(n.right!=null && n.left==null){
                n.min=n.right.min;
                n.h=n.right.h+1;
                n.MSL=n.right.h+1;
            }else{
                n.MSL=n.right.h+n.left.h+2;
                if (n.left.h>=n.right.h){
                    n.h=n.left.h+1;
                    n.min=n.left.min;
                }else{
                    n.h=n.right.h+1;
                    n.min=n.right.min;
                }
            }
        }
    }

    void add(int t) {
        if (root == null) {
            root = new Node(t);
        } else {
            Node temp = root;
            while (temp != null) {
                if (temp.el > t) {
                    if (temp.left == null) {
                        temp.left = new Node(t);
                        break;
                    } else {
                        temp = temp.left;
                    }
                }
                if (temp.el<t) {
                    if (temp.right == null) {
                        temp.right = new Node(t);
                        break;
                    } else {
                        temp = temp.right;
                    }
                }
                if (temp.el==t){temp=null;}
            }
        }
    }
    void delete(int t){
        Node temp=root;
        Node temp1=root;
        while (temp.el!=t) {
            if (temp.el>t) {
                temp1=temp;
                temp=temp.left;
            }
            else {
                temp1=temp;
                temp=temp.right;
            }
            if (temp==null){
                return;
            }
        }
        if (temp.left!=null && temp.right==null) {
            if (temp1.left==temp) {
                temp1.left = temp.left;
            }
            else{
                temp1.right=temp.left;
            }
            if (temp==root){
                root=temp.left;
            }
        }
        else if (temp.left==null && temp.right!=null){
            if (temp1.left==temp) {
                temp1.left = temp.right;
            }
            else{
                temp1.right=temp.right;
            }
            if (temp==root){
                root=temp.right;
            }
        }
        else if (temp.right!=null && temp.left!=null) {
            Node buf1=temp.right;
            Node buf2=temp;
            while (buf1.left!=null) {
                buf2=buf1;
                buf1=buf1.left;
            }
            if (buf2==temp) {
                temp.right=buf1.right;
            }
            else {
                buf2.left=buf1.right;
            }
            temp.el=buf1.el;
        }
        else {
            if (temp==root){
                root=null;
            }
            if (temp1.left==temp) {
                temp1.left=null;
            }
            else {
                temp1.right=null;
            }
        }
    }
    public void elr()throws IOException {
        elr(root);
    }
    private void elr(Node n) throws IOException {
        if (n!=null) {
            file.write(n.el+"\n");
            elr(n.left);
            elr(n.right);
        }
    }
}