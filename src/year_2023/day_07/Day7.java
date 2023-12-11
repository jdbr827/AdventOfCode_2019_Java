package year_2023.day_07;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day7 {

    public static long day_7_part_1(String filename) {
        return computeTotalWinnings(Day7Scanner.scan(filename));
    }

    static long computeTotalWinnings(Collection<CamelCardsGame> games) {
        List<CamelCardsGame> sortedGames = games.stream().sorted(CamelCardsGame::compareTo).collect(Collectors.toList());
        System.out.println(sortedGames.stream().map(CamelCardsGame::getHand).collect(Collectors.toList()));
        long total = 0L;
        for (int i=1; i<=sortedGames.size(); i++) {
            total += (long) i *sortedGames.get(sortedGames.size() - i).bid;
        }
        return total;
    }

};

enum CamelCardsGameHandType implements Comparable<CamelCardsGameHandType> {
    FIVE_OF_A_KIND(7),
    FOUR_OF_A_KIND(6),
    FULL_HOUSE(5),
    THREE_OF_A_KIND(4),
    TWO_PAIR(3),
    ONE_PAIR(2),
    HIGH_CARD(1);

    private final int handValue;

    CamelCardsGameHandType(int handValue) {
        this.handValue = handValue;
    }
}


@AllArgsConstructor
class CamelCardsGame implements Comparable<CamelCardsGame> {
    @Getter
    String hand;
    int bid;


    static final Map<Character, Integer> cardValue = Map.ofEntries(
            Map.entry('2', 2),
            Map.entry('3', 3),
            Map.entry('4', 4),
            Map.entry('5', 5),
            Map.entry('6', 6),
            Map.entry('7', 7),
            Map.entry('8', 8),
            Map.entry('9', 9),
            Map.entry('T', 10),
            Map.entry('J', 11),
            Map.entry('Q', 12),
            Map.entry('K', 13),
            Map.entry('A', 14)
    );



    public static CamelCardsGameHandType getHandType(String hand) {
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (Character card : hand.toCharArray()) {
            frequencyTable.put(card, frequencyTable.getOrDefault(card, 0) + 1);
        }

        Collection<Integer> values = frequencyTable.values();

        Map<Integer, Integer> valueFrequencyTable = new HashMap<>();
        for (Integer value: values) {
            valueFrequencyTable.put(value, valueFrequencyTable.getOrDefault(value, 0) + 1);
        }

        if (valueFrequencyTable.getOrDefault(5, 0) > 0) {
            return CamelCardsGameHandType.FIVE_OF_A_KIND;
        }

        if (valueFrequencyTable.getOrDefault(4, 0) > 0) {
            return CamelCardsGameHandType.FOUR_OF_A_KIND;
        }

        if (valueFrequencyTable.getOrDefault(3, 0) > 0 && valueFrequencyTable.getOrDefault(2, 0) > 0) {
            return CamelCardsGameHandType.FULL_HOUSE;
        }

        if (valueFrequencyTable.getOrDefault(3, 0) > 0) {
            return CamelCardsGameHandType.THREE_OF_A_KIND;
        }

        if (valueFrequencyTable.getOrDefault(2, 0) >= 2) {
            return CamelCardsGameHandType.TWO_PAIR;
        }

        if (valueFrequencyTable.getOrDefault(2, 0) > 0) {
            return CamelCardsGameHandType.ONE_PAIR;
        }

        return CamelCardsGameHandType.HIGH_CARD;

    }

    @Override
    public int compareTo(@NotNull CamelCardsGame o) {
        int handTypeDiff = getHandType(this.getHand()).compareTo(getHandType(o.getHand()));
        if (handTypeDiff != 0) {
            return handTypeDiff;
        }

        for (int i = 0; i < 5; i++) {
            int charAtiDiff = cardValue.get(o.getHand().charAt(i)) - cardValue.get(getHand().charAt(i));
            if (charAtiDiff != 0) {
                return charAtiDiff;
            }
        }

        return 0;
    }
}
