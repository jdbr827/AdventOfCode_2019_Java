package year_2023.day_02;

import lombok.*;

import java.util.Comparator;
import java.util.List;


@AllArgsConstructor
public class Day2 {
    List<Day2GameInfo> gameInfo;


    static int day_2_part_1_2023(String fileName) {
        Day2 day2 = Day2Scanner.scan(fileName);
        return day2.id_sum_of_possible_games();
    }

     static int day_2_part_2_2023(String fileName) {
        Day2 day2 = Day2Scanner.scan(fileName);
        return day2.sum_of_power_of_minimum_possible_setups();
    }

    int id_sum_of_possible_games() {
        return gameInfo.stream()
                .filter(Day2GameInfo::isGamePossible)
                .map(Day2GameInfo::getGameId)
                .reduce(0, Math::addExact);
    }

    int sum_of_power_of_minimum_possible_setups() {
        return gameInfo.stream()
                .map(Day2GameInfo::powerOfMinimumPossibleSetup)
                .reduce(0, Math::addExact);
    }



}


@AllArgsConstructor
class Day2GameInfo {
    @Getter int gameId;
    List<Day2BagPull> bagPulls;

    boolean isGamePossible() {
        return bagPulls.stream().allMatch(Day2BagPull::isPossible);
    }

    Day2BagPull minimumPossibleSetup() {
        return new Day2BagPull(
                bagPulls.stream().map(Day2BagPull::getRed).max(Comparator.naturalOrder()).orElseThrow(() -> new Error("No bag pulls")),
                bagPulls.stream().map(Day2BagPull::getGreen).max(Comparator.naturalOrder()).orElseThrow(() -> new Error("No bag pulls")),
                bagPulls.stream().map(Day2BagPull::getBlue).max(Comparator.naturalOrder()).orElseThrow(() -> new Error("No bag pulls"))
        );
    }

    int powerOfMinimumPossibleSetup() {
        return minimumPossibleSetup().power();
    }

}


@EqualsAndHashCode
@AllArgsConstructor
@ToString
class Day2BagPull {
    @Getter int red;
    @Getter int green;
    @Getter int blue;

    /**
     * Returns whether a pull is possible given the constraints of part 1
     * @return whether a pull is possible given the constraints of part 1
     */
    boolean isPossible() {
        return (red <= 12 && green <= 13 && blue <= 14);
    }

    int power() {
        return red * green * blue;
    }
}