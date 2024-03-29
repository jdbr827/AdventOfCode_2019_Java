package year_2019.day13;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Joystick {
    int paddleX;
    int ballX;
    BlockingQueue<Long> joystickInputs = new LinkedBlockingQueue<>();

    void doNextJoystickInput() {
        if (ballX < paddleX) {
            moveJoystickLeft();
        } else if (ballX == paddleX) {
            keepJoystickCenter();
        } else {
            moveJoystickRight();
        }
    }

    void moveJoystickRight() {
        joystickInputs.add(1L);
    }

    void keepJoystickCenter() {
        joystickInputs.add(0L);
    }

    void moveJoystickLeft() {
        joystickInputs.add(-1L);
    }


}
