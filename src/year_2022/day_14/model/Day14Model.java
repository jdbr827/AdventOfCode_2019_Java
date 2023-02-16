package year_2022.day_14.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Scanner;

import java.util.Set;

import static year_2022.day_14.model.Day14ModelImpl.SPAWN_POINT;

@RequiredArgsConstructor
public class Day14Model {
    @NotNull Day14SolutionMethod solutionMethod;
    @NotNull Day14ModelPartConstraint partConstraint;
    @NotNull @Getter Day14DataModel dataModel;

    public boolean endCondition() {
        return partConstraint.endCondition();
    }

    public int getNumSandPiecesFallenSoFar() {
        return solutionMethod.getNumSandPiecesFallenSoFar();
    }

    boolean allowsSand(JavaPoint javaPoint) {
        return partConstraint.allowsSand(javaPoint);
    }

    public PointState getStateOfPoint(JavaPoint javaPoint) {
        return getDataModel().getStateOfPoint(javaPoint);
    }


    public int floorY() {
        return partConstraint.floorY();
    }


    public int runModelOnly() {
        dataModel.setCurrentSandPiece(SPAWN_POINT);
        while (!endCondition()) {
            executeOneTimeStep();
        }
        return getNumSandPiecesFallenSoFar();
    }

    public static Day14Model fromCornerRocksFile(String fileName,int part, int solutionMethodId, Day14ModelView modelView) {
        Set<JavaPoint> rocks = new Day14Scanner(fileName).readInRocks();
        Day14DataModel dataModel = new Day14DataModelImpl(rocks);
        modelView.setDataModel(dataModel);
        return fromDataModel(dataModel, part, solutionMethodId, modelView);
    }

    static Day14Model fromDataModel(Day14DataModel dataModel, int part, int solutionMethodId, Day14ModelView modelView) {
        Day14ModelPartConstraint partConstraint = Day14ModelPartConstraint.createNew(part, dataModel);
        Day14SolutionMethod solutionMethod = Day14SolutionMethod.createNew(solutionMethodId, modelView, partConstraint);

        return new Day14Model(solutionMethod, partConstraint, dataModel);

    }




    public void executeOneTimeStep() {
        solutionMethod.executeOneTimeStep();
    }
}
