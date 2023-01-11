package year_2022.day_09;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Day9View {

    private JTable table1;
    private JButton rightButton;
    private JPanel panel1;
    private JButton lButton;
    private JButton uButton;
    private JButton dButton;
    private Day9ViewModel viewModel;
    private Day9Controller controller;

    public Day9View() {
        controller = new Day9Controller(this);
        JFrame frame = new JFrame("2022 Day 9 Rope");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        viewModel.setModelToTable(table1);
        viewModel.setController(controller);


        viewModel.updateRope(); // to initialize the Head

        rightButton.addActionListener(e -> controller.moveRope(IChessKing.MovementDirection.RIGHT));
        lButton.addActionListener(e -> controller.moveRope(IChessKing.MovementDirection.LEFT));
        uButton.addActionListener(e -> controller.moveRope(IChessKing.MovementDirection.UP));
        dButton.addActionListener(e -> controller.moveRope(IChessKing.MovementDirection.DOWN));
    }

    private void createUIComponents() {
        viewModel = new Day9ViewModel(this);
        table1 = viewModel.createColorJTable();
    }

    public static void main(String[] args) throws InterruptedException {
        new Day9View();
    }

    public void updateRope() {
        viewModel.updateRope();
    }

    public void repaint() {
        table1.repaint();
    }
}
