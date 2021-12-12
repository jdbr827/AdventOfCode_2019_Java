package year_2019.day15;

import year_2019.day11.Day11Hull;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static year_2019.day15.DroidMazeController.CardinalDirection;

public class DroidMazeView {
    private JPanel panel1;
    JTable table1;
    private JButton northButton;
    private JButton southButton;
    private JButton westButton;
    private JButton eastButton;
    private JButton autopilotButton;
    Point cartesianOrigin = new Point(0, 0);
    Point droidLocation;
    DroidMazeController controller;
    DefaultTableModel ndtm = new DefaultTableModel(1, 1);
    Map<Point, Color> cartesianColorMap = new HashMap<>();

    public static Color backgroundColorFunction(int input) {
        switch (input) {
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
        ndtm.setValueAt(1, 0, 0);
        DefaultTableCellRenderer renderer = Day11Hull.createRenderer(DroidMazeView::backgroundColorFunction);
        table1.setDefaultRenderer(Object.class, renderer);
        table1.prepareRenderer(renderer, 0, 0);

        JFrame frame = new JFrame("Day15");
        panel1.setOpaque(true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        autopilotButton.addActionListener(new ActionListener() {
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
        autopilotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread runDroid = new Thread(() -> {
                    try {
                        controller.droidBrain();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
                runDroid.start();
            }
        });
    }

    public void paintPoint(Point desiredPointCartesian, Color color) {
        Point desiredPointJava = convertCartesianToJava(desiredPointCartesian);
        //assert(convertJavaToCartesian(desiredPointJava).equals(desiredPointCartesian));

        int Y = desiredPointJava.y;
        int X = desiredPointJava.x;
        while (Y < 0) {
            Vector<Integer> newCol = new Vector<>();
            for (int i = 0; i < ndtm.getRowCount(); i++) {
                newCol.add(-1);
            }
            DefaultTableModel newDTM = new DefaultTableModel(ndtm.getRowCount(), ndtm.getColumnCount() + 1);
            Vector<Vector> oldDataVector = ndtm.getDataVector();
            for (Vector v : oldDataVector) {
                v.insertElementAt(-1, 0);
            }
            Vector<Integer> newIdentifiers = new Vector<>();
            for (int i = 0; i < newDTM.getColumnCount(); i++) {
                newIdentifiers.add(i);
            }
            ndtm.setDataVector(oldDataVector, newIdentifiers);
            Y += 1;
            cartesianOrigin.translate(1, 0);
        }
        while (Y >= ndtm.getColumnCount()) {
            Vector<Integer> newCol = new Vector<>();
            for (int i = 0; i < ndtm.getRowCount(); i++) {
                newCol.add(-1);
            }
            ndtm.addColumn(0, newCol);
        }

        while (X < 0) {
            Vector<Integer> newRow = new Vector<>();
            for (int i = 0; i < ndtm.getColumnCount(); i++) {
                newRow.add(-1);
            }
            ndtm.insertRow(0, newRow);
            X += 1;
            cartesianOrigin.translate(0, 1);
        }
        while (X >= ndtm.getRowCount()) {
            Vector<Integer> newRow = new Vector<>();
            for (int i = 0; i < ndtm.getColumnCount(); i++) {
                newRow.add(-1);
            }
            ndtm.addRow(newRow);
        }
        cartesianColorMap.put(desiredPointCartesian, color);
        table1.repaint();

    }

    private Point convertCartesianToJava(Point cartesianPoint) {
        return new Point(cartesianOrigin.y - cartesianPoint.y, cartesianPoint.x + cartesianOrigin.x);
    }

    private Point convertJavaToCartesian(Point javaPoint) {
        return new Point(javaPoint.y - cartesianOrigin.y, javaPoint.x - cartesianOrigin.x);
    }

    private void createUIComponents() {
        table1 = new JTable() {
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        Point p = convertJavaToCartesian(new Point(column, row));
                        Point q = new Point(p.y, -p.x);
                        System.out.println(row + " " + column + " " + q + controller.model.droidLocation);
                        l.setBackground(cartesianColorMap.getOrDefault(q, Color.GRAY));
                        if (q.equals(controller.model.droidLocation)) {
                            l.setBackground(Color.PINK);
                        }
                        //Return the JLabel which renders the cell.
                        return l;
                    }
                };
            }
        };
    }


    public void setDistance(Point droidLocation, int distance) {
        Point javaPoint = convertCartesianToJava(droidLocation);
        ndtm.setValueAt(distance, javaPoint.x, javaPoint.y);
    }
};
