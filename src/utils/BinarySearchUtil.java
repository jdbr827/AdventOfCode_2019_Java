package utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        long dist = midPoint - lowerBound;

        while (midPoint != lowerBound) {

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

    /* Mutation */
    public static <T extends Comparable<T>> ArrayList<T> binaryInsert(ArrayList<T> arr, T toInsert) throws IOException {
        if (toInsert.compareTo(arr.get(0)) < 0) {
            arr.add(0, toInsert);
        } else {
            long idxToInsert = doBinarySearch((i) -> arr.get(i.intValue()), toInsert, 0, arr.size());
            System.out.println(idxToInsert);
            arr.add((int) idxToInsert + 1, toInsert);
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Integer> testLst = new ArrayList<>();
        testLst.add(0);
        testLst.add(2);
        testLst.add(4);
        System.out.println(binaryInsert(testLst, 3));
    }
}
