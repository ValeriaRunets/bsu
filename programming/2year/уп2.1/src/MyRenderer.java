import javax.swing.*;
import java.awt.*;
import java.util.*;

public class MyRenderer extends JLabel implements ListCellRenderer<Country> {

    HashMap<Country, String> map;
    HashMap<String, ImageIcon> icons;
    ImageIcon imageIcon;

    public MyRenderer(HashMap<Country, String> m) {
        setOpaque(true);
        icons=new HashMap<>();
        map=m;
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Country> list, Country country, int index,
                                                  boolean isSelected, boolean cellHasFocus) {

        String name = country.getName();
        if (icons.get(name)!=null) {
            imageIcon = icons.get(name);
        }else {
            imageIcon = new ImageIcon("shadow/flag_" + name + ".png");
            icons.put(name, imageIcon);
        }

        setIcon(imageIcon);
        setText(country.getName());

        if (isSelected) {
            setText(country.getName()+" "+map.get(country));
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        return this;
    }
}
