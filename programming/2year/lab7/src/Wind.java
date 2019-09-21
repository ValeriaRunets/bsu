import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


class Pan extends JPanel {
    BufferedImage im=new BufferedImage(1530, 788, BufferedImage.TYPE_INT_ARGB);
    Pan(){
        super();
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(im, 0, 0, this);
    }
}
public class Wind extends JFrame{
    Pan panel=new Pan();
    JRadioButton bgr=new JRadioButton("Green");
    JRadioButton bred=new JRadioButton("Red");
    JRadioButton bor=new JRadioButton("Orange");
    JButton save=new JButton("Save");
    JButton load=new JButton("Load");
    Point pt;
    ImageIO img;
    String [] colors={"GREEN", "RED", "ORANGE"};
    Wind(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 700);
        JScrollPane scr=new JScrollPane(panel);
        scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setPreferredSize(new Dimension(1530, 788));

        bgr.setActionCommand("0");
        bred.setActionCommand("1");
        bor.setActionCommand("2");
        ButtonGroup group = new ButtonGroup();
        group.add(bgr);
        group.add(bred);
        group.add(bor);
        bgr.setSelected(true);

        Box box = Box.createHorizontalBox();
        box.add(bgr);
        box.add(bred);
        box.add(bor);

        Box box1 = Box.createHorizontalBox();
        box1.add(save);
        box1.add(load);

        add(box1, BorderLayout.SOUTH);
        add(box, BorderLayout.NORTH);
        add(scr);

        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    File f = new File("MyFile.png");
                    ImageIO.write(panel.im, "PNG", f);
                }
                catch(IOException ex){JOptionPane.showMessageDialog(null, "Please, check your file", "Warning", JOptionPane.WARNING_MESSAGE);}
            }
        });

        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileopen = new JFileChooser();
                    int ret = fileopen.showDialog(null, "Открыть файл");
                    if (ret == JFileChooser.APPROVE_OPTION) {
                        File file = fileopen.getSelectedFile();
                        BufferedImage img = ImageIO.read(file);
                        panel.im=new BufferedImage(1530, 788, BufferedImage.TYPE_INT_ARGB);
                        panel.im.getGraphics().drawImage(img, 0, 0, panel);
                        panel.repaint();
                    }
                }
                catch (IOException ex){JOptionPane.showMessageDialog(null, "Please, check your file", "Warning", JOptionPane.WARNING_MESSAGE);}
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter(){
            @Override
            public void mouseDragged(MouseEvent e) {
                Graphics gr=panel.getGraphics();
                Graphics g=panel.im.getGraphics();
                Color [] cl={Color.GREEN, Color.RED, Color.ORANGE};
                gr.setColor(cl[Integer.parseInt(group.getSelection().getActionCommand())]);
                g.setColor(cl[Integer.parseInt(group.getSelection().getActionCommand())]);
                gr.drawLine(pt.x, pt.y, e.getX(), e.getY());
                g.drawLine(pt.x, pt.y, e.getX(), e.getY());
                pt=e.getPoint();
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pt=e.getPoint();
            }
        });
    }
    public static void main(String []args){Wind w=new Wind();}
}

