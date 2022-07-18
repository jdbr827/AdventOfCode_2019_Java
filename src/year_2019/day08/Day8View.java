package year_2019.day08;

import javax.swing.*;

public class Day8View {
    private JPanel panel1;
    private JTable table1;
    BIOSMessageTableModel viewModel;
    private int[][] grid;

    public Day8View(int[][] grid) throws InterruptedException {
        this.grid = grid;
        JFrame frame = new JFrame("Day8Message");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        viewModel.setModelToTable(table1);
        table1.repaint();
    }

    private void createUIComponents() {
        viewModel = new BIOSMessageTableModel(this.grid);
        table1 = viewModel.createColorJTable();
    }

}
