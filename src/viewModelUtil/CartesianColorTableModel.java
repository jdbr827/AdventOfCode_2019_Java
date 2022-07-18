package viewModelUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class CartesianColorTableModel extends CartesianTableModel {
    protected Map<CartesianPoint, Color> cartesianColorMap = new HashMap<CartesianPoint, Color>();

    public void setColorAtCartesian(CartesianPoint desiredPointCartesian, Color color) {
        addNewPointIfNecessary(dtm, desiredPointCartesian);
        cartesianColorMap.put(desiredPointCartesian, color);
    }

    public abstract Color getBackgroundColorAtCartesian(Point q);

    public abstract Color getForegroundColorAtCartesian(Point q);

    public JTable createCartesianColorJTable() {
        return new JTable() {
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        CartesianPoint p = convertDTMtoCartesian(new DTMPoint(column, row));
                        Point q = new Point(p.y, -p.x);
                        return colorJLabel(l, q);
                    }
                };
            }
        };
    }

    public JLabel colorJLabel(JLabel l, Point q) {
        l.setBackground(getBackgroundColorAtCartesian(q));
        l.setForeground(getForegroundColorAtCartesian(q));
        return l;
    }
}
