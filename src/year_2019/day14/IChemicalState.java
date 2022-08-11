package year_2019.day14;

public interface IChemicalState {

    public Long getAmountAvailableOfChemical(String chemical);
    public void createChemical(String chemical, Long units);
    public void destroyChemical(String chemical, Long units);
}
