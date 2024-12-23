package year_2024.day_14;

import org.jetbrains.annotations.NotNull;
import viewModelUtil.CartesianPoint;
import viewModelUtil.JavaColorTableModel;
import viewModelUtil.JavaPoint;

import javax.swing.*;
import java.util.List;
import java.awt.event.*;
import java.util.Set;
import java.util.stream.Collectors;

import static year_2024.day_14.Day14Test.INPUT_FILENAME;

public class BotWatcher extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea1;
    private JLabel timesMovedLabel;
    private int timesMoved = 0;

    List<Day14.BathroomSecurityRobot> bathroomSecurityRobotList;

    public BotWatcher(List<Day14.BathroomSecurityRobot> bathroomSecurityRobotList) {
        this.bathroomSecurityRobotList = bathroomSecurityRobotList;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        for (Day14.BathroomSecurityRobot robot : bathroomSecurityRobotList) {
            robot.move();
        }
        timesMoved++;
        timesMovedLabel.setText(String.valueOf(timesMoved));
        @NotNull Set<CartesianPoint> robotLocations = bathroomSecurityRobotList.stream().map(Day14.BathroomSecurityRobot::getPosition).collect(Collectors.toSet());
        StringBuilder myGrid = new StringBuilder();
        for (int i=0; i<103; i++) {
            for (int j=0; j<101; j++) {
                if (robotLocations.contains(new CartesianPoint(i, j))) {
                    myGrid.append("X");
                } else {
                    myGrid.append(".");
                }
            }
            myGrid.append("\n");
        }
        textArea1.setText(myGrid.toString());
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        BotWatcher dialog = new BotWatcher((List<Day14.BathroomSecurityRobot>) new Day14(INPUT_FILENAME, 103 ,101).bathroomSecurityRobots);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);

    }
}
