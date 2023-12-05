package year_2023.day_05;

import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Day5Test {

    @Test
    public void test_mapping_rules() {
        AlmanacMap almanacMap = new AlmanacMap("seed", "soil", List.of(
                new AlmanacMapRule(50, 98, 2),
                new AlmanacMapRule(52, 50, 48)
        ));

        assertEquals(81, almanacMap.apply(79));
        assertEquals(14, almanacMap.apply(14));
        assertEquals(57, almanacMap.apply(55));
        assertEquals(13, almanacMap.apply(13));
        assertEquals(51, almanacMap.apply(99));

    }
}
