import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

class MyRenderer extends DefaultTreeCellRenderer {
    ImageIcon faceIcon, tableIcon, bookIcon;
    public MyRenderer() {
        faceIcon = new ImageIcon("face.png");
        tableIcon = new ImageIcon("table.jpg");
        bookIcon = new ImageIcon("book.png");
    }
    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {

        super.getTreeCellRendererComponent(
                tree, value, sel,
                expanded, leaf, row,
                hasFocus);
        if (leaf && ((DefaultMutableTreeNode)value).getLevel()==3){
            setIcon(faceIcon);
        }else if(leaf && ((DefaultMutableTreeNode)value).getLevel()==2){
            setIcon(tableIcon);
        }else {
            setIcon(tableIcon);
        }
        return this;
    }
}