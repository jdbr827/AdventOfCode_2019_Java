package year_2022.day_14.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import viewModelUtil.JavaPoint;
import year_2022.day_14.Day14Scanner;
import year_2022.day_14.model.dataModel.Day14DataModel;
import year_2022.day_14.model.dataModel.Day14DataModelImpl;
import year_2022.day_14.model.dataModel.Day14DataReader;
import year_2022.day_14.model.partConstraints.Day14ModelPartConstraint;
import year_2022.day_14.model.solutionMethod.Day14SolutionMethod;

import java.util.Set;

import static year_2022.day_14.model.solutionMethod.Day14SolutionMethod.SPAWN_POINT;

@RequiredArgsConstructor
public class Day14Model {
    @NotNull Day14SolutionMethod solutionMethod;
    @NotNull Day14ModelPartConstraint partConstraint;
    @NotNull Day14DataReader dataReader;
    @NotNull Day14ModelView modelView;

    public boolean endCondition() {
        return partConstraint.endCondition();
    }

    public int getNumSandPiecesFallenSoFar() {
        return solutionMethod.getNumSandPiecesFallenSoFar();
    }

    public PointState getStateOfPoint(JavaPoint javaPoint) {
        return dataReader.getStateOfPoint(javaPoint);
    }


    public int floorY() {
        return partConstraint.floorY();
    }


    public int runModelOnly() {
        modelView.setCurrentSandPiece(SPAWN_POINT);
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

        return new Day14Model(solutionMethod, partConstraint, dataModel, modelView);

    }




    public void executeOneTimeStep() {
        solutionMethod.executeOneTimeStep();
    }
}
