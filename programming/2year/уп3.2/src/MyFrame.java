import javax.swing.*;
import java.util.Timer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

class Pan extends JPanel {
    BufferedImage im=new BufferedImage(1530, 788, BufferedImage.TYPE_INT_ARGB);
    Pan(){
        super();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(im, 0, 0, null);
    }
}
public class MyFrame extends JFrame{

    Pan panel=new Pan();
    Image image= new ImageIcon("star.jpg").getImage();
    double x=0, y=0, t=0;
    int x0, y0, rad;
    double angle=0;
    MyFrame(){
        setSize(1530,788);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        Container pan=this.getContentPane();
        JSlider slider=new JSlider(0, 100, 30);
        String arr[]={"clockwise", "counterclockwise"};
        JList<String> list=new JList<>(arr);

        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                x0=(pan.getWidth()-100)/2;
                y0=pan.getHeight()/2;
                rad=Math.min(x0, y0)-20;
                int value=slider.getValue();
                if (list.isSelectedIndex(0)) {
                    angle += (double) value / rad;
                }
                else{
                    angle -= (double) value / rad;
                }
                x=x0+rad*Math.cos(angle);
                y=y0+rad*Math.sin(angle);
                panel.im=new BufferedImage(1530, 788, BufferedImage.TYPE_INT_ARGB);
                Graphics g=panel.im.getGraphics();
                g.setColor(Color.BLACK);
                g.drawOval(x0-rad, y0-rad, 2*rad, 2*rad);
                g.drawImage( image, (int)x-10, (int)y-10, null);
                repaint();
            }
        };
        Timer timer=new Timer();
        timer.schedule(task, 10, 100);
        setSize(500, 500);
        x0=this.getWidth()/2;
        y0=this.getHeight()/2;
        rad=Math.min(x0, y0)-20;
        setLayout(new BorderLayout());
        add(panel);
        add(slider, BorderLayout.NORTH);
        add(list, BorderLayout.WEST);
    }
    public static void main(String []args){MyFrame w=new MyFrame();}
}