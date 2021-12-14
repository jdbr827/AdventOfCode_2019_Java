package year_2019.day11;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.function.Function;
import static year_2019.day11.HullPaintingRobot.Direction;

public class Day11Hull {
    private JPanel panel1;
    private JTable table1;
    private JButton doEverythingButton;
    private JButton oneStepButton;
    private DefaultTableModel dtm;
    private HullViewModel viewModel = new HullViewModel();
    int height;
    int width;
    Day11 controller;

    public static <T> Color hullPaintingColorFunction(T value) {
        if (value != null) {
            //System.out.println(row + " , " + col + " , " + val + " , " + value.equals(1L));
            if (value.equals(1L)) {
                return Color.WHITE;
            } else {
                return Color.GRAY;
            }
        } else {
            return Color.GRAY;
        }
    }

    public Day11Hull(Day11 controller) {
        JFrame frame = new JFrame("Day11Hull");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.controller = controller;
        viewModel.setModelToTable(table1);

        doEverythingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread runDroid = new Thread(() -> {
                    try {
                        controller.autopilot();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
                runDroid.start();
            }
        });
        oneStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.executeOneStep();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    public static <T> DefaultTableCellRenderer createRenderer(Function<T, Color> colorFunction) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                //Cells are by default rendered as a JLabel.
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                l.setBackground(colorFunction.apply((T) value));

                //Return the JLabel which renders the cell.
                return l;
            }
        };
    }

    private void createUIComponents() {
        table1 = new JTable() {
            public TableCellRenderer getCellRenderer(int row, int column) {
                return new DefaultTableCellRenderer() {
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        Point p = viewModel.convertJavaToCartesian(new Point(column, row));
                        Point q = new Point(p.y, -p.x);
                        return colorJLabel(l, q);
                    }
                };
            }
        };
    }

    private JLabel colorJLabel(JLabel l, Point q) {
        l.setBackground(viewModel.getBackgroundColorAtCartesian(q));
        l.setForeground(viewModel.getForegroundColorAtCartesian(q));
        return l;
    }

    public void setColor(Point position, Long aLong) {
        viewModel.setColorAtCartesian(position, hullPaintingColorFunction(aLong));
    }

    public void repaint() {
        table1.repaint();
    }

    public void setDroid(HullPaintingRobot robot) {
        viewModel.setDroid(robot);
    }


    public void moveRobotForward() {
        viewModel.updateRobot();
    }
}
