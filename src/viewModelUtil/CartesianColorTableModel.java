package viewModelUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class CartesianColorTableModel extends CartesianTableModel implements ColorTableModel {
    protected Map<CartesianPoint, Color> cartesianColorMap = new HashMap<CartesianPoint, Color>();

    public void setColorAtCartesian(CartesianPoint desiredPointCartesian, Color color) {
        addNewPointIfNecessary(dtm, desiredPointCartesian);
        cartesianColorMap.put(desiredPointCartesian, color);
    }

    public abstract Color getBackgroundColorAtCartesian(Point q);

    public abstract Color getForegroundColorAtCartesian(Point q);


    public JLabel colorJLabel(JLabel l, JavaPoint javaPoint) {
        Point q = convertJavaToCartesian(javaPoint);
        l.setBackground(getBackgroundColorAtCartesian(q));
        l.setForeground(getForegroundColorAtCartesian(q));
        return l;
    }
}
