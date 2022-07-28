package year_2019.day14;

import java.io.IOException;
import java.util.function.Function;

public class ExponentialSearchUtil {


    public static <T> Long doExponentialSearch(CheckedFunction<Long, Comparable<T>> func, T target) throws IOException {
        long lowerBound = 1;
        while (func.apply(lowerBound).compareTo(target) < 0) {
            lowerBound *= 2;
        }

        long upperBound = lowerBound;
        lowerBound /= 2;
        long midPoint = (upperBound + lowerBound) / 2;
        long dist = lowerBound;

        while (dist > 1) {

            Comparable<T> currentVal = func.apply(midPoint);

            if (currentVal.compareTo(target) < 0) {
                lowerBound = midPoint;
            } else if (currentVal.compareTo(target) > 0) {
                upperBound = midPoint;
            } else {
                return midPoint;
            }
            dist = upperBound - lowerBound;
            midPoint = (upperBound + lowerBound) / 2;
        }
        return lowerBound;
    }
}
