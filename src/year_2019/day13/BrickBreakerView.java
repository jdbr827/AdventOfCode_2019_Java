package year_2019.day13;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;

import static year_2019.day13.Day13.*;

public class BrickBreakerView {
    JTable table1;
    private JButton showInitialStateButton;
    private JPanel panel1;
    private JButton playGameButton;
    private JButton button1;
    private JButton a0Button;
    private JButton button3;
    JTextPane scoreTextPane;
    private JButton autopickButton;
    private JButton autopilotButton;
    BlockingQueue<Integer> Inputs;



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

    public static <T> DefaultTableCellRenderer createRenderer(Function<T, Color> colorFunction) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                //Cells are by default rendered as a JLabel.
                JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                l.setBackground(colorFunction.apply((T) value));
                //Return the JLabel which renders the cell.
                return l;
            }
        };
    }

    private void setup_table_model(Map<Point, Integer> gameGrid) {
        int xMax = gameGrid.keySet().stream().map((Point p) -> (int) p.x).max(Comparator.naturalOrder()).get();
        int yMax = gameGrid.keySet().stream().map((Point p) -> (int) p.y).max(Comparator.naturalOrder()).get();

        DefaultTableModel ndtm = new DefaultTableModel(xMax + 1, yMax + 1);
        for (Point p : gameGrid.keySet()) {
            ndtm.setValueAt(gameGrid.get(p),  p.x, p.y);
        }
        table1.setModel(ndtm);
        DefaultTableCellRenderer renderer = createRenderer(BrickBreakerView::brickBreakerColorFunction);
        table1.setDefaultRenderer(Object.class, renderer);
        table1.prepareRenderer(renderer, 0, 0);
    }

    public BrickBreakerView() {
        JFrame frame = new JFrame("Day13");
        panel1.setOpaque(true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        showInitialStateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setup_table_model(controller.createGameGrid());

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        playGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setup_table_model(controller.createGameGrid());
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                Thread game = new Thread(() -> {
                    try {
                        controller.playGame();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                game.start();
            }
        });
        a0Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.model.joystick.keepJoystickCenter();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.model.joystick.moveJoystickLeft();
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.model.joystick.moveJoystickRight();
            }
        });
        autopickButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.model.joystick.doNextJoystickInput();
            }
        });
        autopilotButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                controller.flipUseAutopilot(); //useAutopilot = !useAutopilot;
            }
        });
    }

    public void populatePoint(int x, int y, int obj_id) {
        // Switching around because of where the origin is in Java
        table1.setValueAt(obj_id, y, x);
    }

    public void setScore(int score) {
        scoreTextPane.setText(String.valueOf(score));
    }
}
