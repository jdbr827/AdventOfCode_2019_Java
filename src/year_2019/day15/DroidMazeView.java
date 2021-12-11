package year_2019.day15;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static year_2019.day15.DroidMazeController.CardinalDirection;

public class DroidMazeView {
    private JPanel panel1;
    private JTable table1;
    private JButton northButton;
    private JButton southButton;
    private JButton westButton;
    private JButton eastButton;
    private JButton resetButton;
    Point cartesianOrigin;
    Point droidLocation;
    DroidMazeController controller;

    public DroidMazeView(DroidMazeController droidMazeController) {
        this.controller = droidMazeController;

        JFrame frame = new JFrame("Day15");
        panel1.setOpaque(true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        DefaultTableModel ndtm = new DefaultTableModel(1, 1);
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cartesianOrigin = new Point(0, 0);
                droidLocation = new Point(0, 0);
            }
        });
        southButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.moveDroid(CardinalDirection.SOUTH);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }
}
