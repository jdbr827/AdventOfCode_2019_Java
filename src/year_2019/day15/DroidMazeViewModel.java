package year_2019.day15;

import year_2019.CartesianViewModel;

import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class DroidMazeViewModel extends CartesianViewModel {
    Point droidLocation;
    DefaultTableModel dtm = new DefaultTableModel(1, 1);
    Map<Point, Color> cartesianColorMap = new HashMap<Point, Color>();

    public DroidMazeViewModel() {
    }

    void addNewPointIfNecessary(DefaultTableModel dtm, Point desiredPointCartesian) {
        Point desiredPointJava = convertCartesianToJava(desiredPointCartesian);
        //assert(convertJavaToCartesian(desiredPointJava).equals(desiredPointCartesian));

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

    void setColor(Point desiredPointCartesian, Color color) {
        addNewPointIfNecessary(dtm, desiredPointCartesian);
        cartesianColorMap.put(desiredPointCartesian, color);
    }

    void setValueAtCartesian(Point droidLocation, int distance) {
        Point javaPoint = convertCartesianToJava(droidLocation);
        dtm.setValueAt(distance, javaPoint.x, javaPoint.y);
    }
}