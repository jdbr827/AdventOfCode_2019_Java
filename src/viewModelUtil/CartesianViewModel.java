package viewModelUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class CartesianViewModel {
    protected Point cartesianOrigin = new Point(0, 0);
    protected DefaultTableModel dtm = new DefaultTableModel(1, 1);

    public JavaPoint convertCartesianToJava(CartesianPoint cartesianPoint) {
        return new JavaPoint(cartesianOrigin.y - cartesianPoint.y, cartesianPoint.x + cartesianOrigin.x);
    }

    public CartesianPoint convertJavaToCartesian(JavaPoint javaPoint) {
        return new CartesianPoint(javaPoint.y - cartesianOrigin.y, javaPoint.x - cartesianOrigin.x);
    }

    protected void addNewPointIfNecessary(DefaultTableModel dtm, CartesianPoint desiredPointCartesian) {
        Point desiredPointJava = convertCartesianToJava(desiredPointCartesian);

        addNewJavaPointIfNecessary(dtm, desiredPointJava);
    }

    private void addNewJavaPointIfNecessary(DefaultTableModel dtm, Point desiredPointJava) {
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

    public void setValueAtCartesian(CartesianPoint droidLocation, Object value) {
        addNewPointIfNecessary(dtm, droidLocation);
        JavaPoint javaPoint = convertCartesianToJava(droidLocation);
        dtm.setValueAt(value, javaPoint.x, javaPoint.y);
    }

    public void setModelToTable(JTable table1) {
        table1.setModel(dtm);
    }
}
