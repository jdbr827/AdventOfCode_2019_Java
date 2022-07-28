package year_2019.day14;

import java.io.IOException;

public interface Day14 extends IDay14 {
    final static long ONE_TRILLION = 1000000000000L;

    @Override
    default Long leastOreRequiredToMakeOneFuel(String reactionsFileName) throws IOException {
        IReactionInfo reactionInfo = new ReactionInfo(reactionsFileName);
        ChemicalEnvironment chemicalEnvironment = new ChemicalEnvironment(reactionInfo);
        return chemicalEnvironment.leastRequiredOreForNFuel(1L);
    }

    @Override
    default Long mostFuelForOneTrillionOre(String reactionsFileName) throws IOException {
        IReactionInfo reactionInfo = new ReactionInfo(reactionsFileName);
        ChemicalEnvironment chemicalEnvironment = new ChemicalEnvironment(reactionInfo);
        return chemicalEnvironment.fuelYouCanMakeWithNOre(ONE_TRILLION);
    }






}
