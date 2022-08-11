package year_2019.day14;

public interface ChemicalState extends IChemicalState {
    void createChemical(String chemical, Long units);
    void destroyChemical(String chemical, Long units);


    private Long numTimesYouCanApplyReaction(Reaction reaction) {
                Long currentQuantityOfChemical = getAmountAvailableOfChemical(reaction.getOutputChemical());
                Integer reactionQuantityOfChemical = reaction.getOutputChemicalQuantity();
                return (Long) (long) Math.ceil(currentQuantityOfChemical / (double) reactionQuantityOfChemical);
    }


    @Override
    default void applyReactionsToDestroyChemical(Reaction reaction) {
        Long timesToRun = numTimesYouCanApplyReaction(reaction);
        destroyChemical(reaction.getOutputChemical(), timesToRun * reaction.getOutputChemicalQuantity());
        reaction.getInputChemicalInfo()
                .forEach((inputChem, reactionAmt) ->
                        createChemical(inputChem, timesToRun * reactionAmt)
                );
    }
}
