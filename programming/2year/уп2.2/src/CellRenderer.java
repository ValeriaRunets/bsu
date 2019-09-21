import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class CellRenderer extends JLabel implements TableCellRenderer {
    private  int size;
    ImageIcon imageIcon;
    public CellRenderer(int size) {
        this.size=size;
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        if (row==size){
            return this;
        }
        Tour objectCell = (Tour) value;
        imageIcon = new ImageIcon("shadow/flag_"+objectCell.getCountry() + ".png");
        setIcon(imageIcon);
        return this;
    }
}