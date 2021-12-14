package year_2019;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public abstract class CartesianColorViewModel extends CartesianViewModel {
    protected DefaultTableModel dtm = new DefaultTableModel(1, 1);
    protected Map<Point, Color> cartesianColorMap = new HashMap<Point, Color>();

    private void addNewPointIfNecessary(DefaultTableModel dtm, Point desiredPointCartesian) {
        Point desiredPointJava = convertCartesianToJava(desiredPointCartesian);

        int Y = desiredPointJava.y;
        int X = desiredPointJava.x;
        while (Y < 0) {
            Vector<Integer> newCol = new Vector<>();
            for (int i = 0; i < dtm.getRowCount(); i++) {
                newCol.add(-1);
            }
            DefaultTableModel newDTM = new DefaultTableModel(dtm.getRowCount(), dtm.getColumnCount() + 1);
            Vector<Vector> oldDataVector = dtm.getDataVector();
            for (Vector v : oldDataVector) {
                v.insertElementAt(-1, 0);
            }
            Vector<Integer> newIdentifiers = new Vector<>();
            for (int i = 0; i < newDTM.getColumnCount(); i++) {
                newIdentifiers.add(i);
            }
            dtm.setDataVector(oldDataVector, newIdentifiers);
            Y += 1;
            cartesianOrigin.translate(1, 0);
        }
        while (Y >= dtm.getColumnCount()) {
            Vector<Integer> newCol = new Vector<>();
            for (int i = 0; i < dtm.getRowCount(); i++) {
                newCol.add(-1);
            }
            dtm.addColumn(0, newCol);
        }

        while (X < 0) {
            Vector<Integer> newRow = new Vector<>();
            for (int i = 0; i < dtm.getColumnCount(); i++) {
                newRow.add(-1);
            }
            dtm.insertRow(0, newRow);
            X += 1;
            cartesianOrigin.translate(0, 1);
        }
        while (X >= dtm.getRowCount()) {
            Vector<Integer> newRow = new Vector<>();
            for (int i = 0; i < dtm.getColumnCount(); i++) {
                newRow.add(-1);
            }
            this.dtm.addRow(newRow);
        }
    }

    public void setColorAtCartesian(Point desiredPointCartesian, Color color) {
        addNewPointIfNecessary(dtm, desiredPointCartesian);
        cartesianColorMap.put(desiredPointCartesian, color);
    }

    public void setValueAtCartesian(Point droidLocation, Object value) {
        addNewPointIfNecessary(dtm, droidLocation);
        Point javaPoint = convertCartesianToJava(droidLocation);
        dtm.setValueAt(value, javaPoint.x, javaPoint.y);
    }

     public void setModelToTable(JTable table1) {
        table1.setModel(dtm);
    }

    public abstract Color getBackgroundColorAtCartesian(Point q);

    public abstract Color getForegroundColorAtCartesian(Point q);
}
