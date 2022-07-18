package year_2019.day08;

import viewModelUtil.CartesianColorTableModel;
import viewModelUtil.CartesianTableModel;
import viewModelUtil.ColorTableModel;
import viewModelUtil.JavaPoint;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BIOSMessageTableModel extends CartesianTableModel implements ColorTableModel {
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

    @Override
    public JLabel colorJLabel(JLabel l, JavaPoint javaPoint) {
        l.setBackground(getBackgroundColorAt(javaPoint));
        l.setForeground(getForegroundColorAt(javaPoint));
        return l;
    }

    public Color getBackgroundColorAt(JavaPoint javaPoint) {
       return javaColorMap.getOrDefault(javaPoint, Color.GRAY);
    }

    public Color getForegroundColorAt(JavaPoint javaPoint){
        return getBackgroundColorAt(javaPoint);
    }

}
