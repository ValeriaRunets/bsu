import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MyFrame extends JFrame {
    JTable table;
    TableModel model;
    ArrayList<Tour> tours=new ArrayList<>();
    public MyFrame() {
        Tour usa = new Tour("USA", "Far away", 2000);
        Tour belg=new Tour("Belgium", "A lot of excursions", 120);
        Tour gr=new Tour("Greece", "Rest on the beach", 550);
        Tour ken=new Tour("Kenya", "You will see kangaroo there!", 1500);
        Tour mor=new Tour("Morocco", "The country with interesting traditions", 1000);
        Tour lie=new Tour("Liechtenstein", "A small country with beautiful nature", 200);

        tours.add(usa);
        tours.add(belg);
        tours.add(gr);
        tours.add(ken);
        tours.add(mor);
        tours.add(lie);

        JButton but=new JButton("Add");
        model= new MyTable(tours);

        but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean check[]=((MyTable) model).getCheck();
                tours.add(new Tour("quest", "Print name", 100));
                model= new MyTable(tours, check);
                table.setModel(model);
                table.getColumn("Flag").setCellRenderer(new CellRenderer(tours.size()));
            }
        });

        table=new JTable();
        table.setModel(model);
        table.getColumn("Flag").setCellRenderer(new CellRenderer(tours.size()));
        table.setRowHeight(50);
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(but, BorderLayout.SOUTH);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
    }

    public static void main(String[] args) {
        MyFrame frame=new MyFrame();
        frame.setVisible(true);
    }
}
