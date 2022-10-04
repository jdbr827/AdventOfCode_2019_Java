package year_2019.day14;

import java.io.IOException;

public interface IDay14 {
    Long leastOreRequiredToMakeNFuel(Long N);
    Long mostFuelForNOre(Long N) throws IOException;

    static IDay14 create(String reactionsFileName) throws IOException {
        return new Day14(IReactionInfo.create(reactionsFileName));
    }

    default Long leastOreRequiredToMakeOneFuel() throws IOException {
        return leastOreRequiredToMakeNFuel(1L);
    }

}
