package year_2024.day_14;

import lombok.AllArgsConstructor;
import lombok.Getter;
import utils.AOCScanner;
import utils.ReadIn;
import viewModelUtil.CartesianPoint;

import java.awt.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Day14 {

    int N;
    int M;

    @AllArgsConstructor
    class BathroomSecurityRobot {

        @Getter
        CartesianPoint position;
        CartesianPoint velocity;

        int getQuadrant() {
            if (position.getX() == (N-1) / 2.0 || position.getY() == (M-1) / 2.0) {
                return 4;
            }
            if (position.getX() < (N-1)/2.0) {
                if (position.getY() < (M-1)/2.0) {
                    return 0;
                }
                return 1;
            } else {
                if (position.getY() < (M-1)/2.0) {
                    return 2;
                }
                return 3;
            }
        }

        void move() {
            position.x = position.x + velocity.x;
            while (position.x < 0){ position.x += N;}
            position.x %= N;


            position.y = position.y + velocity.y;
            while (position.y < 0) {position.y += M;}
            position.y %= M;
        }
    }

    Collection<BathroomSecurityRobot> bathroomSecurityRobots = new LinkedList<>();

    class Day14Scanner extends AOCScanner {

        Pattern robotPattern = Pattern.compile("p=(\\d*),(\\d*) v=(-?\\d*),(-?\\d*)");

        public Day14Scanner(String fileName) {
            super(fileName);
        }


        public void scan() {
            forEachLine((line) -> bathroomSecurityRobots.add(scanRobot(line)));
        }

        private BathroomSecurityRobot scanRobot(String line) {
            Matcher m = robotPattern.matcher(line);
            ReadIn.findOrElseThrow(m, "Cannot read robot pattern " + line);
            return new BathroomSecurityRobot(
                    new CartesianPoint(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2))),
                    new CartesianPoint(Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)))
            );
        }
    }

    public Day14(String inputFilename, int n, int m) {
        N = n;
        M = m;
        new Day14Scanner(inputFilename).scan();
    }

    public long safetyFactorAfter100Seconds() {
        long[] robotsByQuadrant = {0, 0, 0, 0, 0};
        for (BathroomSecurityRobot robot : bathroomSecurityRobots) {
            for (int i=0; i<100; i++) {
                robot.move();
            }
            robotsByQuadrant[robot.getQuadrant()]++;
        }
        return robotsByQuadrant[0] * robotsByQuadrant[1] * robotsByQuadrant[2] * robotsByQuadrant[3];
    }
}
