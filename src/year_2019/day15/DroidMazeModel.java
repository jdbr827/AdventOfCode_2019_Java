package year_2019.day15;

import java.awt.*;
import static year_2019.day15.DroidMazeController.CardinalDirection;

public class DroidMazeModel {
    int xMax;
    int xMin;
    int yMax;
    int yMin;
    Point droidLocation = new Point(0, 0);

    void moveDroid(CardinalDirection direction) {
        droidLocation.translate(direction.velocity.x, direction.velocity.y);
    }
}
