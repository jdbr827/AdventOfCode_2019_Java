package year_2022.day_11;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

@RequiredArgsConstructor
public class Monkey {
    @NonNull Queue<Long> items;
    @NonNull Function<Long, Long> operation;
    @NonNull Function<Long, Monkey> nextMonkeyFunc;
    @NonNull Long itemsInspected = 0L;

    public void takeTurn() {
        Long item;
        while ((item = items.poll()) != null) {
            itemsInspected++;
            item = operation.apply(item);
            item /= 3;
            nextMonkeyFunc.apply(item).items.add(item);
        }
    }
}
