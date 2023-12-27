package year_2023.day_16;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
                        lightContraption[x][y] = MajorDiagonalMirror.getMAJOR_DIAGONAL_MIRROR();
                        break;
                    case '/':
                        lightContraption[x][y] = MinorDiagonalMirror.getMINOR_DIAGONAL_MIRROR();
                        break;
                    case '|':
                        lightContraption[x][y] = VerticalSplitter.getVERTICAL_SPLITTER();
                        break;
                    case '-':
                        lightContraption[x][y] = HorizontalSplitter.getHORIZONTAL_SPLITTER();
                        break;
                    case '.':
                        lightContraption[x][y] = EmptySpace.getEMPTY_SPACE();
                        break;
                }
            }
        }
        return lightContraption;
    }

    public int runEnergyTest(CartesianPoint initialPosition, CardinalDirection initiallyFacing) {
        return new EnergyTest(initialPosition, initiallyFacing).count_energized_tiles();
    }

    public int runEnergyTest(JavaRotatingMovingRobot startingRobot) {
        return runEnergyTest(startingRobot.getPosition(), startingRobot.getFacing());
    }

    public int maximizeEnergyOutput() {
       List<JavaRotatingMovingRobot> candidateRobots = new ArrayList<>();

       for (int y=0; y<M; y++) {
           candidateRobots.add(new JavaRotatingMovingRobot(new CartesianPoint(0, y), SOUTH));
           candidateRobots.add(new JavaRotatingMovingRobot(new CartesianPoint(N-1, y), NORTH));
       }

       for (int x=0; x<N; x++) {
           candidateRobots.add(new JavaRotatingMovingRobot(new CartesianPoint(x, 0), EAST));
           candidateRobots.add(new JavaRotatingMovingRobot(new CartesianPoint(x, M-1), WEST));
       }

       System.out.println(candidateRobots.size());
       return candidateRobots.stream().map(this::runEnergyTest).max(Comparator.naturalOrder()).get();

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
                while (!beamList.isEmpty()) {
                    beamList.remove(0).track();
                }
                addBeamsFromSplitters();
            }
            return numVisited;
        }

        private void addBeamsFromSplitter(Splitter splitter) {
            beamList.addAll(splitter.getSplitBeams().stream().map(Beam::new).collect(Collectors.toList()));
            splitter.clearSplitBeams();
        }

        private void addBeamsFromSplitters() {
           addBeamsFromSplitter(HorizontalSplitter.getHORIZONTAL_SPLITTER());
           addBeamsFromSplitter(VerticalSplitter.getVERTICAL_SPLITTER());
        }

        @AllArgsConstructor
        class Beam {
            JavaRotatingMovingRobot robot;

            Beam(CartesianPoint position, CardinalDirection initiallyFacing) {
                robot = new JavaRotatingMovingRobot(position, initiallyFacing);
            }


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
                return getLightContraptionAtLocation(robot.getPosition());
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



    interface ContraptionComponent {
        void handleBeam(JavaRotatingMovingRobot beam);


    }

    interface Splitter extends ContraptionComponent {
        List<JavaRotatingMovingRobot> getSplitBeams();
        void clearSplitBeams();

    }

    static class MajorDiagonalMirror implements ContraptionComponent {

        @Getter private static final MajorDiagonalMirror MAJOR_DIAGONAL_MIRROR = new MajorDiagonalMirror();
        private MajorDiagonalMirror() {};



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
    }

    static class MinorDiagonalMirror implements ContraptionComponent {

        @Getter
        private static final MinorDiagonalMirror MINOR_DIAGONAL_MIRROR = new MinorDiagonalMirror();

        private MinorDiagonalMirror() {};


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
    }

    static class EmptySpace implements ContraptionComponent {

        @Getter
        private static final EmptySpace EMPTY_SPACE = new EmptySpace();
        private EmptySpace() {}

        @Override
        public void handleBeam(JavaRotatingMovingRobot beam) {
            // do nothing;
        }

    }

    static class VerticalSplitter implements Splitter {
        List<JavaRotatingMovingRobot> splitterBeamList = new ArrayList<>();

        @Getter
        private static final VerticalSplitter VERTICAL_SPLITTER = new VerticalSplitter();
        private VerticalSplitter(){}

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

    static class HorizontalSplitter implements Splitter {
        List<JavaRotatingMovingRobot> splitterBeamList = new ArrayList<>();

        @Getter
        private static final HorizontalSplitter HORIZONTAL_SPLITTER = new HorizontalSplitter();
        private HorizontalSplitter(){};

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

