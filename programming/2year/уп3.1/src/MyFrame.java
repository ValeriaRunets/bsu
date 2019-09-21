import javax.swing.*;
import java.util.Timer;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

class Pan extends JPanel {
    BufferedImage im=new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
    Pan(){
        super();
        setSize(500, 500);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(im, 0, 0, null);
    }
}
public class MyFrame extends JFrame{
    Pan panel=new Pan();
    double x, y, t=0;
    final int x0=250, y0=250, rad=200;
    MyFrame(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        add(panel);

        TimerTask task=new TimerTask() {
            @Override
            public void run() {
                x=x0+rad*Math.cos(2*3.14*t/60);
                y=y0+rad*Math.sin(2*3.14*t/60);
                t++;
                if (t==60){
                    t=0;
                }
                panel.im=new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
                Graphics g=panel.im.getGraphics();
                g.setColor(Color.BLACK);
                g.drawOval(x0-rad, y0-rad, 2*rad, 2*rad);
                g.drawLine(x0, y0, (int)x, (int)y);
                repaint();
            }
        };
        Timer timer=new Timer();
        timer.schedule(task, 10, 1000);
        setSize(500, 500);
        setResizable(false);
    }
    public static void main(String []args){MyFrame w=new MyFrame();}
}
