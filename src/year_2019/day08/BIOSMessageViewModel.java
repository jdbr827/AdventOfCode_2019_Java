package year_2019.day08;

import viewModelUtil.CartesianColorViewModel;
import viewModelUtil.CartesianPoint;
import viewModelUtil.DTMPoint;
import viewModelUtil.JavaPoint;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static viewModelUtil.JavaPoint.convertDTMPointToJavaPoint;

public class BIOSMessageViewModel extends CartesianColorViewModel {
    Map<JavaPoint, Color> javaColorMap = new HashMap<>();


    public BIOSMessageViewModel(int[][] grid) {
        super();
        for(int x=0; x<grid.length; x++) {
            for(int y = 0; y<grid[0].length; y++) {
                addNewPointIfNecessary(dtm, new CartesianPoint(y, -x));
                javaColorMap.put(new JavaPoint(x, y), grid[x][y] == 1 ? Color.WHITE : Color.BLACK);
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
                        JavaPoint p = convertDTMPointToJavaPoint(new DTMPoint(column, row));
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
