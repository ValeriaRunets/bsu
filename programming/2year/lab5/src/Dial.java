import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Dial extends JFrame {
    private JLabel lab1=new JLabel("n");
    private JLabel lab2=new JLabel("First element");
    private JLabel lab3=new JLabel("Delta");
    private JLabel lab4=new JLabel("Filename");
    protected static JLabel empty = new JLabel("");
    private JButton save=new JButton("Save");
    private JButton show=new JButton("Show");
    private JTextField textField1=new JTextField();
    private JTextField textField2=new JTextField("output.txt");
    private JTextField textField3=new JTextField();
    private JTextField textField5=new JTextField();
    private JRadioButton linerRadioButton=new JRadioButton("Liner");
    private JRadioButton exponentialRadioButton=new JRadioButton("Exponential");
    private JTextField textField4=new JTextField();
    private Series ser;

    public Dial() {
        super();
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        save.setEnabled(false);
        textField4.setEditable(false);

        Box box = Box.createHorizontalBox();
        box.add(linerRadioButton);
        box.add(exponentialRadioButton);
        ButtonGroup group = new ButtonGroup();
        group.add(linerRadioButton);
        group.add(exponentialRadioButton);
        linerRadioButton.setSelected(true);
        box.setBorder(BorderFactory.createBevelBorder(1));
        getContentPane().add(box, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,3,25,3));
        panel.add(lab1);
        panel.add(lab2);
        panel.add(lab3);
        panel.add(textField5);
        panel.add(textField1);
        panel.add(textField3);
        panel.setBorder(BorderFactory.createEmptyBorder());
        getContentPane().add(panel);

        JPanel pan = new JPanel();
        pan.setLayout(new GridLayout(2,2,10,5));
        pan.add(lab4);
        pan.add(textField2);
        pan.add(empty);
        pan.add(save);
        pan.setBorder(BorderFactory.createEtchedBorder());
        getContentPane().add(pan, BorderLayout.EAST);

        Box box1 = Box.createHorizontalBox();
        box1.add(textField4);
        box1.add(show);
        box1.setBorder(BorderFactory.createTitledBorder("Output"));
        getContentPane().add(box1, BorderLayout.SOUTH);

        pack();

        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fE = textField1.getText();
                String d = textField3.getText();
                String n = textField5.getText();
                try{
                    if (Integer.parseInt(n)<0){
                        JOptionPane.showMessageDialog(null, "Please, entere n>0", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (linerRadioButton.isSelected()) {
                        ser = new Liner(Integer.parseInt(fE), Integer.parseInt(n), Double.parseDouble(d));
                    } else {
                        ser = new Exponential(Integer.parseInt(fE), Integer.parseInt(n), Double.parseDouble(d));
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please, check entered information", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                textField4.setText(ser.toString());
                save.setEnabled(true);
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fileName = textField2.getText();
                try {
                    ser.toFile(fileName);
                    save.setEnabled(false);
                }
                catch (IOException ex){JOptionPane.showMessageDialog(null, "Please, check your file", "Warning", JOptionPane.WARNING_MESSAGE);}
            }
        });
    }

    public static void main(String[] args) {
        Dial dialog = new Dial();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
