package year_2019.day15;

import year_2019.CartesianPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DroidMazeView {
    DroidMazeViewModel droidMazeViewModel;
    private JPanel panel1;
    JTable table1;
    private JButton northButton;
    private JButton southButton;
    private JButton westButton;
    private JButton eastButton;
    private JButton autopilotButton;
    private JButton oxygenTankDistanceButton;
    private JTextField directionStackTextField;
    private JButton resetOriginButton;
    DroidMazeController controller;

    private boolean findingOxygenTankDistance = false;

    private void moveDroid(CardinalDirection direction) throws InterruptedException {
        if (!findingOxygenTankDistance) {
            controller.moveDroidFindingTank(direction);
        } else {
            controller.moveDroidFromTank(direction);
        }

    }

    public void setDirectionStack(String txt) {
        directionStackTextField.setText(txt);
    }


    public DroidMazeView(DroidMazeController droidMazeController) {
        this.controller = droidMazeController;


        JFrame frame = new JFrame("Day15");
        panel1.setOpaque(true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        droidMazeViewModel.setModelToTable(table1);
        droidMazeViewModel.setValueAtCartesian(new CartesianPoint(0, 0), 0);


        southButton.addActionListener(e -> tryMoveDroid(CardinalDirection.SOUTH));
        northButton.addActionListener(e -> tryMoveDroid(CardinalDirection.NORTH));
        eastButton.addActionListener(e -> tryMoveDroid(CardinalDirection.EAST));
        westButton.addActionListener(e -> tryMoveDroid(CardinalDirection.WEST));


        autopilotButton.addActionListener(new ActionListener() {;
            @Override
            public void actionPerformed(ActionEvent e) {
                droidMazeViewModel.droidLocation = new Point(0, 0);
                Thread runDroid = new Thread(() -> {
                    try {
                        controller.findOxygenTank();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
                runDroid.start();
            }
        });
        oxygenTankDistanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findingOxygenTankDistance = true;
                Thread runDroid = new Thread(() -> {
                    try {
                        controller.computeAllDistancesFromPoint();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
                runDroid.start();

            }
        });
        resetOriginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.resetOrigin();
            }
        });
    }

    private void tryMoveDroid(CardinalDirection direction) {
        try {
            moveDroid(direction);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void paintPoint(CartesianPoint desiredPointCartesian, Color color) {
        droidMazeViewModel.setColorAtCartesian(desiredPointCartesian, color);
        table1.repaint();

    }

    private void createUIComponents() {
        this.droidMazeViewModel = new DroidMazeViewModel();
        table1 = droidMazeViewModel.createCartesianColorJTable();
    }

    public void setOxygenDistance(CartesianPoint droidLocation, int distance) {
        droidMazeViewModel.foregroundColor.put((Point) droidLocation.clone(), Color.BLUE);
        droidMazeViewModel.setValueAtCartesian(droidLocation, distance);
    }


    public void setDistance(CartesianPoint droidLocation, int distance, Color color) {
        droidMazeViewModel.foregroundColor.put((Point) droidLocation.clone(), color);
        droidMazeViewModel.setValueAtCartesian(droidLocation, distance);
    }

    public void setDroidLocation(Point droidLocation) {
        droidMazeViewModel.droidLocation = (Point) droidLocation.clone();
    }

    public void repaint() {
        table1.repaint();
    }

    public void resetOrigin(CartesianPoint droidLocation) {
        droidMazeViewModel.foregroundColor.clear();
    }
};
