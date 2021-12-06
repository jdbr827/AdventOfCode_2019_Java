package year_2019.day13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import static year_2019.day11.Day11Hull.renderTable;
import static year_2019.day13.Day13.createGameGrid;

public class BrickBreaker {
    private JTable table1;
    private JButton showInitialStateButton;
    private JPanel panel1;

    public static Color brickBreakerColorFunction(int value) {
        switch(value) {
            case 0:
                return Color.WHITE;
            case 1:
                return Color.BLACK;
            case 2:
                return Color.ORANGE;
            case 3:
                return Color.MAGENTA;
            case 4:
                return Color.RED;
            default:
                return Color.WHITE;
        }
    }

    public BrickBreaker() {
        JFrame frame = new JFrame("Day13");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        showInitialStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    renderTable(table1, createGameGrid(), BrickBreaker::brickBreakerColorFunction);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
