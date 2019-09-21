import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyTableModel extends AbstractTableModel {
    private final static int COLUMNS = 7;
    private final static int ROWS = 20;

    private GregorianCalendar[][] dates;
    private String[][] formulas;
    private String reg="=(МИН|МАКС)" +
            "\\((((((0[1-9]|1\\d|2[0-8])\\.02)|(0[1-9]|[1-2]\\d|30)\\.(04|06|09|11)|(0[1-9]|[1-2]\\d|30|31)\\.(01|03|05|07|08|10|12))\\.([1-9]\\d*),?\\s?)*([A-F][1-9][0-9]?,?\\s?)*)*\\)";

    public MyTableModel(Controller controller) {
        dates = new GregorianCalendar[ROWS][COLUMNS];
        formulas =  new String[ROWS][COLUMNS];
        for (int i = 0; i < ROWS; ++i) {
            for (int j = 0; j < COLUMNS; ++j) {
                formulas[i][j] = "";
            }
        }
    }

    public String getFormula(int row, int column) {
        if (row < 0 || row >= ROWS || column < 0 || column >= COLUMNS) {
            return "";
        } else {
            return formulas[row][column];
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column>0)
            return true;
        else return false;
    }

    @Override
    public int getColumnCount() {
        return COLUMNS;
    }

    @Override
    public int getRowCount() {
        return ROWS;
    }

    @Override
    public void setValueAt(Object value, int row, int column) {
        try {
            String str = (String) value;
            dates[row][column] = parseStr(str, row, column);
            fireTableDataChanged();
        }catch (NullPointerException ex){JOptionPane.showMessageDialog(null, "Please, check entered information", "Warning", JOptionPane.WARNING_MESSAGE);}
    }

    @Override
    public Object getValueAt(int row, int column) {
        if (column==0){
            return row+1;
        }
        if (!formulas[row][column].equals("")){
            dates[row][column]=parseStr(formulas[row][column], row, column);
        }
        if (dates[row][column]!=null) {
            SimpleDateFormat fmt = new SimpleDateFormat("dd.MM.yyyy");
            fmt.setCalendar(dates[row][column]);
            String dateFormatted = fmt.format(dates[row][column].getTime());
            return dateFormatted;
        }
        else return null;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0) {
            return "";
        } else if (column < COLUMNS) {
            return Character.toString((char) ('A' + (column - 1)));
        }
        return super.getColumnName(column);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            default:
                return String.class;
        }
    }
    private GregorianCalendar parseStr(String str, int row, int column) throws NullPointerException{
        GregorianCalendar date=null;
        if (str.matches("=(((0[1-9]|1\\d|2[0-8])\\.02)|(0[1-9]|[1-2]\\d|30)\\.(04|06|09|11)|(0[1-9]|[1-2]\\d|30|31)\\.(01|03|05|07|08|10|12))\\.([1-9]\\d*)[+-]\\d*")){
            StringTokenizer tokenizer=new StringTokenizer(str, ".+-=");
            int day = Integer.parseInt(tokenizer.nextToken());
            int month = Integer.parseInt(tokenizer.nextToken());
            int year = Integer.parseInt(tokenizer.nextToken());
            int num;
            if (str.contains("-")) {
                num = -Integer.parseInt(tokenizer.nextToken());
            }else{
                num = Integer.parseInt(tokenizer.nextToken());
            }
            date=new GregorianCalendar(year, month-1, day);
            date.add(Calendar.DAY_OF_MONTH, num);
            formulas[row][column]=str;
        }else if(str.matches("=([A-F][1-9][0-9]?)[+-]\\d*")){
            StringTokenizer tokenizer=new StringTokenizer(str, "+-=");
            String cell=tokenizer.nextToken();
            int num;
            if (str.contains("-")) {
                num = -Integer.parseInt(tokenizer.nextToken());
            }else{
                num = Integer.parseInt(tokenizer.nextToken());
            }
            int c=cell.charAt(0)+1-'A';
            int r=Integer.valueOf(cell.substring(1))-1;
            if (c==column && r==row){
                JOptionPane.showMessageDialog(null, "Please, check entered information", "Warning", JOptionPane.WARNING_MESSAGE);
                return null;
            }
            date=(GregorianCalendar) dates[r][c].clone();
            date.add(Calendar.DAY_OF_MONTH, num);
            formulas[row][column]=str;
        }else if(str.matches(reg)) {
            StringTokenizer tokenizer=new StringTokenizer(str, "(=,) ");
            String word=tokenizer.nextToken();
            TreeSet<GregorianCalendar> set=new TreeSet<>();
            while (tokenizer.hasMoreTokens()){
                String tok=tokenizer.nextToken();
                if (tok.matches("[A-F][1-9][0-9]?")){
                    int c=tok.charAt(0)+1-'A';
                    int r=Character.getNumericValue(tok.charAt(1))-1;
                    if (c==column && r==row){
                        JOptionPane.showMessageDialog(null, "Please, check entered information", "Warning", JOptionPane.WARNING_MESSAGE);
                        return null;
                    }
                    set.add(dates[r][c]);
                }
                else if (tok.matches("(((0[1-9]|1\\d|2[0-8])\\.02)|(0[1-9]|[1-2]\\d|30)\\.(04|06|09|11)|(0[1-9]|[1-2]\\d|30|31)\\.(01|03|05|07|08|10|12))\\.([1-9]\\d*)")){
                    StringTokenizer token=new StringTokenizer(tok, ".");
                    int day = Integer.parseInt(token.nextToken());
                    int month = Integer.parseInt(token.nextToken());
                    int year = Integer.parseInt(token.nextToken());
                    set.add(new GregorianCalendar(year, month-1, day));
                }else {
                    JOptionPane.showMessageDialog(null, "Please, check entered information", "Warning", JOptionPane.WARNING_MESSAGE);
                }
            }
            if (word.equals("МИН")){
                date=set.first();
            }
            if (word.equals("МАКС")){
                date=set.last();
            }
            formulas[row][column]=str;
        }else if(str.matches("=?(((0[1-9]|1\\d|2[0-8])\\.02)|(0[1-9]|[1-2]\\d|30)\\.(04|06|09|11)|(0[1-9]|[1-2]\\d|30|31)\\.(01|03|05|07|08|10|12))\\.([1-9]\\d*)")){
            StringTokenizer tokenizer=new StringTokenizer(str, ".=");
            int day=Integer.parseInt(tokenizer.nextToken());
            int month=Integer.parseInt(tokenizer.nextToken());
            int year=Integer.parseInt(tokenizer.nextToken());
            date=new GregorianCalendar(year, month-1, day);
            if (str.contains("=")){
                formulas[row][column]="";
            }
        }else{
            JOptionPane.showMessageDialog(null, "Please, check entered information", "Warning", JOptionPane.WARNING_MESSAGE);
        }
        return date;
    }
}
