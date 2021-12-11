package year_2019.day15;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

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

    public DroidMazeView(DroidMazeController droidMazeController) {
        this.controller = droidMazeController;
        table1.setModel(ndtm);

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

    public void paintPoint(Point desiredPointCartesian, Color color) {
        Point desiredPointJava = new Point(desiredPointCartesian.x + cartesianOrigin.x, desiredPointCartesian.y - cartesianOrigin.y);
        while (desiredPointJava.x < 0) {
            ndtm.addColumn(null, new Vector<>());
            table1.moveColumn(ndtm.getColumnCount() - 1, 0);
            desiredPointJava.translate(1, 0);
            cartesianOrigin.translate(-1, 0);
        }
        while (desiredPointJava.x >= ndtm.getColumnCount()) {
            ndtm.addColumn(new Vector<>());
            desiredPointJava.translate(-1, 0);
            cartesianOrigin.translate(1, 0);
        }
        while(desiredPointJava.y < 0) {
            ndtm.insertRow(0, new Vector<>());
            desiredPointJava.translate(0, 1);
            cartesianOrigin.translate(0, 1);
        }
        while(desiredPointJava.y >= ndtm.getRowCount()) {
            ndtm.addRow(new Vector<>());
            desiredPointJava.translate(0, -1);
            cartesianOrigin.translate(0, -1);
        }
        System.out.println(desiredPointJava);
        System.out.println(ndtm.getRowCount());
        System.out.println(ndtm.getColumnCount());

    }
}
