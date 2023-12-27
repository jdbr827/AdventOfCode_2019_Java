package year_2023.day_16;

import lombok.AllArgsConstructor;
import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.util.*;
import java.util.stream.Collectors;

import static year_2019.day15.model.CardinalDirection.*;

public class Day16 {

    int N;
    int M;
    LightContraption lightContraption;


    public Day16(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        Character[][] matrix = scanner.scanAsChar2DArray();
        this.N = matrix.length;
        this.M = matrix[0].length;
        lightContraption = new LightContraption(matrix);
    }

    public int runEnergyTest(CartesianPoint initialPosition, CardinalDirection initiallyFacing) {
        return new EnergyTest(initialPosition, initiallyFacing).count_energized_tiles();
    }

    public int runEnergyTest(JavaRotatingMovingRobot startingRobot) {
        return runEnergyTest(startingRobot.getPosition(), startingRobot.getFacing());
    }

    public int maximizeEnergyOutput() {
        List<JavaRotatingMovingRobot> candidateRobots = new ArrayList<>();

        for (int y = 0; y < M; y++) {
            candidateRobots.add(new JavaRotatingMovingRobot(new CartesianPoint(0, y), SOUTH));
            candidateRobots.add(new JavaRotatingMovingRobot(new CartesianPoint(N - 1, y), NORTH));
        }

        for (int x = 0; x < N; x++) {
            candidateRobots.add(new JavaRotatingMovingRobot(new CartesianPoint(x, 0), EAST));
            candidateRobots.add(new JavaRotatingMovingRobot(new CartesianPoint(x, M - 1), WEST));
        }

        return candidateRobots.stream().map(this::runEnergyTest).max(Comparator.naturalOrder())
                .orElseThrow(() -> new Error("No Candidate Robots were made."));

    }

    class EnergyTest {
        List<Beam> beamList = new ArrayList<>();
        boolean[][][] visitedState;
        boolean[][] visitedPosition;
        int numVisited = 0;

        EnergyTest(CartesianPoint initialPosition, CardinalDirection initiallyFacing) {
            beamList.add(new Beam(new JavaRotatingMovingRobot(initialPosition, initiallyFacing)));
            visitedState = new boolean[N][M][4];
            visitedPosition = new boolean[N][M];
        }

        private void markPositionAsVisited(CartesianPoint position) {
            if (!visitedPosition[position.x][position.y]) {
                numVisited++;
                visitedPosition[position.x][position.y] = true;
            }
        }

        private void markStateAsVisited(CartesianPoint position, CardinalDirection facing) {
            visitedState[position.x][position.y][facing.ordinal()] = true;
        }

        private boolean isStateVisited(CartesianPoint position, CardinalDirection facing) {
            return visitedState[position.x][position.y][facing.ordinal()];
        }

        public int count_energized_tiles() {
            while (!beamList.isEmpty()) {
                while (!beamList.isEmpty()) {
                    beamList.remove(0).track();
                }
                addBeamsFromSplitters();
            }
            return numVisited;
        }

        private void addBeamsFromSplitters() {
            addBeamsFromSplitter(HorizontalSplitter.getHORIZONTAL_SPLITTER());
            addBeamsFromSplitter(VerticalSplitter.getVERTICAL_SPLITTER());
        }

        private void addBeamsFromSplitter(Splitter splitter) {
            beamList.addAll(splitter.getSplitBeams().stream().map(Beam::new).collect(Collectors.toList()));
            splitter.clearSplitBeams();
        }

        @AllArgsConstructor
        class Beam {
            JavaRotatingMovingRobot robot;


            public void track() {
                while (robotInBounds() && !currentStateIsVisited()) {
                    markCurrentStateAsVisited();
                    markCurrentPositionAsVisited();
                    processLightContraptionAtCurrentLocation();
                    robot.moveForward();
                }
            }

            private boolean robotInBounds() {
                return robot.getPosition().isInBoundariesInclusive(0, N - 1, 0, M - 1);
            }

            private void processLightContraptionAtCurrentLocation() {
                getLightContraptionAtCurrentLocation().handleBeam(this.robot);
            }

            private ContraptionComponent getLightContraptionAtCurrentLocation() {
                return lightContraption.getLightContraptionAtLocation(robot.getPosition());
            }

            private void markCurrentPositionAsVisited() {
                markPositionAsVisited(robot.getPosition());
            }

            private boolean currentStateIsVisited() {
                return isStateVisited(robot.getPosition(), robot.getFacing());
            }

            private void markCurrentStateAsVisited() {
                markStateAsVisited(robot.getPosition(), robot.getFacing());
            }
        }
    }
}





