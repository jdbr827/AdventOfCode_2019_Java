package viewModelUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class CartesianViewModel {
    protected Point cartesianOrigin = new Point(0, 0);
    protected DefaultTableModel dtm = new DefaultTableModel(1, 1);

    public DTMPoint convertCartesianToDTM(CartesianPoint cartesianPoint) {
        return new DTMPoint(cartesianOrigin.y - cartesianPoint.y, cartesianPoint.x + cartesianOrigin.x);
    }

    public CartesianPoint convertDTMtoCartesian(DTMPoint DTMPoint) {
        return new CartesianPoint(DTMPoint.y - cartesianOrigin.y, DTMPoint.x - cartesianOrigin.x);
    }

    protected void addNewPointIfNecessary(DefaultTableModel dtm, CartesianPoint desiredPointCartesian) {
        Point desiredPointDTM = convertCartesianToDTM(desiredPointCartesian);

        addNewDTMPointIfNecessary(dtm, desiredPointDTM);
    }

    private void addNewDTMPointIfNecessary(DefaultTableModel dtm, Point desiredPointDTM) {
        int Y = desiredPointDTM.y;
        int X = desiredPointDTM.x;
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
        DTMPoint DTMPoint = convertCartesianToDTM(droidLocation);
        dtm.setValueAt(value, DTMPoint.x, DTMPoint.y);
    }

    public void setModelToTable(JTable table1) {
        table1.setModel(dtm);
    }
}
