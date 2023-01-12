package year_2022.day_14.view;

import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Controller;
import year_2022.day_14.view.Day14ViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Day14View {
    private JTable table1;
    private JPanel panel1;
    private JButton executeOneTimeStepButton;
    private JButton autopilotButton;
    private JLabel sandPiecesSoFar;
    private Day14ViewModel viewModel;
    Day14Controller controller;


    public Day14View(Day14Controller controller) {
        this.controller = controller;
        JFrame frame = new JFrame("2022 Day 14 Sand");
        sandPiecesSoFar.setText("0");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        viewModel.setModelToTable(table1);
        table1.repaint();

        executeOneTimeStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!controller.endCondition()) {
                    controller.executeOneTimeStep();
                }
                if (controller.endCondition()) {
                    executeOneTimeStepButton.setText("DONE!");
                }
            }
        });
        autopilotButton.addActionListener(e -> tryAutopilotThread());
    }

    private void tryAutopilotThread() {
        Thread autopilotThread = new Thread(this::tryAutopilot);
        autopilotThread.start();
    }

    private void tryAutopilot() {
            try {
                    controller.autoPilot();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }



    private void createUIComponents() {
        viewModel = new Day14ViewModel(this);
        table1 = viewModel.createColorJTable();
    }



    public void putRock(JavaPoint rock) {
        viewModel.setValueAtJava(rock, '#');
    }

    public void setSandPiecesSoFar(int num) {
        sandPiecesSoFar.setText(String.valueOf(num));
    }

    public void repaint() {
        table1.repaint();
    }

    public void setCurrentSandPiece(JavaPoint currentSandPiece) {
        viewModel.setValueAtJava(currentSandPiece, 'o');
    }
}
