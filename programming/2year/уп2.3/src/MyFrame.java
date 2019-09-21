import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyFrame extends JFrame {
    ArrayList<Student> students=new ArrayList<>();
    public MyFrame() {
        DefaultMutableTreeNode top = makeTree();
        TreeModel treeModel = new DefaultTreeModel(top);
        TreeModelListener listener=new TreeModelListener() {
            @Override
            public void treeNodesChanged(TreeModelEvent e) {
                if (((DefaultMutableTreeNode)(e.getChildren()[0])).getLevel()!=3){
                    return;
                }
                int course=1, group=1;
                String name="yu";
                StringTokenizer tokenizer=new StringTokenizer(e.getChildren()[0].toString(), " ");
                while (tokenizer.hasMoreTokens()){
                    course=Integer.parseInt(tokenizer.nextToken());
                    group=Integer.parseInt(tokenizer.nextToken());
                    name=tokenizer.nextToken();
                }
                ((DefaultTreeModel) treeModel).insertNodeInto(new DefaultMutableTreeNode(course+" "+group+" "+name), (MutableTreeNode) top.getChildAt(course-1).getChildAt(group-1), 0);
                ((DefaultTreeModel) treeModel).removeNodeFromParent((DefaultMutableTreeNode)e.getChildren()[0]);
            }

            @Override
            public void treeNodesInserted(TreeModelEvent e) {
                StringTokenizer tokenizer=new StringTokenizer(e.getChildren()[0].toString(), " ");
                int course, group;
                String name;
                while (tokenizer.hasMoreTokens()){
                    course=Integer.parseInt(tokenizer.nextToken());
                    group=Integer.parseInt(tokenizer.nextToken());
                    name=tokenizer.nextToken();
                    students.add(new Student(course, group, name));
                }
            }

            @Override
            public void treeNodesRemoved(TreeModelEvent e) {
                StringTokenizer tokenizer=new StringTokenizer(e.getChildren()[0].toString(), " ");
                int course, group;
                String name;
                while (tokenizer.hasMoreTokens()){
                    course=Integer.parseInt(tokenizer.nextToken());
                    group=Integer.parseInt(tokenizer.nextToken());
                    name=tokenizer.nextToken();
                    students.remove(new Student(course, group, name));
                }
            }

            @Override
            public void treeStructureChanged(TreeModelEvent e) {

            }
        };
        readFile();
        addStudents(top);
        treeModel.addTreeModelListener(listener);
        JTree tree = new JTree(treeModel);
        tree.setCellRenderer(new MyRenderer());
        tree.setRootVisible(false);
        tree.setEditable(true);
        JButton del=new JButton("Delete");
        JButton add=new JButton("Add");
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tree.isSelectionEmpty()) {
                    DefaultMutableTreeNode node = new DefaultMutableTreeNode(tree.getSelectionPath().getLastPathComponent());
                    if (((DefaultMutableTreeNode) node.getUserObject()).getLevel() == 3) {
                        ((DefaultTreeModel) treeModel).removeNodeFromParent((DefaultMutableTreeNode) node.getUserObject());
                    }
                }
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((DefaultTreeModel) treeModel).insertNodeInto(new DefaultMutableTreeNode("1 1 NoName"), (MutableTreeNode) top.getChildAt(0).getChildAt(0), 0);
            }
        });
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JScrollPane scroll=new JScrollPane(tree);
        add(scroll);
        JPanel pan=new JPanel();
        pan.add(add);
        pan.add(del);
        add(pan, BorderLayout.SOUTH);
        setSize(400, 300);
        setVisible(true);
    }

    public DefaultMutableTreeNode makeTree(){
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Root");
        for (int i=1; i<5; i++) {
            DefaultMutableTreeNode course = new DefaultMutableTreeNode(i + "course");
            for (int j=1; j<4; j++) {
                course.add(new DefaultMutableTreeNode(j+"group"));
            }
            top.add(course);
        }
        return  top;
    }

    public void addStudents(DefaultMutableTreeNode top){
        for (Student student:students){
                DefaultMutableTreeNode node=(DefaultMutableTreeNode) top.getChildAt(student.getCourse()-1).getChildAt(student.getGroup()-1);
                DefaultMutableTreeNode stud=new DefaultMutableTreeNode(student.toString());
                node.add(stud);
        }
    }

    public void readFile(){
        try {
            Scanner scanner = new Scanner(new File("input.txt"));
            int course, group;
            String name;
            while (scanner.hasNext()) {
                course = scanner.nextInt();
                group = scanner.nextInt();
                name = scanner.next();
                students.add(new Student(course, group, name));
            }
        }catch (FileNotFoundException ex){}
    }

    public static void main(String[] args) {
        new MyFrame();
    }

}
