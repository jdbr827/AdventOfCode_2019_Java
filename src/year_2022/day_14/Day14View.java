package year_2022.day_14;

import viewModelUtil.JavaPoint;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Day14View {
    private JTable table1;
    private JPanel panel1;
    private JButton executeOneTimeStepButton;
    private Day14ViewModel viewModel;
    Day14Controller controller;


    public Day14View(Day14Controller controller) {
        this.controller = controller;
        JFrame frame = new JFrame("2022 Day 14 Sand");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        viewModel.setModelToTable(table1);
        table1.repaint();

        executeOneTimeStepButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.executeOneTimeStep();
            }
        });
    }



    private void createUIComponents() {
        viewModel = new Day14ViewModel(this);
        table1 = viewModel.createColorJTable();
    }


    public void putRock(JavaPoint rock) {
        viewModel.setValueAtJava(rock, '#');
    }

    public void repaint() {
        table1.repaint();
    }
}
