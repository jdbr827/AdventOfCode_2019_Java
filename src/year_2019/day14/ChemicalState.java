package year_2019.day14;

public interface ChemicalState {

    public Long getAmountAvailableOfChemical(String chemical);

    public void applyReaction(String chemical);
}
