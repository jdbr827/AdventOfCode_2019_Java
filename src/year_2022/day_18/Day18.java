package year_2022.day_18;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day18 {

    static int part1(String fileName) {
        Day18Scanner scanner = new Day18Scanner(fileName);
        Map<ObsidianPlane, Integer> hasFace = new HashMap<>();
        List<Integer> point;
        while (!(point = scanner.nextObsidianDroplet()).isEmpty()) {
             //System.out.println(point);
            List<ObsidianPlane> sides = List.of(
                    ObsidianPlane.fromPoint(point, 0),
                    ObsidianPlane.fromPoint(point, 1),
                    ObsidianPlane.fromPoint(point, 2),
                    ObsidianPlane.fromPoint(point, 0).plus1X(),
                    ObsidianPlane.fromPoint(point, 1).plus1Y(),
                    ObsidianPlane.fromPoint(point, 2).plus1Z()
            );

            for (ObsidianPlane side : sides) {
                hasFace.put(side, hasFace.getOrDefault(side, 0) + 1);
            }

        }

        int count = 0;
        for (ObsidianPlane side : hasFace.keySet()) {
            if (hasFace.get(side) == 1) {
                //System.out.println(side);
                count++;
            }
        }

        return count;
    }

}
