package utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class FixedSizeBinaryHeap<T> {
    private T[] heap;

    FixedSizeBinaryHeap(Class<T> clazz, int size, Comparator<T> comparator) {
        heap = (T[]) Array.newInstance(clazz, size);
    }
}
