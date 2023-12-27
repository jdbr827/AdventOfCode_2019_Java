package year_2023.day_17;

import lombok.Getter;
import lombok.NoArgsConstructor;
import utils.AOCScanner;
import viewModelUtil.CartesianPoint;
import year_2019.day15.model.CardinalDirection;
import year_2023.day_16.JavaRotatingMovingRobot;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

import static year_2019.day15.model.CardinalDirection.EAST;
import static year_2023.day_17.Day17.Operation.*;


public class Day17 {
    Integer[][] heat;
    int N;
    int M;


    public Day17(String fileName) {
        AOCScanner scanner = new AOCScanner(fileName);
        Character[][] heatChars = scanner.scanAsChar2DArray();
        N = heatChars.length;
        M = heatChars[0].length;
        heat = new Integer[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                heat[i][j] = Integer.parseInt(heatChars[i][j].toString());
            }
        }
    }

    public int minimizeHeatLoss() {
        CrucibleModel crucibleModel = new CrucibleModel();
        return Arrays.stream(Operation.values())
                .map(crucibleModel::minimizeHeatLossGivenFirstOp)
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new Error("No Operations"));
    }


    enum Operation {
        FORWARD,
        LEFT,
        RIGHT
    }

    @NoArgsConstructor
    class CrucibleModel {
        final Crucible crucible = new Crucible(new CartesianPoint(0, 0), EAST);
        Stack<Operation> steps = new Stack<>();
        Stack<Integer> stepsInADirection = new Stack<>();
        Integer[][] smallestHeatLost = new Integer[N][M];


        public int minimizeHeatLossGivenFirstOp(Operation firstOp) {
            for (int i=0; i<N; i++) {
                for (int j=0; j<M; j++) {
                    smallestHeatLost[i][j] = Integer.MAX_VALUE;
                }
            }
            doDFS();
            return smallestHeatLost[N-1][M-1];
        }

        private void doDFS() {
            stepsInADirection.push(0);
            applyLegalOp(FORWARD);
            while (!steps.isEmpty()) {
                Operation nextOpToTry = FORWARD;
                while (!isLegal(nextOpToTry)) {
                    nextOpToTry = getNextOpToTry(nextOpToTry);
                    if (nextOpToTry == null) {
                        return;
                    }
                }
                applyLegalOp(nextOpToTry);
                if (crucible.isAtFinish()) {
                    System.out.println(smallestHeatLost[N-1][M-1]);
                }
            }

        }

        private void applyLegalOp(Operation nextOp) {
            if (nextOp == LEFT) {
                crucible.rotateCounterclockwise();
                stepsInADirection.push(1);
            }
            if (nextOp == RIGHT) {
                crucible.rotateClockwise();
                stepsInADirection.push(1);
            }
            if (nextOp == FORWARD) {
                stepsInADirection.push(stepsInADirection.peek() + 1);
            }
            steps.push(nextOp);
            crucible.moveForward();

        }

        private Operation getNextOpToTry(Operation lastOp) {
            if (lastOp == FORWARD) {
                return Operation.LEFT;
            }
            if (lastOp == Operation.LEFT) {
                return RIGHT;
            }
            if (steps.isEmpty()) {
                return null;
            }
            Operation prevOp = steps.pop();
            stepsInADirection.pop();
            undoLastOp(prevOp);
            return getNextOpToTry(prevOp);
        }

        private boolean isLegal(Operation nextOp) {
            if (nextOp == LEFT) {
                crucible.rotateCounterclockwise();
                boolean isInBounds = crucible.isForwardMoveAllowed();
                crucible.rotateClockwise();
                return isInBounds;
            }
            if (nextOp == RIGHT) {
                crucible.rotateClockwise();
                boolean isInBounds = crucible.isForwardMoveAllowed();
                crucible.rotateCounterclockwise();
                return isInBounds;
            }

            if (nextOp == FORWARD) {
                if (stepsInADirection.peek() >= 3) {
                    return false;
                }
                return crucible.isForwardMoveAllowed();
            }

            return true;
        }

        private void undoLastOp(Operation lastOp) {
            crucible.moveBackward();
            if (lastOp == LEFT) {
                crucible.rotateClockwise();
            }
            if (lastOp == RIGHT) {
                crucible.rotateCounterclockwise();
            }
        }

        class Crucible extends JavaRotatingMovingRobot {


            @Getter int heatLost = 0;
            boolean[][] visited = new boolean[N][M];

            public Crucible(CartesianPoint initialPosition, CardinalDirection initiallyFacing) {
                super(initialPosition, initiallyFacing);
            }

            public boolean isForwardMoveAllowed() {
                super.moveForward();
                boolean isInBounds = isInBounds() && !deJaVu() && heatLost + getHeatLostAtCurrentPosition() < smallestHeatLost[position.x][position.y];
                super.moveBackward();
                return isInBounds;
            }

            private boolean deJaVu() {
                return visited[position.x][position.y];
            }

            @Override
            public void moveForward() {
                super.moveForward();
                if (!isInBounds()) {
                    throw new ArrayIndexOutOfBoundsException("Crucible is out of bounds at Position " + getPosition());
                }
                heatLost += getHeatLostAtCurrentPosition();
                smallestHeatLost[position.x][position.y] = Math.min(heatLost, smallestHeatLost[position.x][position.y]);
                visited[position.x][position.y] = true; // TODO: Might need to beef up visiting by steps/facing

            }

            private Integer getHeatLostAtCurrentPosition() {
                return heat[getPosition().x][getPosition().y];
            }

            public boolean isInBounds() {
                return getPosition().isInBoundariesInclusive(0, N - 1, 0, M - 1);
            }

            public boolean isAtFinish() {
                return getPosition().equals(new CartesianPoint(N - 1, M - 1));
            }

            public void moveBackward() {
                heatLost -= heat[getPosition().x][getPosition().y];
                visited[position.x][position.y] = false;
                rotateClockwise();
                rotateClockwise();
                super.moveForward();
                rotateClockwise();
                rotateClockwise();

            }
        }
    }
}
