package year_2019.day08;

import viewModelUtil.CartesianColorTableModel;
import viewModelUtil.JavaPoint;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BIOSMessageTableModel extends CartesianColorTableModel {
    Map<JavaPoint, Color> javaColorMap = new HashMap<>();


    public BIOSMessageTableModel(int[][] grid) {
        super();
        for(int y=0; y<grid.length; y++) {
            for(int x = 0; x<grid[0].length; x++) {
                JavaPoint javaPoint = new JavaPoint(x, y);
                addNewJavaPointIfNecessary(dtm, javaPoint);
                javaColorMap.put(javaPoint, grid[y][x] == 1 ? Color.WHITE : Color.BLACK);
            }
        }
    }

    public JTable createCartesianColorJTable() {
        return new JTable() {
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        JavaPoint p = new JavaPoint(column, row);
                        return colorJLabel(l, p);
                    }
                };
            }
        };
    }

    @Override
    public Color getBackgroundColorAtCartesian(Point q) {
        return javaColorMap.getOrDefault(q, Color.GRAY);
    }

    @Override
    public Color getForegroundColorAtCartesian(Point q) {
        return getBackgroundColorAtCartesian(q);
    }


}
