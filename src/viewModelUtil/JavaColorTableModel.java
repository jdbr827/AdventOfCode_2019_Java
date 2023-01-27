package viewModelUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class JavaColorTableModel extends CartesianTableModel implements ColorTableModel {
    protected Map<JavaPoint, Color> javaColorMap = new HashMap<JavaPoint, Color>();

    public void setColorAtJava(JavaPoint desiredPointJava, Color color) {
        addNewJavaPointIfNecessary(dtm, desiredPointJava);
        javaColorMap.put(desiredPointJava, color);
        noteUpdateAtJavaPoint(desiredPointJava);

    }

    @Override
    public JLabel colorJLabel(JLabel l, JavaPoint javaPoint) {
        l.setBackground(getBackgroundColorAtJava(javaPoint));
        l.setForeground(getForegroundColorAtJava(javaPoint));
        return l;
    }

    public Color getForegroundColorAtJava(JavaPoint javaPoint) {
        return getBackgroundColorAtJava(javaPoint);
    }

    public abstract Color getBackgroundColorAtJava(JavaPoint javaPoint);
}
