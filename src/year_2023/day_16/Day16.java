package year_2023.day_16;

import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.util.*;
import java.util.stream.Collectors;

import static year_2019.day15.model.CardinalDirection.*;

public class Day16 {

    int N;
    int M;

    final ContraptionComponent[][] lightContraption;

    private ContraptionComponent getLightContraptionAtLocation(CartesianPoint position) {
        return lightContraption[position.x][position.y];
    }

    class EnergyTest {
        List<Beam> beamList = new ArrayList<>();
        boolean[][][] visitedState;
        boolean[][] visitedPosition;
        int numVisited = 0;

        EnergyTest(CartesianPoint initialPosition, CardinalDirection initiallyFacing) {
            beamList.add(new Beam(initialPosition, initiallyFacing));
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
                beamList.remove(0).track();
                addBeamsFromSplitters();
            }
            return numVisited;
        }

        private void addBeamsFromSplitters() {
            for (int x = 0; x < N; x++) {
                for (int y = 0; y < M; y++) {
                    beamList.addAll(lightContraption[x][y].getSplitBeams().stream().map(Beam::new).collect(Collectors.toList()));
                    lightContraption[x][y].clearSplitBeams();
                }
            }
        }

        class Beam extends JavaRotatingMovingRobot {
            JavaRotatingMovingRobot robot;

            Beam(CartesianPoint position, CardinalDirection initiallyFacing) {
                super(initiallyFacing);
                this.position = position;
            }

            Beam(JavaRotatingMovingRobot robot) {
                this(robot.getPosition(), robot.getFacing());
            }

            protected Beam(CardinalDirection initiallyFacing) {
                super(initiallyFacing);
            }

            public void track() {
                while (position.isInBoundariesInclusive(0, N - 1, 0, M - 1) && !currentStateIsVisited()) {
                    markCurrentStateAsVisited();
                    markCurrentPositionAsVisited();
                    processLightContraptionAtCurrentLocation();
                    moveForward();
                }
            }

            private void processLightContraptionAtCurrentLocation() {
                getLightContraptionAtCurrentLocation().handleBeam(this);
            }

            private ContraptionComponent getLightContraptionAtCurrentLocation() {
                return getLightContraptionAtLocation(position);
            }

            private void markCurrentPositionAsVisited() {
                markPositionAsVisited(position);
            }

            private boolean currentStateIsVisited() {
                return isStateVisited(position, getFacing());
            }

            private void markCurrentStateAsVisited() {
                markStateAsVisited(position, getFacing());
            }
        }

    }


    public Day16(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        Character[][] matrix = scanner.scanAsChar2DArray();
        N = matrix.length;
        M = matrix[0].length;
        lightContraption = constructLightContraption(matrix);
    }

    private ContraptionComponent[][] constructLightContraption(Character[][] matrix) {
        ContraptionComponent[][] lightContraption = new ContraptionComponent[N][M];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                switch (matrix[x][y]) {
                    case '\\':
                        lightContraption[x][y] = new MajorDiagonalMirror();
                        break;
                    case '/':
                        lightContraption[x][y] = new MinorDiagonalMirror();
                        break;
                    case '|':
                        lightContraption[x][y] = new VerticalSplitter();
                        break;
                    case '-':
                        lightContraption[x][y] = new HorizontalSplitter();
                        break;
                    case '.':
                        lightContraption[x][y] = new EmptySpace();
                        break;
                }
            }
        }
        return lightContraption;
    }

    public int runEnergyTest(CartesianPoint initialPosition, CardinalDirection initiallyFacing) {
        return new EnergyTest(initialPosition, initiallyFacing).count_energized_tiles();
    }


    interface ContraptionComponent {
        void handleBeam(JavaRotatingMovingRobot beam);

        List<JavaRotatingMovingRobot> getSplitBeams();

        void clearSplitBeams();
    }

    class MajorDiagonalMirror implements ContraptionComponent {

        @Override
        public void handleBeam(JavaRotatingMovingRobot beam) {
            switch (beam.getFacing()) {
                case EAST:
                    beam.setFacing(SOUTH);
                    break;
                case SOUTH:
                    beam.setFacing(EAST);
                    break;
                case WEST:
                    beam.setFacing(NORTH);
                    break;
                case NORTH:
                    beam.setFacing(WEST);
                    break;
            }
        }

        @Override
        public List<JavaRotatingMovingRobot> getSplitBeams() {
            return new ArrayList<>();
        }

        @Override
        public void clearSplitBeams() {

        }
    }

    class MinorDiagonalMirror implements ContraptionComponent {

        @Override
        public void handleBeam(JavaRotatingMovingRobot beam) {
            switch (beam.getFacing()) {
                case EAST:
                    beam.setFacing(NORTH);
                    break;
                case NORTH:
                    beam.setFacing(EAST);
                    break;
                case WEST:
                    beam.setFacing(SOUTH);
                    break;
                case SOUTH:
                    beam.setFacing(WEST);
                    break;
            }
        }

        @Override
        public List<JavaRotatingMovingRobot> getSplitBeams() {
            return new ArrayList<>();
        }

        @Override
        public void clearSplitBeams() {

        }
    }

    class EmptySpace implements ContraptionComponent {
        @Override
        public void handleBeam(JavaRotatingMovingRobot beam) {
            // do nothing;
        }

        @Override
        public List<JavaRotatingMovingRobot> getSplitBeams() {
            return new ArrayList<>();
        }

        @Override
        public void clearSplitBeams() {

        }
    }

    class VerticalSplitter implements ContraptionComponent {
        List<JavaRotatingMovingRobot> splitterBeamList = new ArrayList<>();

        @Override
        public void handleBeam(JavaRotatingMovingRobot beam) {
            if (beam.getFacing() == EAST || beam.getFacing() == WEST) {
                beam.setFacing(NORTH);
                JavaRotatingMovingRobot newBeam = new JavaRotatingMovingRobot(beam.getPosition(), SOUTH);
                splitterBeamList.add(newBeam);
            }
        }

        @Override
        public List<JavaRotatingMovingRobot> getSplitBeams() {
            return splitterBeamList;
        }

        @Override
        public void clearSplitBeams() {
            splitterBeamList.clear();
        }
    }

    class HorizontalSplitter implements ContraptionComponent {
        List<JavaRotatingMovingRobot> splitterBeamList = new ArrayList<>();

        @Override
        public void handleBeam(JavaRotatingMovingRobot beam) {
            if (beam.getFacing() == NORTH || beam.getFacing() == SOUTH) {
                beam.setFacing(EAST);
                JavaRotatingMovingRobot newBeam = new JavaRotatingMovingRobot(beam.getPosition(), WEST);
                splitterBeamList.add(newBeam);
            }
        }

        @Override
        public List<JavaRotatingMovingRobot> getSplitBeams() {
            return splitterBeamList;
        }

        @Override
        public void clearSplitBeams() {
            splitterBeamList.clear();

        }
    }


}

