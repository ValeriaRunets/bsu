import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyFrame extends JFrame {
    public MyFrame(){
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Tab 1", null, getPanel1(), "Lists");
        tabbedPane.addTab("Tab 2",null,  getPanel2(), "Buttons");
        tabbedPane.addTab("Tab 3", null,  getPanel3(), "RadioButtons");

        add(tabbedPane);
        setSize(1000, 500);
        setVisible(true);
    }
    protected JPanel getPanel1(){
        String[] arr1= {"Spring", "Summer", "Winter", "Autumn"};
        String[] arr2={"Rain", "Snow", "Sun", "Wind"};
        JPanel panel=new JPanel();
        panel.setLayout(new BorderLayout());
        DefaultListModel<String> model1=new DefaultListModel<String>();
        DefaultListModel<String> model2=new DefaultListModel<String>();
        for (String el : arr1)
            model1.addElement(el);
        for (String el : arr2)
            model2.addElement(el);
        JList<String> list1=new JList(model1);
        JList<String> list2=new JList(model2);
        panel.add(list1, BorderLayout.WEST);
        panel.add(list2, BorderLayout.EAST);
        JPanel butPanel= new JPanel();
        butPanel.setLayout(new BorderLayout());
        JButton but1=new JButton(">");
        JButton but2=new JButton("<");
        butPanel.add(but1, BorderLayout.NORTH);
        butPanel.add(but2, BorderLayout.SOUTH);
        panel.add(butPanel);

        but1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind[]=list1.getSelectedIndices();
                for (int i=ind.length-1; i>=0; i--) {
                    model2.addElement(model1.get(ind[i]));
                    model1.remove(ind[i]);
                }
            }
        });

        but2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int ind[]=list2.getSelectedIndices();
                for (int i=ind.length-1; i>=0; i--){
                    model1.addElement(model2.get(ind[i]));
                    model2.remove(ind[i]);
                }
            }
        });
        return panel;
    }
    protected JPanel getPanel2() {
        class MyMouseAdapter extends MouseAdapter{
            protected String buf;
            @Override
            public void mouseEntered(MouseEvent e){
                ((JButton)(e.getSource())).setBackground(Color.PINK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ((JButton)(e.getSource())).setBackground(Color.CYAN);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                buf=((JButton)(e.getSource())).getText();
                ((JButton)(e.getSource())).setText("Clicked!");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ((JButton)(e.getSource())).setText(buf);
            }
        }
        JPanel panel = new JPanel();
        final int T = 8;
        panel.setLayout(new GridLayout(T, T));
        MyMouseAdapter myMouse=new MyMouseAdapter();
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < T; j++) {
                JButton bn = new JButton(i * T + j + 1 + "");
                bn.setBackground(Color.CYAN);
                bn.addMouseListener(myMouse);
                panel.add(bn);
            }
        }
        return panel;
    }
    protected JPanel getPanel3() {
        JPanel panel= new JPanel();
        ButtonGroup radioGroup = new ButtonGroup();
        ButtonGroup group=new ButtonGroup();
        final ImageIcon[] icons = new ImageIcon[] {
                new ImageIcon("1.jpg"),
                new ImageIcon("2.jpg"),
                new ImageIcon("3.jpg"),
                new ImageIcon("4.jpg"),
        };
        for (int i = 0; i < 3; i++) {
            JRadioButton temp = new JRadioButton("Choose me!", icons[0]);
            temp.setPressedIcon(icons[2]);
            temp.setRolloverIcon(icons[1]);
            temp.setSelectedIcon(icons[3]);
            group.add(temp);
            panel.add(temp);
        }
        return panel;
    }
    public static void main(String[]args){MyFrame frame=new MyFrame();}
}
