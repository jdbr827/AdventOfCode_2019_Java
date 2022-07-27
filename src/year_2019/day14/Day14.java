package year_2019.day14;

import java.io.IOException;

public interface Day14 extends IDay14 {

    @Override
    default Long leastOreRequiredToMakeOneFuel(String reactionsFileName) throws IOException {
        IReactionInfo reactionInfo = new ReactionInfo(reactionsFileName);
        ChemicalEnvironment chemicalEnvironment = new ChemicalEnvironment(reactionInfo);
        return chemicalEnvironment.leastRequiredOreForOneFuel();
    }

    @Override
    default Long mostFuelForOneTrillionOre(String reactionsFileName) throws IOException {
        IReactionInfo reactionInfo = new ReactionInfo(reactionsFileName);
        ChemicalEnvironment chemicalEnvironment = new ChemicalEnvironment(reactionInfo);
        return chemicalEnvironment.fuelYouCanMakeWithATrillionOre();
    }





}
