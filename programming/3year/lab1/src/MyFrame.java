import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class MyFrame extends JFrame {
    JTextArea text1=new JTextArea();
    JTextArea text2=new JTextArea();
    JButton but=new JButton("Code");
    String text="";
    String key="Java";
    byte bytes[];
    byte res[];
    MyFrame() {
        super();
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        JMenuBar bar=new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem open=new JMenuItem("Open");
        JMenuItem save=new JMenuItem("Save");
        menu.add(open);
        menu.add(save);
        bar.add(menu);
        setJMenuBar(bar);
        setLayout(new BorderLayout());
        Box box=Box.createHorizontalBox();
        text1.setEnabled(false);
        JScrollPane pane1=new JScrollPane(text1);
        JScrollPane pane2=new JScrollPane(text2);
        box.add(pane1, BorderLayout.WEST);
        box.add(pane2, BorderLayout.EAST);
        add(box);
        add(but, BorderLayout.SOUTH);
        setSize(500, 300);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                if (!text.equals(text2.getText())) {
                    int result=JOptionPane.showConfirmDialog(null, "You didn't save changes. Are you sure you want to continue?", "Are you sure?", JOptionPane.YES_NO_OPTION);
                    if (result==JOptionPane.YES_OPTION){
                        dispose();
                    }
                }else{
                    dispose();
                }
            }
        });
        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ans=translate();
                text2.setText(ans.substring(0, ans.length()-1));
            }
        });
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text.equals(text2.getText())) {
                    read();
                }
                else{
                    int result=JOptionPane.showConfirmDialog(null, "You didn't save changes. Are you sure you want to continue?", "Are you sure?", JOptionPane.YES_NO_OPTION);
                    if (result==JOptionPane.YES_OPTION){
                        read();
                    }
                }
            }
        });
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }
    void save(){
        JFileChooser fileopen = new JFileChooser("E:\\Java\\git\\programming\\3year\\lab1");
        fileopen.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
        int ret = fileopen.showSaveDialog(MyFrame.this);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text", "txt");
        fileopen.setFileFilter(filter);
        if (ret == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileopen.getSelectedFile();
                FileOutputStream out = new FileOutputStream(file);
                out.write(text2.getText().getBytes("cp1251"));
            }catch (IOException ex){}
        }
        text=text2.getText();
    }
    void read(){
        JFileChooser fileopen = new JFileChooser("E:\\Java\\git\\programming\\3year\\lab1");
        fileopen.setFileFilter(new FileNameExtensionFilter("txt", "txt"));
        int ret = fileopen.showDialog(null, "Открыть файл");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileopen.getSelectedFile();
            try {
                FileInputStream in=new FileInputStream(file);
                bytes=in.readAllBytes();
                text1.setText(new String(bytes, "cp1251"));
            } catch (IOException ex) {
            }
        }
    }
    String translate(){
        String ans = "";
        try{
            byte[] txt = text1.getText().getBytes("cp1251");
            byte[] k = key.getBytes();
            res = new byte[txt.length];
            for (int i = 0; i < txt.length; i++) {
                res[i] = (byte) (txt[i] ^ k[i % k.length]);
            }
                ans = new String(res, "cp1251");
            }catch (IOException ex){}
        return ans;
    }

    public static void main(String[] args) {
        MyFrame f=new MyFrame();
    }

}
