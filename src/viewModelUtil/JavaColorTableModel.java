package viewModelUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static viewModelUtil.JavaPoint.convertJavaPointToDTMPoint;

public abstract class JavaColorTableModel extends CartesianTableModel implements ColorTableModel {
    protected Map<JavaPoint, Color> javaColorMap = new HashMap<JavaPoint, Color>();

    public void setColorAtJava(JavaPoint desiredPointJava, Color color) {
        addNewJavaPointIfNecessary(dtm, desiredPointJava);
        javaColorMap.put(desiredPointJava, color);
        DTMPoint dtmPoint = convertJavaPointToDTMPoint(desiredPointJava);
        dtm.fireTableCellUpdated(dtmPoint.x, dtmPoint.y);
    }

    @Override
    public JLabel colorJLabel(JLabel l, JavaPoint javaPoint) {
        l.setBackground(getBackgroundColorAtJava(javaPoint));
        l.setForeground(getForegroundColorAtJava(javaPoint));
        return l;
    }

    public abstract Color getForegroundColorAtJava(JavaPoint javaPoint);

    public abstract Color getBackgroundColorAtJava(JavaPoint javaPoint);
}
