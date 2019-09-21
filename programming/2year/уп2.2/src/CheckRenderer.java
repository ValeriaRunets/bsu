import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

class CheckRenderer extends JLabel implements TableCellRenderer {
    public CheckRenderer() {
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        JCheckBox checkBox = new JCheckBox();
        checkBox.setSelected(Boolean.TRUE.equals(value));
        return checkBox;
    }
}