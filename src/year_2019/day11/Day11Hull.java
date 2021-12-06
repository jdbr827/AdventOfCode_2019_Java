package year_2019.day11;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;

public class Day11Hull {
    private JPanel panel1;
    private JTable table1;
    private JButton doEverythingButton;
    private JButton oneStepButton;
    private JTextField heightTextField;
    private JTextField widthTextField;
    private JButton createGridButton;
    private JButton placeRobotButton;
    private DefaultTableModel dtm;
    int height; int width;

    public static Color hullPaintingColorFunction(Object value) {
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

    public static class StatusColumnCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

            //Cells are by default rendered as a JLabel.
            Component l = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            //System.out.println(row + " , " + col);
            Object val = table.getValueAt(row, col);
            if (val != null) {
                 //System.out.println(row + " , " + col + " , " + val + " , " + value.equals(1L));
                if (value.equals(1L)) {
                    l.setBackground(Color.WHITE);
                } else {
                    l.setBackground(Color.GRAY);
                }
            } else {
                l.setBackground(Color.GRAY);
            }
            //Return the JLabel which renders the cell.
            return l;
        }
    }

    public Day11Hull() {
        JFrame frame = new JFrame("Day11Hull");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        doEverythingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Map<Point, Long> paintedHull = Day11.paintHull();
                    renderTable(paintedHull, Day11Hull::hullPaintingColorFunction);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        placeRobotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dtm = new DefaultTableModel(1, 1);
                dtm.setValueAt("^", 0, 0);
                table1.setModel(dtm);
                table1.prepareRenderer(new StatusColumnCellRenderer(), 0, 0);
            }
        });
    }

    private void renderTable(Map<Point, Long> objectsInTable, Function<Object, Color> colorFunction) {
        int hullxMax = objectsInTable.keySet().stream().map((Point p) -> (int) p.x).max(Comparator.naturalOrder()).get();
        int hullyMax = objectsInTable.keySet().stream().map((Point p) -> (int) p.y).max(Comparator.naturalOrder()).get();

        DefaultTableModel ndtm = new DefaultTableModel(hullxMax + 1, hullyMax + 1);
        for (Point p : objectsInTable.keySet()) {
            ndtm.setValueAt(objectsInTable.get(p),  p.x, p.y);
        }
        table1.setModel(ndtm);

        DefaultTableCellRenderer renderer = createRenderer(colorFunction);
        table1.setDefaultRenderer(Object.class, renderer);
        table1.prepareRenderer(renderer, 0, 0);
    }

    public static DefaultTableCellRenderer createRenderer(Function<Object, Color> colorFunction) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

                //Cells are by default rendered as a JLabel.
                Component l = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                l.setBackground(colorFunction.apply(value));

                //Return the JLabel which renders the cell.
                return l;
            }
        };
    }
}
