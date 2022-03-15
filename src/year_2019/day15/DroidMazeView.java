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
    private JButton findTank;
    private JButton AllDistancesButton;
    private JTextField directionStackTextField;
    private JButton resetOriginButton;
    private JComboBox goalBox;
    private JTextField textField1;
    private JButton startAnimationButton;
    private JTextField furthestDistanceField;
    DroidMazeController controller;

    private boolean findingOxygenTankDistance = false;

    private void moveDroid(CardinalDirection direction) throws InterruptedException {
       controller.attemptDroidMove(direction, controller.currentTracker);
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


        findTank.addActionListener(new ActionListener() {;
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread runDroid = new Thread(() -> {
                    try {
                        goalBox.setSelectedIndex(0);
                        controller.findOxygenTank();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                });
                runDroid.start();
            }
        });
        AllDistancesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findingOxygenTankDistance = true;
                Thread runDroid = new Thread(() -> {
                    try {
                        goalBox.setSelectedIndex(1);
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

        goalBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (goalBox.getSelectedItem().equals("TANK")) {
                    controller.setCurrentTrackerToTank();
                } else {
                    controller.setCurrentTrackerToAllPoints();
                }
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
        furthestDistanceField.setText("");


    }

    public int getFurthestDistance() {
        return furthestDistanceField.getText().isEmpty() ? 0 : Integer.parseInt(furthestDistanceField.getText());
    }

    public void setFurthestDistance(Integer distance) {
        furthestDistanceField.setText(distance.toString());
    }
};
