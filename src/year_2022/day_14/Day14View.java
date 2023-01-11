package year_2022.day_14;

import javax.swing.*;

public class Day14View {
    private JTable table1;
    private JPanel panel1;
    private JButton button1;
    private Day14ViewModel viewModel;


    public Day14View() {
        JFrame frame = new JFrame("2022 Day 14 Sand");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        viewModel.setModelToTable(table1);
        table1.repaint();

    }

    private void createUIComponents() {
        viewModel = new Day14ViewModel(this);
        table1 = viewModel.createColorJTable();
    }

    public static void main(String[] args) {
        new Day14View();
    }
}
