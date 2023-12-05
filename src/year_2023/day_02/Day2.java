package year_2023.day_02;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

public class Day2 {

}


@AllArgsConstructor
class Day2GameInfo {
    int gameId;
    List<Day2BagPull> bagPulls;

    boolean isGamePossible() {
        return bagPulls.stream().allMatch(Day2BagPull::isPossible);
    }

}


@AllArgsConstructor
class Day2BagPull {
    int red;
    int green;
    int blue;

    /**
     * Returns whether a pull is possible given the constraints of part 1
     * @return whether a pull is possible given the constraints of part 1
     */
    boolean isPossible() {
        return (red <= 12 && green <= 13 && blue <= 14);
    }
}