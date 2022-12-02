package utils;


import java.io.IOException;
import java.util.function.Function;

public class BinarySearchUtil {


    public static <T> Long doExponentialSearch(Function<Long, Comparable<T>> func, T target) throws IOException {
        long lowerBound = 1;
        while (func.apply(lowerBound).compareTo(target) < 0) {
            lowerBound *= 2;
        }

        long upperBound = lowerBound;
        lowerBound /= 2;
        return doBinarySearch(func, target, lowerBound, upperBound);
    }

    /**
     * Returns x such that func(x) = target assuming func is non-decreasing over [lowerbound, upperbound]
     * @param func
     * @param target
     * @param lowerBound
     * @param upperBound
     * @param <T>
     * @return
     * @throws IOException
     */
    private static <T> long doBinarySearch(Function<Long, Comparable<T>> func, T target, long lowerBound, long upperBound) throws IOException {
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
