package year_2019.day15;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import static year_2019.day15.DroidMazeController.CardinalDirection;

public class DroidMazeView {
    final DroidMazeViewModel droidMazeViewModel = new DroidMazeViewModel();
    private JPanel panel1;
    JTable table1;
    private JButton northButton;
    private JButton southButton;
    private JButton westButton;
    private JButton eastButton;
    private JButton autopilotButton;
    private JButton oxygenTankDistanceButton;
    DroidMazeController controller;
    private Map<Point,Boolean> usingOxygenDistance = new HashMap<>();
    private boolean findingOxygenTankDistance = false;

    private void moveDroid(CardinalDirection direction) throws InterruptedException {
        if (!findingOxygenTankDistance) {
            controller.moveDroidFindingTank(direction);
        } else {
            controller.moveDroidFromTank(direction);
        }

    }


    public DroidMazeView(DroidMazeController droidMazeController) {
        this.controller = droidMazeController;
        table1.setModel(droidMazeViewModel.dtm);
        droidMazeViewModel.setValueAtCartesian(new Point(0, 0), 0);

        JFrame frame = new JFrame("Day15");
        panel1.setOpaque(true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        southButton.addActionListener(e -> tryMoveDroid(CardinalDirection.SOUTH));
        northButton.addActionListener(e -> tryMoveDroid(CardinalDirection.NORTH));
        eastButton.addActionListener(e -> tryMoveDroid(CardinalDirection.EAST));
        westButton.addActionListener(e -> tryMoveDroid(CardinalDirection.WEST));


        autopilotButton.addActionListener(new ActionListener() {;
            @Override
            public void actionPerformed(ActionEvent e) {
                droidMazeViewModel.droidLocation = new Point(0, 0);
                Thread runDroid = new Thread(() -> {
                    try {
                        controller.findOxygenTank();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
                runDroid.start();
            }
        });
        oxygenTankDistanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findingOxygenTankDistance = true;
                Thread runDroid = new Thread(() -> {
                    try {
                        controller.computeOxygenTankDistances();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
                runDroid.start();

            }
        });
    }

    private void tryMoveDroid(CardinalDirection direction) {
        try {
            moveDroid(direction);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void paintPoint(Point desiredPointCartesian, Color color) {
        droidMazeViewModel.setColor(desiredPointCartesian, color);
        table1.repaint();

    }

    private void createUIComponents() {
        table1 = new JTable() {
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        Point p = droidMazeViewModel.convertJavaToCartesian(new Point(column, row));
                        Point q = new Point(p.y, -p.x);
                        return colorJLabel(l, q);
                    }
                };
            }
        };
    }

    private JLabel colorJLabel(JLabel l, Point q) {
        l.setBackground(droidMazeViewModel.cartesianColorMap.getOrDefault(q, Color.GRAY));
        if (q.equals(droidMazeViewModel.droidLocation)) {
            l.setBackground(Color.PINK);
        }
        if (usingOxygenDistance.getOrDefault(q, false)) {
            l.setForeground(Color.BLUE);
        }
        //Return the JLabel which renders the cell.
        return l;
    }

    public void setOxygenDistance(Point droidLocation, int distance) {
        usingOxygenDistance.put((Point) droidLocation.clone(), true);
        droidMazeViewModel.setValueAtCartesian(droidLocation, distance);
    }




    public void setDistance(Point droidLocation, int distance) {
        droidMazeViewModel.setValueAtCartesian(droidLocation, distance);
    }

    public void setDroidLocation(Point droidLocation) {
        droidMazeViewModel.droidLocation = (Point) droidLocation.clone();
    }
};
