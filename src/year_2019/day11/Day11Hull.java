package year_2019.day11;

import javax.swing.*;
import java.awt.*;

public class Day11Hull {
    private JPanel panel1;
    private JTable table1;
    private JButton doEverythingButton;
    private JButton oneStepButton;

    private HullViewModel viewModel;
    Day11 controller;

    public Day11Hull(Day11 controller) {
        JFrame frame = new JFrame("Day11Hull");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        this.controller = controller;
        viewModel.setModelToTable(table1);

        doEverythingButton.addActionListener(e -> {
            Thread runDroid = new Thread(() -> {
                try {
                    controller.autopilot();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
            runDroid.start();
        });
        oneStepButton.addActionListener(e -> {
            try {
                controller.executeOneStep();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }

    private void createUIComponents() {
        viewModel = new HullViewModel();
        table1 = viewModel.createCartesianColorJTable();
    }

    public void setColor(Point position, Color color) {
        viewModel.setColorAtCartesian(position, color);
        table1.repaint();
    }


    public void setDroid(HullPaintingRobot robot) {
        viewModel.setDroid(robot);
        viewModel.updateRobot();
        table1.repaint();
    }

    public void updateRobot() {
        viewModel.updateRobot();
        table1.repaint();
    }
}
