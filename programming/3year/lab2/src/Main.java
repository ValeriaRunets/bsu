import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main extends JFrame {
    ArrayList<Student> arr=new ArrayList<Student>();
    JTextArea text=new JTextArea();
    ArrayList<Group> groups=new ArrayList<>();
    static final int NUMGROUPS=3;
    Main(){
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JMenuBar bar=new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem open=new JMenuItem("Open");
        JMenuItem save=new JMenuItem("Save");
        JMenuItem add=new JMenuItem("Add");
        menu.add(open);
        menu.add(save);
        menu.add(add);
        bar.add(menu);
        setJMenuBar(bar);
        text.setEditable(false);
        add(text);
        setSize(500, 500);
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                arr.clear();
                readFromFile(arr);
                group();
                text.setText("");
                for (Group g:groups){
                    text.append(g.toString()+'\n');
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add();
            }
        });
    }

    public static void main(String[] args) {
        new Main();
    }

    void readFromFile(ArrayList<Student> arr){
        JFileChooser fileopen = new JFileChooser("E:\\Java\\lab2");
        fileopen.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try {
                FileInputStream in=new FileInputStream(file);
                ObjectInputStream stream=new ObjectInputStream(in);
                Student temp;
                while((temp=(Student) stream.readObject())!=null){
                    arr.add(temp);
                }
                in.close();
            } catch (ClassNotFoundException |IOException ex) {}
        }
    }
    public void group(){
        groups.clear();
        for (int i=0; i<NUMGROUPS;) {
            int k=i+1;
            groups.add(new Group(new ArrayList<Student>(arr.stream().filter((s)->(s.getGroup()==k)).collect(Collectors.toList())), k));
        }
        groups=new ArrayList<Group>(groups.stream().sorted(Comparator.comparing(Group::getAvg).reversed()).collect(Collectors.toList()));
    }
    public  void save(){
        JFileChooser fileopen = new JFileChooser("E:\\Java\\lab2");
        fileopen.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
        int ret = fileopen.showSaveDialog(Main.this);
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileopen.getSelectedFile();
                FileOutputStream out = new FileOutputStream(file);
                ObjectOutputStream os=new ObjectOutputStream(out);
                for (int i=0; i<arr.size(); i++) {
                    os.writeObject(arr.get(i));
                }
                out.close();
            }catch (IOException ex){}
        }
    }
    public void add(){
        JDialog dialog=new JDialog();
        Box box=Box.createHorizontalBox();
        dialog.add(box);
        JTextField surname=new JTextField("Surname");
        box.add(surname);
        JTextField group=new JTextField("Group");
        box.add(group);
        JTextField math=new JTextField("Math");
        box.add(math);
        JTextField language=new JTextField("Language");
        box.add(language);
        JTextField biology=new JTextField("Biology");
        box.add(biology);
        JButton ok=new JButton("OK");
        box.add(ok);
        dialog.setVisible(true);
        dialog.setSize(450, 80);
        dialog.setResizable(false);
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Student student = new Student(surname.getText(), Integer.parseInt(group.getText()),
                            Double.parseDouble(math.getText()), Double.parseDouble(language.getText()), Double.parseDouble(biology.getText()));
                    arr.add(student);
                    dialog.dispose();
                    group();
                    text.setText("");
                    for (Group g:groups){
                        text.append(g.toString()+'\n');
                    }
                }catch(NumberFormatException ex){JOptionPane.showMessageDialog(null, "Check entered information");}
            }
        });

    }
}
