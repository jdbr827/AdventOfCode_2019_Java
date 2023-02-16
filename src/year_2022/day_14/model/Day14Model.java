package year_2022.day_14.model;

import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
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
    @NotNull @Delegate Day14SolutionMethod solutionMethod;
    @NotNull @Delegate Day14ModelPartConstraint partConstraint;
    @NotNull @Delegate Day14DataReader dataReader;
    @NotNull Day14ModelView modelView;


    public int runModelOnly() {
        modelView.setCurrentSandPiece(SPAWN_POINT);
        while (!endCondition()) {
            solutionMethod.executeOneTimeStep();
        }
        return getNumSandPiecesFallenSoFar();
    }

    public int getNumSandPiecesFallenSoFar() {
        return dataReader.getNumAtRest();
    }

    public static Day14Model fromCornerRocksFile(String fileName,int part, int solutionMethodId, Day14ModelView modelView) {
        Set<JavaPoint> rocks = new Day14Scanner(fileName).readInRocks();
        Day14DataModel dataModel = new Day14DataModelImpl(rocks);
        modelView.setDataWriter(dataModel);
        return fromDataModel(dataModel, part, solutionMethodId, modelView);
    }

    static Day14Model fromDataModel(Day14DataModel dataModel, int part, int solutionMethodId, Day14ModelView modelView) {
        Day14ModelPartConstraint partConstraint = Day14ModelPartConstraint.createNew(part, dataModel);
        Day14SolutionMethod solutionMethod = Day14SolutionMethod.createNew(solutionMethodId, modelView, partConstraint, dataModel);

        return new Day14Model(solutionMethod, partConstraint, dataModel, modelView);

    }



}
