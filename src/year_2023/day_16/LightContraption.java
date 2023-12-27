package year_2023.day_16;

import lombok.Getter;
import viewModelUtil.CartesianPoint;

import java.util.ArrayList;
import java.util.List;

import static year_2019.day15.model.CardinalDirection.*;

public class LightContraption {

    final ContraptionComponent[][] lightContraption;

    public ContraptionComponent getLightContraptionAtLocation(CartesianPoint position) {
        return lightContraption[position.x][position.y];
    }

    LightContraption(Character[][] matrix) {
        int N = matrix.length;
        int M = matrix[0].length;
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
        this.lightContraption = lightContraption;
    }

}

interface ContraptionComponent {
    void handleBeam(JavaRotatingMovingRobot beam);


}

interface Splitter extends ContraptionComponent {
    List<JavaRotatingMovingRobot> getSplitBeams();

    void clearSplitBeams();

}

class MajorDiagonalMirror implements ContraptionComponent {

    @Getter
    private static final MajorDiagonalMirror MAJOR_DIAGONAL_MIRROR = new MajorDiagonalMirror();

    private MajorDiagonalMirror() {
    }



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

class MinorDiagonalMirror implements ContraptionComponent {

    @Getter
    private static final MinorDiagonalMirror MINOR_DIAGONAL_MIRROR = new MinorDiagonalMirror();

    private MinorDiagonalMirror() {
    }



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

class EmptySpace implements ContraptionComponent {

    @Getter
    private static final EmptySpace EMPTY_SPACE = new EmptySpace();

    private EmptySpace() {
    }

    @Override
    public void handleBeam(JavaRotatingMovingRobot beam) {
        // do nothing;
    }

}

class VerticalSplitter implements Splitter {
    List<JavaRotatingMovingRobot> splitterBeamList = new ArrayList<>();

    @Getter
    private static final VerticalSplitter VERTICAL_SPLITTER = new VerticalSplitter();

    private VerticalSplitter() {
    }

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

class HorizontalSplitter implements Splitter {
    List<JavaRotatingMovingRobot> splitterBeamList = new ArrayList<>();

    @Getter
    private static final HorizontalSplitter HORIZONTAL_SPLITTER = new HorizontalSplitter();

    private HorizontalSplitter() {
    }

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

