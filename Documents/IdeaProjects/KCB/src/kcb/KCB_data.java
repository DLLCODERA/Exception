package kcb;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class KCB_data implements TableModel {
    private String[] title = {
            "Monday", "Tuesday", "wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };
    private String[][] data = new String[12][7];

    public KCB_data(){
        for (int i = 0; i < data.length; i++){
            for (int j = 0; j < data[i].length; j++){
                data[i][j] = "";
            }
        }
    }

    @Override
    public int getRowCount() {
        return 12;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return title[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = (String) aValue;

    }

    @Override
    public void addTableModelListener(TableModelListener l) {

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {

    }
}
