import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends  JPanel {
    JTextField editTextField;
    int row = -1;
    int column = -1;

    public View(MyTableModel tableModel) {
        super();

        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                changeText(table, tableModel);
            }
        });
        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT ||  keyEvent.getKeyCode() == KeyEvent.VK_RIGHT ||
                        keyEvent.getKeyCode() == KeyEvent.VK_DOWN || keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                    changeText(table, tableModel);
                }
            }
        });
        table.getTableHeader().setReorderingAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setShowGrid(true);
        table.setGridColor(Color.lightGray);

        setLayout(new BorderLayout());
        table.setCellSelectionEnabled(true);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        editTextField = new JTextField("");
        editTextField.setEditable(true);
        add(editTextField, BorderLayout.NORTH);
        editTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                row = table.getSelectedRow();
                column = table.getSelectedColumn();
                if(row == -1 || column == -1){
                    JOptionPane.showMessageDialog(null, "Please, check entered information", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                tableModel.setValueAt(editTextField.getText(), row, column);
            }
        });
    }

    private void changeText(JTable table, MyTableModel tableModel) {
        row = table.getSelectedRow();
        column = table.getSelectedColumn();
        editTextField.setText(tableModel.getFormula(row, column));
    }

}
