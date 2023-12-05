package year_2023.day_02;

import lombok.AllArgsConstructor;

import java.util.List;

public class Day2 {

}


@AllArgsConstructor
class Day2GameInfo {
    int gameId;
    List<Day2BagPull> bagPulls;

    /**
     * Returns whether a pull is possible given the constraints of part 1
     * @param red red cubes pulled
     * @param green green cubes pulled
     * @param blue blue cubes pulled
     * @return whether the pull was possible
     */
    static boolean isPullPossible(int red, int green, int blue) {
        return (red <= 12 && green <= 13 && blue <= 14);
    }

    static boolean isGamePossible(List<Day2BagPull> bagPulls) {
        return bagPulls.stream().allMatch(bagPull -> isPullPossible(bagPull.red, bagPull.green, bagPull.blue));
    }

}


@AllArgsConstructor
class Day2BagPull {
    int red;
    int green;
    int blue;
}