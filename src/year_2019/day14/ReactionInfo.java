package year_2019.day14;

import java.io.IOException;

public class ReactionInfo {
    final ChemicalInfo chemicalInfo;
    final static long ONE_TRILLION = 1000000000000L;

    ReactionInfo(String fileName) throws IOException {
        chemicalInfo = new ChemicalInfo(this, fileName);
    }


    public Long findLeastRequiredOreForOneFuel() {
        return findLeastRequiredOreForNFuel(1L);
    }

    private void balance() {
        chemicalInfo.balance();
    }

    public Long findFuelYouCanMakeWithATrillionOre() {
        long lowerBound = 1;
        while (findLeastRequiredOreForNFuel(lowerBound) < ONE_TRILLION) {
            lowerBound *= 2;
        }

        long upperBound = lowerBound;
        lowerBound /= 2;
        long midPoint = (upperBound + lowerBound) / 2;
        long dist = lowerBound;

        while (dist > 1) {

            long oreNeeded = findLeastRequiredOreForNFuel(midPoint);
            System.out.println(midPoint + " " + oreNeeded);
            if (oreNeeded < ONE_TRILLION) {
                lowerBound = midPoint;
            } else if (oreNeeded > ONE_TRILLION) {
                upperBound = midPoint;
            } else {
                return midPoint;
            }
            dist = upperBound - lowerBound;
            midPoint = (upperBound + lowerBound) / 2;
            chemicalInfo.balance();
            System.out.println(lowerBound + "  " + upperBound);
        }
        return lowerBound;

    }

    private long findLeastRequiredOreForNFuel(long N) {
        chemicalInfo.currentState.clear();
        chemicalInfo.currentState.put("FUEL", N);
        chemicalInfo.balance();
        return chemicalInfo.currentState.get("ORE");
    }

}
