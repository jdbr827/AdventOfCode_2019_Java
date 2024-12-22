package utils;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FrequencyTableUtil {

    public static <T> Map<T, Integer> createFrequencyTable(Collection<T> inputCollection) {
        Map<T, Integer> frequencyTable = new HashMap<>();
        for (T item : inputCollection) {
            frequencyTable.put(item, frequencyTable.getOrDefault(item, 0) + 1);
        }
        return frequencyTable;
    }

    public static <T> Map<T, Long> createFrequencyTableLong(Collection<T> inputCollection) {
        Map<T, Long> frequencyTable = new HashMap<>();
        for (T item : inputCollection) {
            frequencyTable.put(item, frequencyTable.getOrDefault(item, 0L) + 1);
        }
        return frequencyTable;
    }

    public static Map<Character, Integer> decomposeStringToFrequencyTable(String inputString) {
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (Character item : inputString.toCharArray()) {
            frequencyTable.put(item, frequencyTable.getOrDefault(item, 0) + 1);
        }
        return frequencyTable;
    }
}
