package year_2019;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.function.Supplier;

/**
 * Uses a supplier to implement the blockingqueue "take" functionality
 * @param <V>
 */
class SupplierQueue<V> extends ArrayBlockingQueue<V> {

    private Supplier<V> supplier;

    public SupplierQueue(Supplier<V> supplier) {
        super(1);
        this.supplier = supplier;
    }

    @Override
    public V take() {
        return supplier.get();
    }
}

