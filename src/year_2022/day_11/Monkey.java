package year_2022.day_11;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;
import java.util.function.Function;

@RequiredArgsConstructor
public class Monkey {
    @NonNull Queue<Item> items;
    @NonNull Consumer<Item> operation;
    @NonNull Function<Item, Monkey> nextMonkeyFunc;
    @NonNull Long itemsInspected = 0L;

    public void takeTurn() {
        Item item;
        while ((item = items.poll()) != null) {
            itemsInspected++;
            operation.accept(item);
            item.applyRelief();
            nextMonkeyFunc.apply(item).items.add(item);
        }
    }
}
