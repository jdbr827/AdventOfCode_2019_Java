package viewModelUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.Map;

public interface ColorTableModel {

     default JTable createColorJTable() {
        return new JTable() {
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        JavaPoint javaPoint = new JavaPoint(column, row);
                        return colorJLabel(l, javaPoint);
                    }
                };
            }
        };
    }

    JLabel colorJLabel(JLabel l, JavaPoint javaPoint);
}
