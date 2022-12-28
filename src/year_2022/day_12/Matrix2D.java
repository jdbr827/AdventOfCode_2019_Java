package year_2022.day_12;


import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Matrix2D<T> implements IMatrix2D<T> {
    List<List<T>> matrix;

    @Override
    public T getValue(int x, int y) {
        return matrix.get(x).get(y);
    }

    @Override
    public void setValue(int x, int y, T val) {
        matrix.get(x).set(y, val);
    }

    @Override
    public int getXSize() {
        return matrix.size();
    }

    @Override
    public int getYSize() {
        return matrix.get(0).size();
    }

}
