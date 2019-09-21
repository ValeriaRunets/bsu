import javax.swing.*;
import java.util.*;

public class MyFrame extends JFrame {
    JList<Country> countryList;
    public MyFrame() {
        Country usa = new Country("USA");
        Country belg=new Country("Belgium");
        Country gr=new Country("Greece");
        Country ken=new Country("Kenya");
        Country mor=new Country("Morocco");
        Country lie=new Country("Liechtenstein");

        DefaultListModel<Country> listModel = new DefaultListModel<>();
        listModel.addElement(usa);
        listModel.addElement(belg);
        listModel.addElement(gr);
        listModel.addElement(ken);
        listModel.addElement(mor);
        listModel.addElement(lie);

        HashMap<Country, String> map=new HashMap<>();
        map.put(usa, "Washington");
        map.put(belg, "Brussel");
        map.put(gr, "Athens");
        map.put(ken, "Nairobi");
        map.put(mor, "Rabat");
        map.put(lie, "Vaduz");

        countryList = new JList<>(listModel);
        add(new JScrollPane(countryList));
        countryList.setCellRenderer(new MyRenderer(map));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        MyFrame frame=new MyFrame();
    }
}
