package year_2019.day11;

import javax.swing.*;
import java.io.IOException;

public class Day11Hull {
    private JPanel panel1;
    private JTable table1;
    private JButton doEverythingButton;
    private JButton oneStepButton;
    private JTextField textField1;
    private JButton setCurrentPanelToButton;
    private JButton setCurrentPanelToButton1;

    private HullViewModel viewModel;
    Day11 controller;

    public Day11Hull() {
        JFrame frame = new JFrame("Day11Hull");
        textField1.setEditable(false);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        viewModel.setModelToTable(table1);
        controller = new Day11(viewModel);

        doEverythingButton.addActionListener(e -> tryAutopilotThread());
        oneStepButton.addActionListener(e -> tryExecuteOneStep());
        setCurrentPanelToButton.addActionListener(e -> controller.colorPoint(0L));
        setCurrentPanelToButton1.addActionListener(e -> controller.colorPoint(1L));
    }

    private void tryAutopilotThread() {
        Thread runDroid = new Thread(this::tryAutopilot);
        runDroid.start();
    }

    private void tryAutopilot() {
        try {
            controller.autopilot();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void tryExecuteOneStep() {
        try {
            controller.executeOneStep();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void createUIComponents() {
        viewModel = new HullViewModel(this);
        table1 = viewModel.createCartesianColorJTable();
    }

    public void repaint() {
        table1.repaint();
    }

    public void setNumberOfUniquePanelsPainted(int uniquePanelsPainted) {
        textField1.setText(String.valueOf(uniquePanelsPainted));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Day11Hull();
    }
}
