package year_2022.day_11;

import lombok.AllArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class KeepAwaySimulation {
    List<Monkey> monkeys;

    public void executeRound() {
        for (Monkey monkey : monkeys) {
            monkey.takeTurn();
        }
    }

    public List<Monkey> mostActiveMonkeys() {
        return monkeys.stream()
                .sorted(Comparator.comparing(m -> -m.itemsInspected))
                .limit(2)
                .collect(Collectors.toList());
    }

    public int predictMonkeyBusiness() {
        for (int i=0; i<20; i++) {
            executeRound();
        }
        return mostActiveMonkeys().stream()
                .map(m -> m.itemsInspected)
                .reduce(1, Math::multiplyExact);
    }
}
