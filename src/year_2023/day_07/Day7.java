package year_2023.day_07;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import utils.FrequencyTableUtil;

import java.util.*;
import java.util.stream.Collectors;

public class Day7 {

    public static long day_7_part_1(String filename) {
        return computeTotalWinnings(Day7Scanner.scan(filename, false));
    }

    public static long day_7_part_2(String filename) {
        return computeTotalWinnings(Day7Scanner.scan(filename, true));
    }

    static long computeTotalWinnings(Collection<CamelCardsGame> games) {
        List<CamelCardsGame> sortedGames = games.stream()
                .sorted(CamelCardsGame::compareTo)
                .collect(Collectors.toList());
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
    boolean jokers;


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

    public Integer getCardValue(Character c) {
        if (jokers && c == 'J') {
            return 1;
        } else {
            return cardValue.get(c);
        }
    }

    public static CamelCardsGameHandType getHandType(String hand) {
        return getHandType(hand, false);
    }

    public static CamelCardsGameHandType getHandType(String hand, boolean jokers) {
        Map<Character, Integer> frequencyTable = FrequencyTableUtil.decomposeStringToFrequencyTable(hand);

        int numJokers = 0;
        if (jokers && frequencyTable.containsKey('J')) {
            numJokers = frequencyTable.remove('J');
        }

        List<Integer> values = frequencyTable.values().stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        if (values.isEmpty()) { // five jokers case
            return CamelCardsGameHandType.FIVE_OF_A_KIND;
        }

        if (jokers) {
            values.set(0, values.get(0) + numJokers);
        }

        if (values.get(0) >= 5) {
            return CamelCardsGameHandType.FIVE_OF_A_KIND;
        }

        Map<Integer, Integer> valueFrequencyTable = FrequencyTableUtil.createFrequencyTable(values);

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
        int handTypeDiff = getHandType(this.getHand(), jokers).compareTo(getHandType(o.getHand(), jokers));
        if (handTypeDiff != 0) {
            return handTypeDiff;
        }

        for (int i = 0; i < 5; i++) {
            int charAtiDiff = getCardValue(o.getHand().charAt(i)) - getCardValue(getHand().charAt(i));
            if (charAtiDiff != 0) {
                return charAtiDiff;
            }
        }

        return 0;
    }
}
