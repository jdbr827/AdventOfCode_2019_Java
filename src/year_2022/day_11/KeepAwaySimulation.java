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

    public long predictMonkeyBusiness(int numRounds) {
        for (int i=0; i<numRounds; i++) {executeRound();}

        return mostActiveMonkeys().stream()
                .map(m -> m.itemsInspected)
                .reduce(1L, Math::multiplyExact);
    }
}
