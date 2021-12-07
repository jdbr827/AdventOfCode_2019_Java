package year_2019.day13;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import static year_2019.day11.Day11Hull.renderTable;
import static year_2019.day13.Day13.createGameGrid;
import static year_2019.day13.Day13.playGame;

public class BrickBreaker {
    JTable table1;
    private JButton showInitialStateButton;
    private JPanel panel1;
    private JButton playGameButton;
    private JButton button1;
    private JButton a0Button;
    private JButton button3;
    BlockingQueue<Integer> joystickInputs;

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
    public void renderBrickBreakerTable(Map<Point, Integer> gameGrid) {
        renderTable(table1, gameGrid, BrickBreaker::brickBreakerColorFunction);
//        table1.repaint();
//        table1.revalidate();
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
                    renderBrickBreakerTable(createGameGrid());

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        playGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread game = new Thread(() -> {
                    try {
                        playGame();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
                game.start();
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joystickInputs.add(0);
            }
        });
    }
}
