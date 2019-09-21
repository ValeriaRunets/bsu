import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyWind extends JFrame{
    private JLabel lab;
    private JButton but;
    private JPanel pan;
    private Point pt, ptr;
    MyWind(){
        setLayout(new BorderLayout());
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500,300);
        pan=new JPanel();
        but= new JButton("Button");
        lab=new JLabel("");
        pan.setLayout(null);
        but.setSize(100,40);
        pan.add(but);
        add(lab, BorderLayout.SOUTH);
        add(pan);

        pan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                pt=e.getPoint();
                but.setLocation(pt);
            }
        });

        but.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                ptr = new Point(e.getXOnScreen()-pan.getLocationOnScreen().x, e.getYOnScreen()-pan.getLocationOnScreen().y);
                lab.setText(Double.toString(ptr.x)+"x"+Double.toString(ptr.y));
                if (e.isControlDown()) {
                    but.setLocation(ptr);
                }
            }
            public void mouseMoved(MouseEvent e){
                pt=e.getPoint();
                lab.setText(Double.toString(pt.x+but.getLocation().x)+"x"+Double.toString(pt.y+but.getLocation().y));
            }
        });
        pan.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e){
                pt=e.getPoint();
                lab.setText(Double.toString(pt.x)+"x"+Double.toString(pt.y));
            }
            public void mouseDragged(MouseEvent e) {
                pt = e.getPoint();
                lab.setText(Double.toString(pt.x) + "x" + Double.toString(pt.y));
            }
        });
        but.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                    if (but.getText().length()!=0) {
                        StringBuffer str = new StringBuffer(but.getText());
                        str.deleteCharAt(str.length() - 1);
                        but.setText(str.toString());
                    }
                }
                else {
                    but.setText(but.getText() + Character.toString(e.getKeyChar()));
                }
            }
        });
    }
    public static void main(String args[]){
        MyWind wind=new MyWind();
    }
}
