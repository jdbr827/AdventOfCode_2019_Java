package year_2019.day11;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Map;

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
                    int hullxMax = paintedHull.keySet().stream().map((Point p) -> (int) p.x).max(Comparator.naturalOrder()).get();
                    int hullxMin = paintedHull.keySet().stream().map((Point p) -> (int) p.x).min(Comparator.naturalOrder()).get();
                    int hullyMax = paintedHull.keySet().stream().map((Point p) -> (int) p.y).max(Comparator.naturalOrder()).get();
                    int hullyMin = paintedHull.keySet().stream().map((Point p) -> (int) p.y).min(Comparator.naturalOrder()).get();
                    int xdiff = hullxMax-hullxMin;
                    int ydiff = hullyMax-hullyMin;
                    System.out.println(xdiff);
                    System.out.println(ydiff);

                    DefaultTableModel ndtm = new DefaultTableModel(ydiff + 1, xdiff + 1);
                    for (Point p : paintedHull.keySet()) {
                        ndtm.setValueAt(paintedHull.get(p),  hullyMax-p.y, p.x - hullxMin);
                    }
                    table1.setModel(ndtm);
                    table1.setDefaultRenderer(Object.class, new StatusColumnCellRenderer());
                    table1.prepareRenderer(new StatusColumnCellRenderer(), 0, 0);
                  } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

    }
}
