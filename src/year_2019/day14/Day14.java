package year_2019.day14;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import utils.BinarySearchUtil;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class Day14 implements IDay14 {

    IReactionInfo reactionInfo;


    public Long leastOreRequiredToMakeNFuel(Long desiredFuel) {

        class StoichDoer {
            final IChemicalState chemicalState = new ChemicalState(desiredFuel);

            StoichDoer() {}


            private long getRequiredOre() {
                Optional<String> unbalancedChemical;
                while ((unbalancedChemical = findUnbalancedChemical()).isPresent()) {
                    chemicalState.applyReactionsToDestroyChemical(reactionInfo.getReaction(unbalancedChemical.get()));
                }
                return chemicalState.getAmountAvailableOfChemical("ORE");
            }


            private Optional<String> findUnbalancedChemical() {
                return chemicalState.knownChemicals().stream()
                        .filter((chem) -> !(chem.equals("ORE")) && chemicalState.getAmountAvailableOfChemical(chem) > 0)
                        .findAny();
            }

        }
        return new StoichDoer().getRequiredOre();
    }

    public Long mostFuelForNOre(Long availableOre) throws IOException {
        return BinarySearchUtil.doExponentialSearch(this::leastOreRequiredToMakeNFuel, availableOre);
    }






}
