package year_2019.day11;

import viewModelUtil.CartesianPoint;

import javax.swing.*;
import java.io.IOException;

public class HullView {
    private JPanel panel1;
    private JTable table1;
    private JButton doEverythingButton;
    private JButton oneStepButton;
    private JTextField textField1;
    private JButton setCurrentPanelToButton;
    private JButton setCurrentPanelToButton1;

    private HullTableModel viewModel;
    Day11 controller;

    public HullView() {
        controller = new Day11(this);
        viewModel.setController(controller);
        JFrame frame = new JFrame("Day11Hull");
        textField1.setEditable(false);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        viewModel.setModelToTable(table1);


        doEverythingButton.addActionListener(e -> tryAutopilotThread());
        oneStepButton.addActionListener(e -> tryExecuteOneStep());
        setCurrentPanelToButton.addActionListener(e -> controller.colorPoint(0L));
        setCurrentPanelToButton1.addActionListener(e -> controller.colorPoint(1L));
        repaint();
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
        viewModel = new HullTableModel(this);
        table1 = viewModel.createColorJTable();
    }

    public void repaint() {
        table1.repaint();
    }

    public void setNumberOfUniquePanelsPainted(int uniquePanelsPainted) {
        textField1.setText(String.valueOf(uniquePanelsPainted));
    }

    public void updateRobot() {
        viewModel.updateRobot();
        repaint();
    }

    public void paintAtCartesian(CartesianPoint currentRobotPosition, Long paint) {
        viewModel.paintAtCartesian(currentRobotPosition, paint);
        repaint();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new HullView();
    }
}
