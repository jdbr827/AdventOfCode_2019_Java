package year_2022.day_11;

import lombok.AllArgsConstructor;

import java.util.Map;

public interface Item {

    long getWorryLevel();
    void setWorryLevel(long worryLevel);
    void applyRelief();

    static Item newItem(long initialWorry, int version) {
        if (version == 1) {
            return new SimpleItem(initialWorry);
        } else {
            return new ComplexItem(initialWorry);
        }
    }
}

@AllArgsConstructor
class SimpleItem implements Item {
    long worryLevel;

    @Override
    public long getWorryLevel() {
        return worryLevel;
    }

    @Override
    public void setWorryLevel(long worryLevel) {
        this.worryLevel = worryLevel;
    }

    @Override
    public void applyRelief() {
        this.worryLevel /= 3;
    }
}


@AllArgsConstructor
class ComplexItem implements Item {
    long worryLevel;

    public Map<Integer, Integer> primeFactorization = Map.of(
            2, 0,
            3, 0,
            5, 0,
            7, 0,
            11, 0,
            13, 0,
            17, 0,
            19, 0
    );

    @Override
    public long getWorryLevel() {
    }

    @Override
    public void setWorryLevel(long worryLevel) {
        this.worryLevel = worryLevel;
    }

    @Override
    public void applyRelief() {}
}
