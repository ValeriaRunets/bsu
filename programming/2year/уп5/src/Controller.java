import javax.swing.*;
import java.awt.*;

public class Controller {
    private MyTableModel model;
    private View view;
    public Controller() {
        this.model = new MyTableModel(this);
        this.view = new View(this.model);
        JFrame frame = new JFrame("My Excel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this.view, BorderLayout.CENTER);
        frame.setSize(800, 500);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }

}