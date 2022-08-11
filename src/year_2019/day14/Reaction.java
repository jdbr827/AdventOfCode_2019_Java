package year_2019.day14;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public class Reaction {
    private @Getter String outputChemical;
    private @Getter Map<String, Integer> inputChemicalInfo;
    private @Getter Integer outputChemicalQuantity;
}
