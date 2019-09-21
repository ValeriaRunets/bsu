import javax.swing.table.AbstractTableModel;
import java.util.*;

public class MyTable extends AbstractTableModel{
    private ArrayList<Tour> tours;
    private boolean[] check;
    int sum;

    public MyTable(ArrayList<Tour> tours) {
        this.tours = tours;
        sum=0;
        check=new boolean[tours.size()];
    }

    public MyTable(ArrayList<Tour> tours, boolean[] check) {
        this.tours = tours;
        this.check = new boolean[tours.size()];
        for (int i=0; i<check.length; i++){
            this.check[i]=check[i];
        }
        setValueAt(0, 0, 0);
    }

    public boolean[] getCheck() {
        return check;
    }

    @Override
    public int getRowCount() {
        return tours.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public String getColumnName(int c) {
        String result = "";
        switch (c) {
            case 0:
                result = "Flag";
                break;
            case 1:
                result = "Description";
                break;
            case 2:
                result = "Price";
                break;
            case 3:
                result="Choose";
                break;
            case 4:
                result="Sum";
                break;
        }
        return result;
    }

    public Class<?> getColumnClass(int columnIndex) {
        Class clazz = String.class;
        switch (columnIndex) {
            case 3:
                clazz = Boolean.class;
                break;
            case 2:
                clazz=Integer.class;
                break;
        }
        return clazz;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return tours.get(rowIndex);
                case 1:
                    return tours.get(rowIndex).getDescription();
                case 2:
                    return tours.get(rowIndex).getPrice();
                case 3:
                    return check[rowIndex];
                case 4:
                    if (rowIndex==0){
                        return sum;
                    }
                default:
                    return "";
            }
    }

    public boolean isCellEditable(int row, int column) {
        return column == 3 || column==2||column==1;
    }

    public void setValueAt(Object aValue, int row, int column) {
        if (column == 2) {
            tours.get(row).setPrice((Integer)aValue);
        }
        if (aValue instanceof Boolean && column == 3) {
            check[row] = (boolean) aValue;
        }
        if (column==1){
            tours.get(row).setDescription((String)aValue);
        }
        if (column==1 && row>5){
            tours.get(row).setCountry((String) aValue);
            fireTableCellUpdated(row, 0);
        }
            sum = 0;
            for (int i = 0; i < tours.size(); i++) {
                if (check[i]) {
                    sum += tours.get(i).getPrice();
                }
            fireTableCellUpdated(0, 4);
            fireTableCellUpdated(row, column);
        }
    }
}
