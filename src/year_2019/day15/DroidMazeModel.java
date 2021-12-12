package year_2019.day15;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static year_2019.day15.DroidMazeController.CardinalDirection;

public class DroidMazeModel {
    Point droidLocation = new Point(0, 0);
    Map<Point, Integer> dfsDistance = new HashMap<>(); // distance from starting point of a point



    void moveDroid(CardinalDirection direction) {
        droidLocation.translate(direction.velocity.x, direction.velocity.y);
    }

}
