package year_2019.day14;

import java.io.IOException;


public class ReactionInfo {
    ReactionInfoReader reactionInfoReader;
    final static long ONE_TRILLION = 1000000000000L;

    ReactionInfo(String fileName) throws IOException {
        reactionInfoReader = new ReactionInfoReader(fileName);
    }


    public Long findLeastRequiredOreForOneFuel() throws IOException {
        return findLeastRequiredOreForNFuel(1L);
    }


    public Long findFuelYouCanMakeWithATrillionOre() throws IOException {
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
            System.out.println(lowerBound + "  " + upperBound);
        }
        return lowerBound;

    }

    private long findLeastRequiredOreForNFuel(long N) throws IOException {
        return StoichDoer.findLeastRequiredOreForNFuel(reactionInfoReader, N);

    }

}
