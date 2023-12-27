package year_2023.day_16;

import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;

import java.util.*;

import static year_2019.day15.model.CardinalDirection.*;

public class Day16 {
    List<Beam> beamList = new ArrayList<>();
    int N;
    int M;
    final ContraptionComponent[][] lightContraption;
    boolean[][][] visitedState;
    boolean[][] visitedPosition;
    int numVisited = 0;

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


    public Day16(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        Character[][] matrix = scanner.scanAsChar2DArray();
        N = matrix.length;
        M = matrix[0].length;
        lightContraption = constructLightContraption(matrix);

        beamList.add(new Beam(EAST));

        visitedState = new boolean[N][M][4];
        visitedPosition = new boolean[N][M];
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

    public int count_energized_tiles() {
        while(!beamList.isEmpty()) {
            beamList.remove(0).track();
        }
        return numVisited;
    }


    interface ContraptionComponent {
        void handleBeam(Beam beam);
    }

    class MajorDiagonalMirror implements ContraptionComponent {

        @Override
        public void handleBeam(Beam beam) {
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

    class MinorDiagonalMirror implements ContraptionComponent {

        @Override
        public void handleBeam(Beam beam) {
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

    class EmptySpace implements ContraptionComponent {
        @Override
        public void handleBeam(Beam beam) {
            // do nothing;
        }
    }

    class VerticalSplitter implements ContraptionComponent {

        @Override
        public void handleBeam(Beam beam) {
            if (beam.getFacing() == EAST || beam.getFacing() == WEST) {
                beam.setFacing(NORTH);
                Beam newBeam = new Beam(beam.getPosition(), SOUTH);

                beamList.add(newBeam);
            }
        }
    }

    class HorizontalSplitter implements ContraptionComponent {

         @Override
        public void handleBeam(Beam beam) {
            if (beam.getFacing() == NORTH || beam.getFacing() == SOUTH) {
                beam.setFacing(EAST);
                Beam newBeam = new Beam(beam.getPosition(), WEST);
                beamList.add(newBeam);
            }
        }
    }

     class Beam extends JavaRotatingMovingRobot {

        Beam(CartesianPoint position, CardinalDirection initiallyFacing) {
            super(initiallyFacing);
            this.position = position;
        }

        protected Beam(CardinalDirection initiallyFacing) {
            super(initiallyFacing);
        }

         public void track() {
            while(position.isInBoundariesInclusive(0, N-1, 0, M-1) && !currentStateIsVisited()) {
                markCurrentStateAsVisited();
                markCurrentPositionAsVisited();
                lightContraption[position.x][position.y].handleBeam(this);
                moveForward();
            }
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

