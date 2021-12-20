package year_2019.day11;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Day11Hull {
    private JPanel panel1;
    private JTable table1;
    private JButton doEverythingButton;
    private JButton oneStepButton;
    private JTextField textField1;
    private JButton setCurrentPanelToButton;
    private JButton setCurrentPanelToButton1;
    private JButton inputCurrentColorButton;
    private JTextField textField2;

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
        setCurrentPanelToButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.colorPoint(0L);
            }
        });
        setCurrentPanelToButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.colorPoint(1L);
            }
        });
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
