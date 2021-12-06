package year_2019;

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
    private JButton button1;
    private DefaultTableModel dtm = new DefaultTableModel(0, 0);

    public static class StatusColumnCellRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

            //Cells are by default rendered as a JLabel.
            Component l = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            System.out.println(row + " , " + col);
            System.out.println(table.getValueAt(row, col));

//            if (table.getValueAt(row, col).equals(1)) {
//                l.setBackground(Color.BLACK);
//            }
            //Return the JLabel which renders the cell.
            return l;
        }
    }





    public Day11Hull() {
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Map<Point, Long> paintedHull = Day11.paintHull();
                    int hullxMax = paintedHull.keySet().stream().map((Point p) -> (int) p.x).max(Comparator.naturalOrder()).get();
                    int hullxMin = paintedHull.keySet().stream().map((Point p) -> (int) p.x).min(Comparator.naturalOrder()).get();
                    int hullyMax = paintedHull.keySet().stream().map((Point p) -> (int) p.y).max(Comparator.naturalOrder()).get();
                    int hullyMin = paintedHull.keySet().stream().map((Point p) -> (int) p.y).min(Comparator.naturalOrder()).get();
                    // Thankfully they are both even
                    // TODO: figure out acceptable odd case
                    int xdiff = hullxMax-hullxMin;
                    int ydiff = hullyMax-hullyMin;
                    System.out.println(xdiff);
                    System.out.println(ydiff);

                    int[][] hull = new int[(hullxMax-hullxMin)*2][(hullyMax-hullyMin)*2];

                    button1.setBackground(Color.RED);
                    DefaultTableModel dtm = (DefaultTableModel) table1.getModel();
                    DefaultTableModel ndtm = new DefaultTableModel(xdiff + 1, ydiff + 1);
                    for (Point p : paintedHull.keySet()) {
                        ndtm.setValueAt(paintedHull.get(p), p.x - hullxMin, p.y - hullyMin);
                    }

                    System.out.println(ndtm.getValueAt(xdiff, ydiff));
                    System.out.println(ndtm.getValueAt(xdiff - 2, ydiff));
                    table1.setModel(ndtm);
                    table1.setDefaultRenderer(Object.class, new StatusColumnCellRenderer());
                    table1.prepareRenderer(new StatusColumnCellRenderer(), 0, 0);
                  } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

//                table1.addColumn(new TableColumn(0, 0, new StatusColumnCellRenderer(), null));
//
//                System.out.println(table1.isCellEditable(0, 0));
//                table1.getCellEditor(0, 0);
////                table1.getColumnModel().getColumn(0).setCellRenderer(new StatusColumnCellRenderer());
////                table1.setModel(new DefaultTableModel(1, 2));
//                System.out.println(table1.getModel().getColumnCount());
//                //table1.setModel(table1.getColumnModel().getColumn(0).setCellRenderer(new StatusColumnCellRenderer()));
//                table1.setDefaultRenderer(Object.class, new StatusColumnCellRenderer());
//                System.out.println(table1.getRowCount());
//                table1.prepareRenderer(new StatusColumnCellRenderer(), 0, 0);
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Day11Hull");
        frame.setContentPane(new Day11Hull().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
