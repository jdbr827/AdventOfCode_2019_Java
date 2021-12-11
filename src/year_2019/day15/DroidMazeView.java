package year_2019.day15;

import year_2019.day11.Day11Hull;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static year_2019.day11.Day11Hull.createRenderer;
import static year_2019.day15.DroidMazeController.CardinalDirection;

public class DroidMazeView {
    private JPanel panel1;
    private JTable table1;
    private JButton northButton;
    private JButton southButton;
    private JButton westButton;
    private JButton eastButton;
    private JButton resetButton;
    Point cartesianOrigin = new Point(0, 0);
    Point droidLocation;
    DroidMazeController controller;
    DefaultTableModel ndtm = new DefaultTableModel(1, 1);

    public static Color backgroundColorFunction(int input) {
        switch(input) {
            case 0:
                return Color.BLACK;
            case 1:
                return Color.WHITE;
            case 2:
                return Color.GREEN;
            default:
                return Color.GRAY;
        }
    }

    public DroidMazeView(DroidMazeController droidMazeController) {
        this.controller = droidMazeController;
        table1.setModel(ndtm);
        ndtm.setValueAt( 1, 0, 0);
        DefaultTableCellRenderer renderer = Day11Hull.createRenderer(DroidMazeView::backgroundColorFunction);
        table1.setDefaultRenderer(Object.class, renderer);
        table1.prepareRenderer(renderer, 0, 0);

        JFrame frame = new JFrame("Day15");
        panel1.setOpaque(true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartesianOrigin = new Point(0, 0);
                droidLocation = new Point(0, 0);
            }
        });
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.moveDroid(CardinalDirection.SOUTH);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        });
        northButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.moveDroid(CardinalDirection.NORTH);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        eastButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.moveDroid(CardinalDirection.EAST);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        westButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.moveDroid(CardinalDirection.WEST);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void paintPoint(Point desiredPointCartesian, int result) {
        Point desiredPointJava = new Point(desiredPointCartesian.x + cartesianOrigin.x, cartesianOrigin.y - desiredPointCartesian.y);
        while (desiredPointJava.x < 0) {
            Vector<Integer> newCol = new Vector<>();
            for (int i=0; i<ndtm.getRowCount(); i++){ newCol.add(-1);}
//            ndtm.addColumn(0, newCol);
            DefaultTableModel newDTM = new DefaultTableModel(ndtm.getRowCount(), ndtm.getColumnCount() + 1);
            Vector<Vector> oldDataVector = ndtm.getDataVector();
            for (Vector v : oldDataVector) {
                v.insertElementAt(-1, 0);
            }
//            oldDataVector.insertElementAt(newCol, 0);
            Vector<Integer> newIdentifiers = new Vector<>();
            for (int i=0; i<newDTM.getColumnCount(); i++) { newIdentifiers.add(i);}
            ndtm.setDataVector(oldDataVector, newIdentifiers);
//            table1.moveColumn(ndtm.getColumnCount() - 1, 0);
            desiredPointJava.translate(1, 0);
            cartesianOrigin.translate(1, 0);
        }
        while (desiredPointJava.x >= ndtm.getColumnCount()) {
            Vector<Integer> newCol = new Vector<>();
            for (int i=0; i<ndtm.getRowCount(); i++){ newCol.add(-1);}
            ndtm.addColumn(0, newCol);
        }
        while(desiredPointJava.y < 0) {
            Vector<Integer> newRow = new Vector<>();
            for (int i=0; i<ndtm.getColumnCount(); i++){ newRow.add(-1);}
            ndtm.insertRow(0, newRow);
            desiredPointJava.translate(0, 1);
            cartesianOrigin.translate(0, 1);
        }
        while(desiredPointJava.y >= ndtm.getRowCount()) {
            Vector<Integer> newRow = new Vector<>();
            for (int i=0; i<ndtm.getColumnCount(); i++){ newRow.add(-1);}
            ndtm.addRow(newRow);
        }
        System.out.println(desiredPointJava);
        System.out.println(ndtm.getRowCount());
        System.out.println(ndtm.getColumnCount());
        ndtm.setValueAt(result, desiredPointJava.y, desiredPointJava.x);


    }
}
