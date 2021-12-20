package year_2019.day13;

import java.awt.*;

public class BrickBreakerModel {
    Joystick joystick = new Joystick();
    int score = 0;


     public void populatePoint(Point p, int obj_id) {
        if (obj_id == 3) {
            joystick.paddleX = p.x;
        }
        if (obj_id == 4) {
            joystick.ballX = p.x;
        }
    }
}
