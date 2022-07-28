package year_2019.day14;

import java.io.IOException;

@FunctionalInterface
public interface CheckedFunction<T, R> {
   R apply(T t) throws IOException;
}
